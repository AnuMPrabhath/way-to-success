package lk.ijse.waytosuccess.bo.custom;

import lk.ijse.waytosuccess.bo.SuperBO;
import lk.ijse.waytosuccess.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO extends SuperBO {
    ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException;
    boolean addCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException;
    ArrayList<CustomerDto> searchCustomer(String id) throws SQLException, ClassNotFoundException;
    CustomerDto selectCustomerId(String customerId) throws SQLException;
    List<String> getIds() throws SQLException;
}
