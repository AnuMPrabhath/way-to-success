package lk.ijse.waytosuccess.bo.custom;

import lk.ijse.waytosuccess.bo.SuperBO;
import lk.ijse.waytosuccess.dto.UserDto;

import java.sql.SQLException;

public interface LoginPageBO extends SuperBO {
    boolean isUser(UserDto userDto) throws SQLException;
}
