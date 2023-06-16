package lk.ijse.waytosuccess.dao.custom;

import lk.ijse.waytosuccess.Entity.Service;
import lk.ijse.waytosuccess.dao.CrudDAO;
import lk.ijse.waytosuccess.dto.ServiceDto;

import java.sql.SQLException;
import java.util.List;

public interface ServiceDAO extends CrudDAO<Service> {
    Service selectService(String serviceCode) throws SQLException;
    List<String> getIds() throws SQLException;
}
