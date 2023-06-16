package lk.ijse.waytosuccess.bo.custom.impl;

import lk.ijse.waytosuccess.Entity.Customer;
import lk.ijse.waytosuccess.bo.custom.CustomerBO;
import lk.ijse.waytosuccess.dao.DAOFactory;
import lk.ijse.waytosuccess.dao.custom.CustomerDAO;
import lk.ijse.waytosuccess.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.waytosuccess.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDto> allCustomer = new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();

        for(Customer customer : all){
            allCustomer.add(new CustomerDto(
                    customer.getCust_id(),
                    customer.getCust_name(),
                    customer.getContact(),
                    customer.getAddress(),
                    customer.getDate()
            ));
        }
        return allCustomer;
    }

    @Override
    public boolean addCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(customerDto.getCust_id(), customerDto.getCust_name(), customerDto.getContact(), customerDto.getAddress(), customerDto.getDate()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDto.getCust_id(), customerDto.getCust_name(), customerDto.getContact(), customerDto.getAddress(), customerDto.getDate()));
    }

    @Override
    public ArrayList<CustomerDto> searchCustomer(String id) throws SQLException, ClassNotFoundException {
        List<Customer> customers = customerDAO.search(id);
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();

        for(Customer customer : customers){
            customerDtos.add(new CustomerDto(
                    customer.getCust_id(),
                    customer.getCust_name(),
                    customer.getContact(),
                    customer.getAddress(),
                    customer.getDate()
            ));
        }
        return customerDtos;
    }

    @Override
    public CustomerDto selectCustomerId(String customerId) throws SQLException {
        Customer customer = customerDAO.selectCustomerId(customerId);
        return new CustomerDto(customer.getCust_id(), customer.getCust_name(),customer.getContact(), customer.getAddress(),customer.getDate());
    }

    @Override
    public List<String> getIds() throws SQLException {
        return customerDAO.getIds();
    }
}
