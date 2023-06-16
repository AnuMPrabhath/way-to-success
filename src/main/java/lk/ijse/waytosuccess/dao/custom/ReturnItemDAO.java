package lk.ijse.waytosuccess.dao.custom;

import lk.ijse.waytosuccess.Entity.Return_Item;
import lk.ijse.waytosuccess.dao.SuperDAO;
import lk.ijse.waytosuccess.dto.ReturnItemDto;

import java.sql.SQLException;

public interface ReturnItemDAO extends SuperDAO {
    boolean addReturn(Return_Item returnItem) throws SQLException;
    String generateNextReturnCode() throws SQLException;
}
