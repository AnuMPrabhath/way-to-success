package lk.ijse.waytosuccess.bo.custom;

import lk.ijse.waytosuccess.bo.SuperBO;
import lk.ijse.waytosuccess.dto.UserDto;

import java.sql.SQLException;

public interface ChangePasswordBO extends SuperBO {
    boolean userVerified(String username) throws SQLException;
    boolean update(UserDto user) throws SQLException;
}
