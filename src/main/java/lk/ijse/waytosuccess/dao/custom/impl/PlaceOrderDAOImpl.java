package lk.ijse.waytosuccess.dao.custom.impl;

import lk.ijse.waytosuccess.Entity.Place_Order;
import lk.ijse.waytosuccess.dao.SQLUtil;
import lk.ijse.waytosuccess.dao.custom.PlaceOrderDAO;
import lk.ijse.waytosuccess.dto.Place_orderDto;
import lk.ijse.waytosuccess.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderDAOImpl implements PlaceOrderDAO {
    @Override
    public Place_Order selectOrder(String orderId) throws SQLException {
        String sql = "SELECT * FROM place_order WHERE order_id = ?";

        ResultSet resultSet = SQLUtil.execute(sql, orderId);
        if (resultSet.next()) {
            return new Place_Order(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
        }
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        String sql = "SELECT order_id FROM place_order";

        List<String> ids = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            ids.add(resultSet.getString(1));
        }
        return ids;
    }
}
