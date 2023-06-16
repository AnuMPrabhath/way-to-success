package lk.ijse.waytosuccess.dao.custom.impl;

import lk.ijse.waytosuccess.Entity.Customer;
import lk.ijse.waytosuccess.dao.SQLUtil;
import lk.ijse.waytosuccess.dao.custom.CustomerDAO;
import lk.ijse.waytosuccess.dto.CustomerDto;
import lk.ijse.waytosuccess.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM customer";
        ArrayList<Customer> data = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            data.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return data;
    }

    @Override
    public boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO customer(cust_id, cust_name, contact_no, address, date) VALUES(?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, customer.getCust_id(), customer.getCust_name(), customer.getContact(), customer.getAddress(), customer.getDate());
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE customer SET cust_name = ?, contact_no = ?, address = ?, date = ?  WHERE cust_id = ?";
        return SQLUtil.execute(sql, customer.getCust_name(), customer.getContact(), customer.getAddress(), customer.getDate(), customer.getCust_id());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM customer WHERE cust_id = ?";
        return SQLUtil.execute(sql, id);
    }

    @Override
    public List<Customer> search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM customer WHERE cust_id LIKE '"+id+"%'";
        ResultSet execute = SQLUtil.execute(sql);
        ArrayList<Customer> customerDtos = new ArrayList<>();
        while (execute.next()){
            customerDtos.add(new Customer(
                    execute.getString(1),
                    execute.getString(2),
                    execute.getString(3),
                    execute.getString(4),
                    execute.getString(5)
            ));
        }
        return customerDtos;
    }

    @Override
    public Customer selectCustomerId(String customerId) throws SQLException {
        String sql = "SELECT * FROM customer WHERE cust_id = ?";

        ResultSet resultSet = SQLUtil.execute(sql, customerId);
        if (resultSet.next()) {
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        String sql = "SELECT cust_id FROM customer";

        List<String> ids = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            ids.add(resultSet.getString(1));
        }
        return ids;
    }
}
