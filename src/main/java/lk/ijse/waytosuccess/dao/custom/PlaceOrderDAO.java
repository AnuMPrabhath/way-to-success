package lk.ijse.waytosuccess.dao.custom;

import lk.ijse.waytosuccess.Entity.Place_Order;
import lk.ijse.waytosuccess.dao.SuperDAO;

import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderDAO extends SuperDAO {
    Place_Order selectOrder(String orderId) throws SQLException;
    List<String> getIds() throws SQLException;
}
