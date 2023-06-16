package lk.ijse.waytosuccess.bo.custom.impl;

import lk.ijse.waytosuccess.Entity.User;
import lk.ijse.waytosuccess.bo.custom.ChangePasswordBO;
import lk.ijse.waytosuccess.dao.DAOFactory;
import lk.ijse.waytosuccess.dao.custom.ChangePasswordDAO;
import lk.ijse.waytosuccess.dto.SupplierDto;
import lk.ijse.waytosuccess.dto.UserDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class ChangePasswordBOImpl implements ChangePasswordBO {

    ChangePasswordDAO changePasswordDAO = (ChangePasswordDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CHANGE_PASSWORD);

    @Override
    public boolean userVerified(String username) throws SQLException {
        return changePasswordDAO.userVerified(username);
    }

    @Override
    public boolean update(UserDto user) throws SQLException {
        return changePasswordDAO.update(new User(user.getUsername(), user.getPassword()));
    }
}
