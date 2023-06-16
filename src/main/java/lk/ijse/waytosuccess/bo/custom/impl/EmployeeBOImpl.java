package lk.ijse.waytosuccess.bo.custom.impl;

import lk.ijse.waytosuccess.Entity.Customer;
import lk.ijse.waytosuccess.Entity.Employee;
import lk.ijse.waytosuccess.bo.custom.EmployeeBO;
import lk.ijse.waytosuccess.dao.DAOFactory;
import lk.ijse.waytosuccess.dao.custom.EmployeeDAO;
import lk.ijse.waytosuccess.dto.CustomerDto;
import lk.ijse.waytosuccess.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public ArrayList<EmployeeDto> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = employeeDAO.getAll();
        ArrayList<EmployeeDto> all = new ArrayList<>();

        for(Employee employee : employees){
            all.add(new EmployeeDto(
                    employee.getEmp_id(),
                    employee.getEmp_name(),
                    employee.getContact(),
                    employee.getAddress(),
                    employee.getDate()
            ));
        }
        return all;
    }

    @Override
    public boolean addEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        return employeeDAO.add(new Employee(employeeDto.getEmp_id(), employeeDto.getEmp_name(), employeeDto.getContact(), employeeDto.getAddress(), employeeDto.getDate()));
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public boolean updateEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(employeeDto.getEmp_id(), employeeDto.getEmp_name(), employeeDto.getContact(), employeeDto.getAddress(), employeeDto.getDate()));
    }

    @Override
    public ArrayList<EmployeeDto> searchEmployee(String id) throws SQLException, ClassNotFoundException {
        List<Employee> employees = employeeDAO.search(id);
        ArrayList<EmployeeDto> all = new ArrayList<>();

        for(Employee employee : employees){
            all.add(new EmployeeDto(
                    employee.getEmp_id(),
                    employee.getEmp_name(),
                    employee.getContact(),
                    employee.getAddress(),
                    employee.getDate()
            ));
        }
        return all;
    }
}
