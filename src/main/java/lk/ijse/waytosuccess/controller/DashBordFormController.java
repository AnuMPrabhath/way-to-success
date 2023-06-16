package lk.ijse.waytosuccess.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashBordFormController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton LogoutBtn;

    @FXML
    private JFXButton mainDashBoardBtn;

    @FXML
    private JFXButton mainCustomerBtn;

    @FXML
    private JFXButton mainPlaceOrderBtn;

    @FXML
    private JFXButton mainReturnBtn;

    @FXML
    private JFXButton mainEmployeeBtn;

    @FXML
    private JFXButton mainSupplierBtn;

    @FXML
    private JFXButton mainItemBtn;

    @FXML
    private JFXButton mainServiceBtn;

    @FXML
    private AnchorPane rootChange;

    @FXML
    void openCustomerDashBord(ActionEvent event) throws IOException {
        setColor(); mainCustomerBtn.setStyle("-fx-background-color: #8EA7E9");
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/CustomerMainForm.fxml"));
        rootChange.getChildren().clear();
        rootChange.getChildren().add(anchorPane);
    }

    @FXML
    void openEmployeeForm(ActionEvent event) throws IOException {
        setColor(); mainEmployeeBtn.setStyle("-fx-background-color: #8EA7E9");
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/EmployeeMainForm.fxml"));
        rootChange.getChildren().clear();
        rootChange.getChildren().add(anchorPane);
    }

    @FXML
    void openDashboardForm(ActionEvent event) throws IOException {
        setColor(); mainDashBoardBtn.setStyle("-fx-background-color: #8EA7E9");
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/StartDashBordForm.fxml"));
        rootChange.getChildren().clear();
        rootChange.getChildren().add(anchorPane);
    }

    @FXML
    void openItemForm(ActionEvent event) throws IOException {
        setColor(); mainItemBtn.setStyle("-fx-background-color: #8EA7E9");
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ItemForm.fxml"));
        rootChange.getChildren().clear();
        rootChange.getChildren().add(anchorPane);
    }

    @FXML
    void openPlaceOrderForm(ActionEvent event) throws IOException {
        setColor(); mainPlaceOrderBtn.setStyle("-fx-background-color: #8EA7E9");
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/OrderForm.fxml"));
        rootChange.getChildren().clear();
        rootChange.getChildren().add(anchorPane);
    }

    @FXML
    void openReturnItemForm(ActionEvent event) throws IOException {
        setColor(); mainReturnBtn.setStyle("-fx-background-color: #8EA7E9");
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ReturnItemForm.fxml"));
        rootChange.getChildren().clear();
        rootChange.getChildren().add(anchorPane);
    }

    @FXML
    void openSupplierForm(ActionEvent event) throws IOException {
        setColor(); mainSupplierBtn.setStyle("-fx-background-color: #8EA7E9");
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/SupplierMainForm.fxml"));
        rootChange.getChildren().clear();
        rootChange.getChildren().add(anchorPane);
    }

    @FXML
    void openServiceForm(ActionEvent event) throws IOException {
        setColor(); mainServiceBtn.setStyle("-fx-background-color: #8EA7E9");
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ServiceForm.fxml"));
        rootChange.getChildren().clear();
        rootChange.getChildren().add(anchorPane);
    }

    @FXML
    void logoutAccount(ActionEvent event) throws IOException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/LoginPageForm.fxml"));
            Scene scene = new Scene(anchorPane);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        }
    }

    void setColor(){
        mainCustomerBtn.setStyle("-fx-background-color: null");
        mainPlaceOrderBtn.setStyle("-fx-background-color: null");
        mainDashBoardBtn.setStyle("-fx-background-color: null");
        mainReturnBtn.setStyle("-fx-background-color: null");
        mainEmployeeBtn.setStyle("-fx-background-color: null");
        mainSupplierBtn.setStyle("-fx-background-color: null");
        mainItemBtn.setStyle("-fx-background-color: null");
        mainServiceBtn.setStyle("-fx-background-color: null");
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setColor(); mainDashBoardBtn.setStyle("-fx-background-color: #8EA7E9");
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/StartDashBordForm.fxml"));
        rootChange.getChildren().clear();
        rootChange.getChildren().add(anchorPane);
    }
}
