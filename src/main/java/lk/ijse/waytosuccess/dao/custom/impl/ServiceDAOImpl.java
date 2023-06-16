package lk.ijse.waytosuccess.dao.custom.impl;

import lk.ijse.waytosuccess.Entity.Service;
import lk.ijse.waytosuccess.dao.SQLUtil;
import lk.ijse.waytosuccess.dao.custom.ServiceDAO;
import lk.ijse.waytosuccess.dto.ServiceDto;
import lk.ijse.waytosuccess.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAOImpl implements ServiceDAO {
    @Override
    public ArrayList<Service> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM repair";
        ArrayList<Service> data = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            data.add(new Service(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3)
            ));
        }
        return data;
    }

    @Override
    public boolean add(Service dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO repair(service_code, description, service_charge) VALUES(?, ?, ?)";
        return SQLUtil.execute(sql, dto.getService_code(), dto.getDescription(), dto.getService_charge());
    }

    @Override
    public boolean update(Service dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE repair SET description = ?, getService_charge = ? WHERE getService_code = ?";
        return SQLUtil.execute(sql, dto.getDescription(), dto.getService_charge(), dto.getService_code());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM repair WHERE service_code = ?";
        return SQLUtil.execute(sql, id);
    }

    @Override
    public List<Service> search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM repair WHERE service_code LIKE '"+id+"%'";
        ResultSet execute = SQLUtil.execute(sql);
        List<Service> services = new ArrayList<>();
        while (execute.next()){
            services.add(new Service(
                    execute.getString(1),
                    execute.getString(2),
                    execute.getDouble(3)
            ));
        }
        return services;
    }

    @Override
    public Service selectService(String serviceCode) throws SQLException {
        String sql = "SELECT * FROM repair WHERE service_code = ?";

        ResultSet resultSet = SQLUtil.execute(sql, serviceCode);
        if (resultSet.next()) {
            return new Service(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3)
            );
        }
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        String sql = "SELECT service_code FROM repair";

        List<String> ids = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            ids.add(resultSet.getString(1));
        }
        return ids;
    }
}
