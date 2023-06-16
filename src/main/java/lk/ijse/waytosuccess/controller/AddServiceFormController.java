package lk.ijse.waytosuccess.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import lk.ijse.waytosuccess.bo.BOFactory;
import lk.ijse.waytosuccess.bo.custom.ServiceBO;
import lk.ijse.waytosuccess.dto.ServiceDto;

import java.sql.SQLException;
import java.util.Optional;

public class AddServiceFormController {

    @FXML
    private JFXTextField txtServiceCharge;

    @FXML
    private JFXTextField txtServiceDsc;

    @FXML
    private JFXTextField txtServiceCode;

    @FXML
    private JFXButton btnServiceAdd;

    @FXML
    private JFXButton btnCloseForm;

    ServiceBO serviceBO = (ServiceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SERVICE);

    @FXML
    void CloseServiceForm(ActionEvent event) {
        Stage window = (Stage)btnServiceAdd.getScene().getWindow();
        window.close();
    }

    @FXML
    void saveNewService(ActionEvent event) throws SQLException {
        //var service = new ServiceDto(txtServiceCode.getText(), txtServiceDsc.getText(), Double.parseDouble(txtServiceCharge.getText()));

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Save?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            boolean isSaved = false;
            try {
                isSaved = serviceBO.addService(new ServiceDto(txtServiceCode.getText(), txtServiceDsc.getText(), Double.parseDouble(txtServiceCharge.getText())));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer saved!!!").show();
            }
        }
    }

}
