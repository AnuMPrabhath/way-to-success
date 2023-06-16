package lk.ijse.waytosuccess.dao.custom;

import lk.ijse.waytosuccess.Entity.User;
import lk.ijse.waytosuccess.dao.SuperDAO;

import java.sql.SQLException;

public interface LoginPageDAO extends SuperDAO {
    boolean isUser(User user) throws SQLException;
}
