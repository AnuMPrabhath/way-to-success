package lk.ijse.waytosuccess.dao.custom.impl;

import lk.ijse.waytosuccess.Entity.Supplier;
import lk.ijse.waytosuccess.dao.SQLUtil;
import lk.ijse.waytosuccess.dao.custom.SupplierDAO;
import lk.ijse.waytosuccess.dto.SupplierDto;
import lk.ijse.waytosuccess.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM supplier";

        ArrayList<Supplier> data = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            data.add(new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return data;
    }

    @Override
    public boolean add(Supplier supplier) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO supplier(supplier_id, supplier_name, company, contact_no) VALUES( ?, ?, ?, ?)";
        return SQLUtil.execute(sql, supplier.getSupp_id(), supplier.getSupp_name(), supplier.getCompany(),
                supplier.getContact());
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE supplier SET supplier_name = ?,company = ? , contact_no = ?  WHERE supplier_id = ?";
        return SQLUtil.execute(sql, supplier.getSupp_name(), supplier.getCompany(), supplier.getContact(),
                supplier.getSupp_id());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM supplier WHERE supplier_id = ?";
        return SQLUtil.execute(sql, id);
    }

    @Override
    public List<Supplier> search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM supplier WHERE supplier_id LIKE '"+id+"%'";
        ResultSet execute = SQLUtil.execute(sql);
        ArrayList<Supplier> suppliers = new ArrayList<>();
        while (execute.next()){
            suppliers.add(new Supplier(
                    execute.getString(1),
                    execute.getString(2),
                    execute.getString(3),
                    execute.getString(4)
            ));
        }
        return suppliers;
    }
}
