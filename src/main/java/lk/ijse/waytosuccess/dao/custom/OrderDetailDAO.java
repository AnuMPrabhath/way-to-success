package lk.ijse.waytosuccess.dao.custom;

import lk.ijse.waytosuccess.Entity.CartItem;
import lk.ijse.waytosuccess.Entity.CartService;
import lk.ijse.waytosuccess.Entity.Order_Details;
import lk.ijse.waytosuccess.Entity.Return_Item;
import lk.ijse.waytosuccess.dao.SuperDAO;
import lk.ijse.waytosuccess.dto.CartServiceDto;
import lk.ijse.waytosuccess.dto.Order_DetailsDto;
import lk.ijse.waytosuccess.dto.ReturnItemDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO extends SuperDAO {
    boolean save(String orderId, CartItem cartItem) throws SQLException;
    boolean saveServices(String orderId, CartService cartService) throws SQLException;
    boolean updateQty(Return_Item returnItem) throws SQLException;
    Order_Details selectOrder(String orderId) throws SQLException;
    List<String> getIds(String selectOrderId) throws SQLException;
}
