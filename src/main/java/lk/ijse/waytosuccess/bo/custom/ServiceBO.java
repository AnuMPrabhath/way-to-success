package lk.ijse.waytosuccess.bo.custom;

import lk.ijse.waytosuccess.bo.SuperBO;
import lk.ijse.waytosuccess.dto.CustomerDto;
import lk.ijse.waytosuccess.dto.ServiceDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ServiceBO extends SuperBO {
    ArrayList<ServiceDto> getAllServices() throws SQLException, ClassNotFoundException;
    boolean addService(ServiceDto serviceDto) throws SQLException, ClassNotFoundException;
    boolean deleteService(String code) throws SQLException, ClassNotFoundException;
    boolean updateService(ServiceDto serviceDto) throws SQLException, ClassNotFoundException;
    ArrayList<ServiceDto> searchService(String code) throws SQLException, ClassNotFoundException;
    ServiceDto selectService(String serviceCode) throws SQLException;
    List<String> getIds() throws SQLException;
}
