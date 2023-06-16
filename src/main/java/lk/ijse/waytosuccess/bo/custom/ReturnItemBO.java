package lk.ijse.waytosuccess.bo.custom;

import lk.ijse.waytosuccess.bo.SuperBO;
import lk.ijse.waytosuccess.dto.ReturnItemDto;

import java.sql.SQLException;

public interface ReturnItemBO extends SuperBO {
    boolean placeReturn(ReturnItemDto returnItemDto) throws SQLException;
    String generateNextReturnCode() throws SQLException;
}
