package lk.ijse.waytosuccess.bo.custom.impl;

import lk.ijse.waytosuccess.Entity.CartItem;
import lk.ijse.waytosuccess.Entity.CartService;
import lk.ijse.waytosuccess.Entity.Order_Details;
import lk.ijse.waytosuccess.Entity.Return_Item;
import lk.ijse.waytosuccess.bo.custom.OrderDetailBO;
import lk.ijse.waytosuccess.dao.DAOFactory;
import lk.ijse.waytosuccess.dao.custom.OrderDetailDAO;
import lk.ijse.waytosuccess.dto.CartItemDto;
import lk.ijse.waytosuccess.dto.CartServiceDto;
import lk.ijse.waytosuccess.dto.Order_DetailsDto;
import lk.ijse.waytosuccess.dto.ReturnItemDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailBOImpl implements OrderDetailBO {

    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);

    @Override
    public boolean saveItem(String orderId, List<CartItemDto> cartItemDTOList) throws SQLException {
        List<CartItem> cartItemList = new ArrayList<>();
        for (CartItemDto cartItem : cartItemDTOList){
            cartItemList.add(new CartItem(cartItem.getItem_code(), cartItem.getUnit_price(), cartItem.getQty()));
        }

        for(CartItem cartItem : cartItemList) {
            if(!orderDetailDAO.save(orderId, cartItem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean saveServices(String orderId, List<CartServiceDto> cartSDTOList) throws SQLException {
        List<CartService> cartServiceList = new ArrayList<>();
        for (CartServiceDto cartServiceDto : cartSDTOList){
            cartServiceList.add(new CartService(cartServiceDto.getService_code(), cartServiceDto.getCharge()));
        }

        for(CartService cartService :  cartServiceList) {
            if(!orderDetailDAO.saveServices(orderId, cartService)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateQty(ReturnItemDto returnItemDto) throws SQLException {
        return orderDetailDAO.updateQty(new Return_Item(returnItemDto.getReturn_code(), returnItemDto.getItem_code(), returnItemDto.getQty(), returnItemDto.getOrder_id()));
    }

    @Override
    public Order_DetailsDto selectOrder(String orderId) throws SQLException {
        Order_Details order_details = orderDetailDAO.selectOrder(orderId);
        return new Order_DetailsDto(order_details.getOrder_id(), order_details.getItem_code(), order_details.getQty());
    }

    @Override
    public List<String> getIds(String selectOrderId) throws SQLException {
        return orderDetailDAO.getIds(selectOrderId);
    }
}
