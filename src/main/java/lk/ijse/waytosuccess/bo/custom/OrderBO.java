package lk.ijse.waytosuccess.bo.custom;

import lk.ijse.waytosuccess.bo.SuperBO;

import java.sql.SQLException;
import java.time.LocalDate;

public interface OrderBO extends SuperBO {
    boolean addOrder(String orderId, String cusId, LocalDate now) throws SQLException;
    String generateNextOrderId() throws SQLException;
}
