package lk.ijse.waytosuccess.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import lk.ijse.waytosuccess.bo.BOFactory;
import lk.ijse.waytosuccess.bo.custom.CustomerBO;
import lk.ijse.waytosuccess.dto.CustomerDto;
import lk.ijse.waytosuccess.util.Regex;

import java.sql.SQLException;
import java.util.Optional;

public class AddCustomerFormController {

    @FXML
    private JFXTextField txtCustId;

    @FXML
    private JFXTextField txtCustAddress;

    @FXML
    private JFXTextField txtCustName;

    @FXML
    private JFXTextField txtCustContact;

    @FXML
    private DatePicker txtCustDate;

    @FXML
    private JFXButton btnCustClose;

    @FXML
    private JFXButton btnCustAdd;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    @FXML
    void CloseCustomerForm(ActionEvent event) {
        Stage window = (Stage)btnCustAdd.getScene().getWindow();
        window.close();
    }

    @FXML
    void saveNewCustomer(ActionEvent event) throws SQLException {
        if (Regex.validateCustomerCID(txtCustId.getText()) && Regex.validateMobile(txtCustContact.getText())) {
            //var customer = new CustomerDto(txtCustId.getText(), txtCustName.getText(), txtCustContact.getText(), txtCustAddress.getText(), txtCustDate.getValue().toString());

            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Save?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                boolean isSaved = false;
                try {
                    isSaved = customerBO.addCustomer(new CustomerDto(txtCustId.getText(), txtCustName.getText(), txtCustContact.getText(), txtCustAddress.getText(), txtCustDate.getValue().toString()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer saved!!!").show();
                }
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Input!").show();
        }
    }


}
