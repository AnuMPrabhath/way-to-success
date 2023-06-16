package lk.ijse.waytosuccess.bo.custom;

import lk.ijse.waytosuccess.bo.SuperBO;
import lk.ijse.waytosuccess.dto.CartItemDto;
import lk.ijse.waytosuccess.dto.CustomerDto;
import lk.ijse.waytosuccess.dto.ItemDto;
import lk.ijse.waytosuccess.dto.ReturnItemDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemBO extends SuperBO {
    ArrayList<ItemDto> getAllItems() throws SQLException, ClassNotFoundException;
    boolean addItem(ItemDto itemDto) throws SQLException, ClassNotFoundException;
    boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDto itemDto) throws SQLException, ClassNotFoundException;
    ArrayList<ItemDto> searchItem(String code) throws SQLException, ClassNotFoundException;
    boolean updateQty(List<CartItemDto> cartItemDTOList) throws SQLException;
    ItemDto selectItemCodes(String itemCode) throws SQLException;
    List<String> getIds() throws SQLException;
    boolean updateQty(ReturnItemDto returnItemDto) throws SQLException;
}
