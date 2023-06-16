package lk.ijse.waytosuccess.bo.custom.impl;

import lk.ijse.waytosuccess.Entity.CartItem;
import lk.ijse.waytosuccess.Entity.Item;
import lk.ijse.waytosuccess.Entity.Return_Item;
import lk.ijse.waytosuccess.bo.custom.ItemBO;
import lk.ijse.waytosuccess.dao.DAOFactory;
import lk.ijse.waytosuccess.dao.custom.ItemDAO;
import lk.ijse.waytosuccess.dto.CartItemDto;
import lk.ijse.waytosuccess.dto.ItemDto;
import lk.ijse.waytosuccess.dto.ReturnItemDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDto> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = itemDAO.getAll();
        ArrayList<ItemDto> all = new ArrayList<>();

        for (Item item: items){
            all.add(new ItemDto(item.getItem_code(), item.getItem_type(), item.getDescription(), item.getUnit_price(), item.getQty()));
        }
        return all;
    }

    @Override
    public boolean addItem(ItemDto itemDto) throws SQLException, ClassNotFoundException {
        return itemDAO.add(new Item(itemDto.getItem_code(), itemDto.getItem_type(), itemDto.getDescription(), itemDto.getUnit_price(), itemDto.getQty()));
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

    @Override
    public boolean updateItem(ItemDto itemDto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(itemDto.getItem_code(), itemDto.getItem_type(), itemDto.getDescription(), itemDto.getUnit_price(), itemDto.getQty()));
    }

    @Override
    public ArrayList<ItemDto> searchItem(String code) throws SQLException, ClassNotFoundException {
        List<Item> items = itemDAO.search(code);
        ArrayList<ItemDto> all = new ArrayList<>();

        for (Item item: items){
            all.add(new ItemDto(item.getItem_code(), item.getItem_type(), item.getDescription(), item.getUnit_price(), item.getQty()));
        }
        return all;
    }

    @Override
    public boolean updateQty(List<CartItemDto> cartItemDTOList) throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();
        for (CartItemDto dto : cartItemDTOList){
            cartItems.add(new CartItem(dto.getItem_code(), dto.getUnit_price(), dto.getQty()));
        }

        for (CartItem item : cartItems) {
            if (!itemDAO.updateQty(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemDto selectItemCodes(String itemCode) throws SQLException {
        Item item = itemDAO.selectItemCodes(itemCode);
        return new ItemDto(item.getItem_code(), item.getItem_type(), item.getDescription(), item.getUnit_price(), item.getQty());
    }

    @Override
    public List<String> getIds() throws SQLException {
        return itemDAO.getIds();
    }

    @Override
    public boolean updateQty(ReturnItemDto returnItemDto) throws SQLException {
        return itemDAO.updateQty(new Return_Item(returnItemDto.getReturn_code(), returnItemDto.getItem_code(), returnItemDto.getQty(), returnItemDto.getOrder_id()));
    }
}
