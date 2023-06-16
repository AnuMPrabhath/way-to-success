package lk.ijse.waytosuccess.bo.custom.impl;

import lk.ijse.waytosuccess.Entity.Service;
import lk.ijse.waytosuccess.bo.custom.ServiceBO;
import lk.ijse.waytosuccess.dao.DAOFactory;
import lk.ijse.waytosuccess.dao.custom.ServiceDAO;
import lk.ijse.waytosuccess.dto.ServiceDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceBOImpl implements ServiceBO {

    ServiceDAO serviceDAO = (ServiceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SERVICE);

    @Override
    public ArrayList<ServiceDto> getAllServices() throws SQLException, ClassNotFoundException {
        ArrayList<Service> all = serviceDAO.getAll();
        ArrayList<ServiceDto> serviceDtos = new ArrayList<>();

        for (Service service : all){
            serviceDtos.add(new ServiceDto(service.getService_code(), service.getDescription(), service.getService_charge()));
        }
        return serviceDtos;
    }

    @Override
    public boolean addService(ServiceDto serviceDto) throws SQLException, ClassNotFoundException {
        return serviceDAO.add(new Service(serviceDto.getService_code(), serviceDto.getDescription(), serviceDto.getService_charge()));
    }

    @Override
    public boolean deleteService(String code) throws SQLException, ClassNotFoundException {
        return serviceDAO.delete(code);
    }

    @Override
    public boolean updateService(ServiceDto serviceDto) throws SQLException, ClassNotFoundException {
        return serviceDAO.update(new Service(serviceDto.getService_code(), serviceDto.getDescription(), serviceDto.getService_charge()));
    }

    @Override
    public ArrayList<ServiceDto> searchService(String code) throws SQLException, ClassNotFoundException {
        List<Service> services = serviceDAO.search(code);
        ArrayList<ServiceDto> all = new ArrayList<>();

        for (Service service : services){
            all.add(new ServiceDto(service.getService_code(), service.getDescription(), service.getService_charge()));
        }
        return all;
    }

    @Override
    public ServiceDto selectService(String serviceCode) throws SQLException {
        Service service = serviceDAO.selectService(serviceCode);
        return new ServiceDto(service.getService_code(), service.getDescription(), service.getService_charge());
    }

    @Override
    public List<String> getIds() throws SQLException {
        return serviceDAO.getIds();
    }
}
