package lk.ijse.waytosuccess.dao.custom.impl;

import lk.ijse.waytosuccess.Entity.User;
import lk.ijse.waytosuccess.dao.SQLUtil;
import lk.ijse.waytosuccess.dao.custom.ChangePasswordDAO;
import lk.ijse.waytosuccess.dto.UserDto;
import lk.ijse.waytosuccess.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangePasswordDAOImpl implements ChangePasswordDAO {
    @Override
    public boolean userVerified(String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ?";

        ResultSet resultSet = SQLUtil.execute(sql, username);

        if(resultSet.next()){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(User user) throws SQLException {
        String sql = "UPDATE user SET password = ? WHERE username = ?";
        return SQLUtil.execute(sql, user.getPassword(), user.getUsername());
    }
}
