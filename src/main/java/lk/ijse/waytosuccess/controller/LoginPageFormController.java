package lk.ijse.waytosuccess.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lk.ijse.waytosuccess.bo.BOFactory;
import lk.ijse.waytosuccess.bo.custom.LoginPageBO;
import lk.ijse.waytosuccess.dto.UserDto;

import java.io.IOException;
import java.sql.SQLException;

public class LoginPageFormController {
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField username;

    @FXML
    private Label changePassword;

    LoginPageBO loginPageBO = (LoginPageBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN_PAGE);

    public void changePassword(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent passwordRoot = FXMLLoader.load(getClass().getResource("/view/ChangePasswordForm.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Change Password");
        stage.centerOnScreen();
        stage.setScene(new Scene(passwordRoot));
        stage.show();
    }

    public void changeRedColor(javafx.scene.input.MouseEvent mouseEvent) {
        changePassword.setTextFill(Color.RED);
    }

    public void changeBlackColor(MouseEvent mouseEvent) {
        changePassword.setTextFill(Color.BLACK);
    }

    public void loginProcess(ActionEvent actionEvent) throws SQLException, IOException {

        boolean isUser = loginPageBO.isUser(new UserDto(username.getText(), password.getText()));

       if (isUser){
            System.out.println("Done");
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/DashBordForm.fxml"));
            Scene scene = new Scene(anchorPane);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Bicycle Work & Shop");
            stage.centerOnScreen();


        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }
    }
}
