package lk.ijse.waytosuccess.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import lk.ijse.waytosuccess.bo.BOFactory;
import lk.ijse.waytosuccess.bo.custom.ChangePasswordBO;
import lk.ijse.waytosuccess.dto.UserDto;
import lk.ijse.waytosuccess.util.Regex;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ChangePasswordFormController implements Initializable {

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXButton btn;

    @FXML
    private JFXPasswordField txtNewPassword;

    @FXML
    private JFXPasswordField txtConfirmPassword1;

    @FXML
    private JFXButton btnNewPassShow;

    @FXML
    private JFXButton btnConfirmPassShow;

    private boolean confirmPasswordVisible;

    private boolean passwordVisible;

    ChangePasswordBO changePasswordBO = (ChangePasswordBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CHANGE_PASSWORD);

    private void toggleConfirmPasswordVisibility() {
        passwordVisible = !passwordVisible;
        txtConfirmPassword1.setManaged(false);
        txtConfirmPassword1.setVisible(false);
        txtConfirmPassword1.setManaged(true);
        txtConfirmPassword1.setVisible(true);
        if (passwordVisible) {
            txtConfirmPassword1.setPromptText(txtConfirmPassword1.getText());
            txtConfirmPassword1.setText("");
        } else {
            txtConfirmPassword1.setText(txtConfirmPassword1.getPromptText());
            txtConfirmPassword1.setPromptText("");
        }
    }

    private void togglePasswordVisibility() {
        passwordVisible = !passwordVisible;
        txtNewPassword.setManaged(false);
        txtNewPassword.setVisible(false);
        txtNewPassword.setManaged(true);
        txtNewPassword.setVisible(true);
        if (passwordVisible) {
            txtNewPassword.setPromptText(txtNewPassword.getText());
            txtNewPassword.setText("");
        } else {
            txtNewPassword.setText(txtNewPassword.getPromptText());
            txtNewPassword.setPromptText("");
        }
    }

    @FXML
    void changePassword(ActionEvent event) throws SQLException {
        String username = txtUserName.getText();
        String password = txtNewPassword.getText();
        String confirmPassword = txtConfirmPassword1.getText();

        if (!password.equals(confirmPassword)) {
            new Alert(Alert.AlertType.WARNING, "Passwords do not match!").show();
            return;
        }

        if (Regex.validatePassword(password)) {
            try {
                boolean isUserVerified = changePasswordBO.userVerified(username);
                if (isUserVerified) {
                    //var user = new UserDto(username, password);
                    boolean isUpdated = changePasswordBO.update(new UserDto(username, password));
                    if (isUpdated) {
                        new Alert(Alert.AlertType.INFORMATION, "Password reset successful!").show();
                        txtUserName.setText(null);
                        txtNewPassword.setText(null);
                        txtConfirmPassword1.setText(null);
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Oops something went wrong while updating password!").show();
                    }
                } else {
                    new Alert(Alert.AlertType.WARNING, "User Not Verified!").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Oops something wrong!").show();
            }
        }else {
            new Alert(Alert.AlertType.INFORMATION, "Hint : [aA-zZ0-9@]{8,20}").show();
            txtNewPassword.setText(null);
            txtConfirmPassword1.setText(null);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        confirmPasswordVisible = false;
        passwordVisible = false;
        btnNewPassShow.setOnAction(event -> togglePasswordVisibility());
        btnConfirmPassShow.setOnAction(event -> toggleConfirmPasswordVisibility());
    }

    public void showNewPassword(ActionEvent event) {

    }

    public void showConfirmPassword(ActionEvent event) {

    }
}
