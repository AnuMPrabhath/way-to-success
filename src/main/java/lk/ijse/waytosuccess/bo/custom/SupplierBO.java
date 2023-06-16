package lk.ijse.waytosuccess.bo.custom;

import lk.ijse.waytosuccess.bo.SuperBO;
import lk.ijse.waytosuccess.dto.ServiceDto;
import lk.ijse.waytosuccess.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    ArrayList<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException;
    boolean addSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException;
    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;
    boolean updateSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException;
    ArrayList<SupplierDto> searchSupplier(String id) throws SQLException, ClassNotFoundException;
}
