package lk.ijse.waytosuccess.dao.custom.impl;

import lk.ijse.waytosuccess.Entity.CartItem;
import lk.ijse.waytosuccess.Entity.Item;
import lk.ijse.waytosuccess.Entity.Return_Item;
import lk.ijse.waytosuccess.dao.SQLUtil;
import lk.ijse.waytosuccess.dao.custom.ItemDAO;
import lk.ijse.waytosuccess.dto.ItemDto;
import lk.ijse.waytosuccess.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM item";

        ArrayList<Item> data = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            data.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5)
            ));
        }
        return data;
    }

    @Override
    public boolean add(Item item) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item(item_code, item_type, description, unit_price, qty_on_hand) VALUES(?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, item.getItem_code(), item.getItem_type(), item.getDescription(), item.getUnit_price(), item.getQty());
    }

    @Override
    public boolean update(Item dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE item SET qty_on_hand = (qty_on_hand - ?) WHERE item_code = ?";
        return SQLUtil.execute(sql, dto.getQty(), dto.getItem_code());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM item WHERE item_code = ?";
        return SQLUtil.execute(sql, id);
    }

    @Override
    public List<Item> search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM item WHERE item_code LIKE '" + id + "%'";
        ResultSet execute = SQLUtil.execute(sql);
        ArrayList<Item> items = new ArrayList<>();
        while (execute.next()) {
            items.add(new Item(
                    execute.getString(1),
                    execute.getString(2),
                    execute.getString(3),
                    execute.getDouble(4),
                    execute.getInt(5)
            ));
        }
        return items;
    }

    @Override
    public boolean updateQty(CartItem cartItem) throws SQLException {
        String sql = "UPDATE item SET qty_on_hand = (qty_on_hand - ?) WHERE item_code = ?";
        return SQLUtil.execute(sql, cartItem.getQty(), cartItem.getItem_code());
    }

    @Override
    public Item selectItemCodes(String itemCode) throws SQLException {
        String sql = "SELECT * FROM item WHERE item_code = ?";

        ResultSet resultSet = SQLUtil.execute(sql, itemCode);
        if (resultSet.next()) {
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5)
            );
        }
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        String sql = "SELECT item_code FROM item";

        List<String> ids = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            ids.add(resultSet.getString(1));
        }
        return ids;
    }

    @Override
    public boolean updateQty(Return_Item returnItem) throws SQLException {
        String sql = "UPDATE item SET qty_on_hand = (qty_on_hand + ?) WHERE item_code = ?";
        return SQLUtil.execute(sql, returnItem.getQty(), returnItem.getItem_code());
    }
}
