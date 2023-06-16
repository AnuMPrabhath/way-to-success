package lk.ijse.waytosuccess.dao.custom.impl;

import lk.ijse.waytosuccess.Entity.Employee;
import lk.ijse.waytosuccess.dao.SQLUtil;
import lk.ijse.waytosuccess.dao.custom.EmployeeDAO;
import lk.ijse.waytosuccess.dto.EmployeeDto;
import lk.ijse.waytosuccess.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM employee";

        ArrayList<Employee> data = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            data.add(new Employee(
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
    public boolean add(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO employee(emp_id, emp_name, contact_no, address, date) VALUES(?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, employee.getEmp_id(), employee.getEmp_name(), employee.getContact(),
                employee.getAddress(), employee.getDate());
    }

    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE customer SET emp_name = ?, contact_no = ?, address = ?, date = ?  WHERE emp_id = ?";
        return SQLUtil.execute(sql, employee.getEmp_name(), employee.getContact(), employee.getAddress(),
                employee.getDate(), employee.getEmp_id());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM customer WHERE emp_id = ?";
        return SQLUtil.execute(sql, id);
    }

    @Override
    public List<Employee> search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM employee WHERE emp_id LIKE '"+id+"%'";
        ResultSet execute = SQLUtil.execute(sql);
        ArrayList<Employee> employees = new ArrayList<>();
        while (execute.next()){
            employees.add(new Employee(
                    execute.getString(1),
                    execute.getString(2),
                    execute.getString(3),
                    execute.getString(4),
                    execute.getString(5)
            ));
        }
        return employees;
    }
}
