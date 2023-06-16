package lk.ijse.waytosuccess.bo.custom.impl;

import lk.ijse.waytosuccess.Entity.Supplier;
import lk.ijse.waytosuccess.bo.custom.SupplierBO;
import lk.ijse.waytosuccess.dao.DAOFactory;
import lk.ijse.waytosuccess.dao.custom.SupplierDAO;
import lk.ijse.waytosuccess.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public ArrayList<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> all = supplierDAO.getAll();
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();

        for (Supplier supplier : all){
            supplierDtos.add(new SupplierDto(supplier.getSupp_id(), supplier.getSupp_name(), supplier.getCompany(), supplier.getCompany()));
        }
        return supplierDtos;
    }

    @Override
    public boolean addSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        return supplierDAO.add(new Supplier(supplierDto.getSupp_id(), supplierDto.getSupp_name(), supplierDto.getCompany(),supplierDto.getContact()));
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public boolean updateSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplierDto.getSupp_id(), supplierDto.getSupp_name(), supplierDto.getCompany(),supplierDto.getContact()));
    }

    @Override
    public ArrayList<SupplierDto> searchSupplier(String id) throws SQLException, ClassNotFoundException {
        List<Supplier> suppliers = supplierDAO.search(id);
        ArrayList<SupplierDto> all = new ArrayList<>();

        for (Supplier supplier : suppliers){
            all.add(new SupplierDto(supplier.getSupp_id(), supplier.getSupp_name(), supplier.getCompany(), supplier.getCompany()));
        }
        return all;
    }
}
