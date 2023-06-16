package lk.ijse.waytosuccess.dao.custom.impl;

import lk.ijse.waytosuccess.Entity.Return_Item;
import lk.ijse.waytosuccess.dao.SQLUtil;
import lk.ijse.waytosuccess.dao.custom.ReturnItemDAO;
import lk.ijse.waytosuccess.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnItemDAOImpl implements ReturnItemDAO {
    @Override
    public boolean addReturn(Return_Item returnItem) throws SQLException {
        String sql = "INSERT INTO return_item(return_code, item_code, qty, order_id) VALUES(?, ?, ?, ?)";
        return SQLUtil.execute(sql, returnItem.getReturn_code(), returnItem.getItem_code(),
                returnItem.getQty(), returnItem.getOrder_id());
    }

    @Override
    public String generateNextReturnCode() throws SQLException {
        String sql = "SELECT return_code FROM return_item ORDER BY return_code DESC LIMIT 1";

        ResultSet resultSet = SQLUtil.execute(sql);
        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
