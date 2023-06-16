package lk.ijse.waytosuccess.bo.custom;

import lk.ijse.waytosuccess.bo.SuperBO;
import lk.ijse.waytosuccess.dto.CartItemDto;
import lk.ijse.waytosuccess.dto.CartServiceDto;
import lk.ijse.waytosuccess.dto.Place_orderDto;

import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {
    boolean placeOrder(String orderId, String cusId, List<CartItemDto> cartItemDtoList, List<CartServiceDto> cartServiceDtoList) throws SQLException;
    Place_orderDto selectOrder(String orderId) throws SQLException;
    List<String> getIds() throws SQLException;
}
