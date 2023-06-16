package lk.ijse.waytosuccess.bo.custom.impl;

import lk.ijse.waytosuccess.Entity.User;
import lk.ijse.waytosuccess.bo.custom.LoginPageBO;
import lk.ijse.waytosuccess.dao.DAOFactory;
import lk.ijse.waytosuccess.dao.custom.LoginPageDAO;
import lk.ijse.waytosuccess.dto.UserDto;

import java.sql.SQLException;

public class LoginPageBOImpl implements LoginPageBO {

    LoginPageDAO loginPageDAO = (LoginPageDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOGIN_PAGE);

    @Override
    public boolean isUser(UserDto userDto) throws SQLException {
        return loginPageDAO.isUser(new User(userDto.getUsername(), userDto.getPassword()));
    }
}
