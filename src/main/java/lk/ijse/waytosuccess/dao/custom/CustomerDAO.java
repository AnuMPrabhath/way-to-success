package lk.ijse.waytosuccess.dao.custom;

import lk.ijse.waytosuccess.Entity.Customer;
import lk.ijse.waytosuccess.dao.CrudDAO;
import lk.ijse.waytosuccess.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {
    Customer selectCustomerId(String customerId) throws SQLException;
    List<String> getIds() throws SQLException;
}
