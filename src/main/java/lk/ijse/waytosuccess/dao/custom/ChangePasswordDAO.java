package lk.ijse.waytosuccess.dao.custom;

import lk.ijse.waytosuccess.Entity.User;
import lk.ijse.waytosuccess.dao.SuperDAO;

import java.sql.SQLException;

public interface ChangePasswordDAO extends SuperDAO {
    boolean userVerified(String username) throws SQLException;
    boolean update(User user) throws SQLException;
}
