package lk.ijse.waytosuccess.dao.custom;

import lk.ijse.waytosuccess.Entity.CartItem;
import lk.ijse.waytosuccess.Entity.Item;
import lk.ijse.waytosuccess.Entity.Return_Item;
import lk.ijse.waytosuccess.dao.CrudDAO;
import lk.ijse.waytosuccess.dto.CartItemDto;
import lk.ijse.waytosuccess.dto.ItemDto;
import lk.ijse.waytosuccess.dto.ReturnItemDto;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {
    boolean updateQty(CartItem cartItem) throws SQLException;
    Item selectItemCodes(String itemCode) throws SQLException;
    List<String> getIds() throws SQLException;
    boolean updateQty(Return_Item returnItem) throws SQLException;
}
