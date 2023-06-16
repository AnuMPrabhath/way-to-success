package lk.ijse.waytosuccess.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean add(T dto) throws SQLException, ClassNotFoundException;
    boolean update(T dto) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    List<T> search(String id) throws SQLException, ClassNotFoundException;

}
