package lk.ijse.waytosuccess.bo.custom.impl;

import lk.ijse.waytosuccess.bo.custom.OrderBO;
import lk.ijse.waytosuccess.dao.DAOFactory;
import lk.ijse.waytosuccess.dao.custom.OrderDAO;

import java.sql.SQLException;
import java.time.LocalDate;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public boolean addOrder(String orderId, String cusId, LocalDate now) throws SQLException {
        return orderDAO.addOrder(orderId, cusId, now);
    }

    @Override
    public String generateNextOrderId() throws SQLException {
        String currentOrderId = orderDAO.generateNextOrderId();
        if(currentOrderId != null) {
            String[] strings = currentOrderId.split("O");
            int id = Integer.parseInt(strings[1]);
            id++;

            return String.format("O%03d",id);
        }
        return "O001";
    }
}
