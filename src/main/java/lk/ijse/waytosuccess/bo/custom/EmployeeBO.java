package lk.ijse.waytosuccess.bo.custom;

import lk.ijse.waytosuccess.bo.SuperBO;
import lk.ijse.waytosuccess.dto.CustomerDto;
import lk.ijse.waytosuccess.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    ArrayList<EmployeeDto> getAllEmployee() throws SQLException, ClassNotFoundException;
    boolean addEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException;
    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;
    boolean updateEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException;
    ArrayList<EmployeeDto> searchEmployee(String id) throws SQLException, ClassNotFoundException;
}
