package lk.ijse.waytosuccess.dao.custom.impl;

import lk.ijse.waytosuccess.dao.SQLUtil;
import lk.ijse.waytosuccess.dao.custom.OrderDAO;
import lk.ijse.waytosuccess.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean addOrder(String orderId, String cusId, LocalDate now) throws SQLException {
        String sql = "INSERT INTO place_order(order_id, date, cust_id) VALUES(?, ?, ?)";
        return SQLUtil.execute(sql, orderId, now, cusId);
    }

    @Override
    public String generateNextOrderId() throws SQLException {
        String sql = "SELECT order_id FROM place_order ORDER BY order_id DESC LIMIT 1";

        ResultSet resultSet = SQLUtil.execute(sql);
        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
