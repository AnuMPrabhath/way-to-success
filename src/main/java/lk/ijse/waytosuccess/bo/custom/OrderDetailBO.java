package lk.ijse.waytosuccess.bo.custom;

import lk.ijse.waytosuccess.bo.SuperBO;
import lk.ijse.waytosuccess.dto.CartItemDto;
import lk.ijse.waytosuccess.dto.CartServiceDto;
import lk.ijse.waytosuccess.dto.Order_DetailsDto;
import lk.ijse.waytosuccess.dto.ReturnItemDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailBO extends SuperBO {
    boolean saveItem(String orderId, List<CartItemDto> cartItemDTOList) throws SQLException;
    boolean saveServices(String orderId, List<CartServiceDto> cartSDTOList) throws SQLException;
    boolean updateQty(ReturnItemDto returnItemDto) throws SQLException;
    Order_DetailsDto selectOrder(String orderId) throws SQLException;
    List<String> getIds(String selectOrderId) throws SQLException;
}
