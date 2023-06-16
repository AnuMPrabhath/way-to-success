package lk.ijse.waytosuccess.dao.custom.impl;

import lk.ijse.waytosuccess.Entity.CartItem;
import lk.ijse.waytosuccess.Entity.CartService;
import lk.ijse.waytosuccess.Entity.Order_Details;
import lk.ijse.waytosuccess.Entity.Return_Item;
import lk.ijse.waytosuccess.dao.SQLUtil;
import lk.ijse.waytosuccess.dao.custom.OrderDetailDAO;
import lk.ijse.waytosuccess.dto.Order_DetailsDto;
import lk.ijse.waytosuccess.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean save(String orderId, CartItem cartItem) throws SQLException {
        String sql = "INSERT INTO order_detail(order_id, item_code, qty) VALUES (?, ?, ?)";
        return SQLUtil.execute(sql,orderId,cartItem.getItem_code(),cartItem.getQty());
    }

    @Override
    public boolean saveServices(String orderId, CartService cartService) throws SQLException {
        String sql = "INSERT INTO repair_detail(service_code, order_id) VALUES (?, ?)";
        return SQLUtil.execute(sql, cartService.getService_code(), orderId);
    }

    @Override
    public boolean updateQty(Return_Item returnItem) throws SQLException {
        String sql = "UPDATE order_detail SET qty = (qty - ?) WHERE order_id = ? AND item_code = ?";
        return SQLUtil.execute(sql, returnItem.getQty(), returnItem.getOrder_id(),
                returnItem.getItem_code());
    }

    @Override
    public Order_Details selectOrder(String orderId) throws SQLException {
        String sql = "SELECT * FROM order_detail WHERE order_id = ?";

        ResultSet resultSet = SQLUtil.execute(sql, orderId);
        if (resultSet.next()) {
            return new Order_Details(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
        }
        return null;
    }

    @Override
    public List<String> getIds(String selectOrderId) throws SQLException {
        String sql = "SELECT item_code FROM order_detail WHERE order_id = ?";

        List<String> ids = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql, selectOrderId);
        while (resultSet.next()) {
            ids.add(resultSet.getString(1));
        }
        return ids;
    }
}
