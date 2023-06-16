package lk.ijse.waytosuccess.bo.custom.impl;

import lk.ijse.waytosuccess.DB.DBConnection;
import lk.ijse.waytosuccess.Entity.Return_Item;
import lk.ijse.waytosuccess.bo.BOFactory;
import lk.ijse.waytosuccess.bo.custom.ItemBO;
import lk.ijse.waytosuccess.bo.custom.OrderDetailBO;
import lk.ijse.waytosuccess.bo.custom.ReturnItemBO;
import lk.ijse.waytosuccess.dao.DAOFactory;
import lk.ijse.waytosuccess.dao.custom.ReturnItemDAO;
import lk.ijse.waytosuccess.dto.ReturnItemDto;

import java.sql.Connection;
import java.sql.SQLException;

public class ReturnItemBOImpl implements ReturnItemBO {

    ReturnItemDAO returnItemDAO = (ReturnItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RETURN_ITEM);
    OrderDetailBO orderDetailBO = (OrderDetailBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER_DETAIL);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    @Override
    public boolean placeReturn(ReturnItemDto returnItemDto) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();

            con.setAutoCommit(false);

            boolean isSaved = returnItemDAO.addReturn(new Return_Item(returnItemDto.getReturn_code(), returnItemDto.getItem_code(), returnItemDto.getQty(), returnItemDto.getOrder_id()));
            if (isSaved) {
                boolean isUpdatedOrder = orderDetailBO.updateQty(returnItemDto);
                if (isUpdatedOrder) {
                    boolean isUpdateItemQty = itemBO.updateQty(returnItemDto);
                    if (isUpdateItemQty) {
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
    public String generateNextReturnCode() throws SQLException {
        String currentOrderId = returnItemDAO.generateNextReturnCode();
        if(currentOrderId != null) {
            String[] strings = currentOrderId.split("R");
            int id = Integer.parseInt(strings[1]);
            id++;

            return String.format("R%03d",id);
        }
        return "R001";
    }
}
