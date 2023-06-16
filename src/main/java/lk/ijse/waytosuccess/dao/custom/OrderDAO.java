package lk.ijse.waytosuccess.dao.custom;

import lk.ijse.waytosuccess.dao.SuperDAO;

import java.sql.SQLException;
import java.time.LocalDate;

public interface OrderDAO extends SuperDAO {
    boolean addOrder(String orderId, String cusId, LocalDate now) throws SQLException;
    String generateNextOrderId() throws SQLException;
}
