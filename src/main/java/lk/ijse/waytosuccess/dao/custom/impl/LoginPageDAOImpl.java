package lk.ijse.waytosuccess.dao.custom.impl;

import lk.ijse.waytosuccess.Entity.User;
import lk.ijse.waytosuccess.dao.SQLUtil;
import lk.ijse.waytosuccess.dao.custom.LoginPageDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageDAOImpl implements LoginPageDAO {
    @Override
    public boolean isUser(User user) throws SQLException {
        String sql = "SELECT * FROM User WHERE username = ? AND password = ?;";

        ResultSet resultSet = SQLUtil.execute(sql, user.getUsername(), user.getPassword());

        if (resultSet.next()){
            return true;
        }
        return false;
    }
}
