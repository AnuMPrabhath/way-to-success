package lk.ijse.waytosuccess.bo.custom.impl;

import lk.ijse.waytosuccess.DB.DBConnection;
import lk.ijse.waytosuccess.Entity.Place_Order;
import lk.ijse.waytosuccess.bo.BOFactory;
import lk.ijse.waytosuccess.bo.custom.ItemBO;
import lk.ijse.waytosuccess.bo.custom.OrderBO;
import lk.ijse.waytosuccess.bo.custom.OrderDetailBO;
import lk.ijse.waytosuccess.bo.custom.PlaceOrderBO;
import lk.ijse.waytosuccess.dao.DAOFactory;
import lk.ijse.waytosuccess.dao.custom.PlaceOrderDAO;
import lk.ijse.waytosuccess.dto.CartItemDto;
import lk.ijse.waytosuccess.dto.CartServiceDto;
import lk.ijse.waytosuccess.dto.Place_orderDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    PlaceOrderDAO placeOrderDAO = (PlaceOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PLACE_ORDER);

    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);
    OrderDetailBO orderDetailBO = (OrderDetailBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER_DETAIL);

    @Override
    public boolean placeOrder(String orderId, String cusId, List<CartItemDto> cartItemDtoList, List<CartServiceDto> cartServiceDtoList) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();

            con.setAutoCommit(false);

            boolean isSaved = orderBO.addOrder(orderId, cusId, LocalDate.now());
            if (isSaved) {
                boolean isUpdated = itemBO.updateQty(cartItemDtoList);
                if (isUpdated) {
                    boolean isOrderDetailSaved = orderDetailBO.saveItem(orderId, cartItemDtoList);
                    boolean isRepairDetailSaved = orderDetailBO.saveServices(orderId, cartServiceDtoList);
                    if (isOrderDetailSaved && isRepairDetailSaved) {
                        con.commit();
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException er) {
            er.printStackTrace();
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }

    @Override
    public Place_orderDto selectOrder(String orderId) throws SQLException {
        Place_Order place_order = placeOrderDAO.selectOrder(orderId);
        return new Place_orderDto(place_order.getOrder_id(), place_order.getDate(), place_order.getCust_id());
    }

    @Override
    public List<String> getIds() throws SQLException {
        return placeOrderDAO.getIds();
    }
}
