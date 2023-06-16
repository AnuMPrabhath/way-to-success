package lk.ijse.waytosuccess.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.waytosuccess.bo.BOFactory;
import lk.ijse.waytosuccess.bo.custom.ServiceBO;
import lk.ijse.waytosuccess.dto.ServiceDto;
import lk.ijse.waytosuccess.dto.tm.ServiceTM;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ServiceFormController implements Initializable {

    @FXML
    private JFXTextField txtServiceCharge;

    @FXML
    private JFXTextField txtServiceDsc;

    @FXML
    private JFXTextField txtServiceCode;

    @FXML
    private TableView<ServiceTM> tblService;

    @FXML
    private TableColumn<ServiceTM, String> colServiceCode;

    @FXML
    private TableColumn<ServiceTM, String> colDsc;

    @FXML
    private TableColumn<ServiceTM, Double> colServiceCharge;

    @FXML
    private JFXTextField txtSearchService;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnClearDetails;

    ServiceBO serviceBO = (ServiceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SERVICE);

    @FXML
    void addService(ActionEvent event) throws SQLException {
        var service = new ServiceDto(txtServiceCode.getText(), txtServiceDsc.getText(),
                Double.parseDouble(txtServiceCharge.getText()));

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Save?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            boolean isSaved = false;
            try {
                isSaved = serviceBO.addService(service);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer saved!!!").show();
                clearDetails(event);
            }
        }
    }

    @FXML
    void clearDetails(ActionEvent event) {
        txtServiceCode.setText(null);
        txtServiceDsc.setText(null);
        txtServiceCharge.setText(null);
    }

    @FXML
    void deleteService(ActionEvent event) throws SQLException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Delete?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            boolean isSaved = false;
            try {
                isSaved = serviceBO.deleteService(txtServiceCode.getText());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted!!!").show();
                clearDetails(event);
            }
        }
    }

    @FXML
    void searchService(KeyEvent event) throws SQLException {
        List<ServiceDto> filterServiceList = null;
        try {
            filterServiceList = serviceBO.searchService(txtSearchService.getText());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<ServiceTM> obList = FXCollections.observableArrayList();

        for(ServiceDto service : filterServiceList) {
            obList.add(new ServiceTM(
                    service.getService_code(),
                    service.getDescription(),
                    service.getService_charge()
            ));
        }
        tblService.setItems(obList);
    }

    @FXML
    void updateService(ActionEvent event) throws SQLException {
        ServiceDto service = new ServiceDto(txtServiceCode.getText(), txtServiceDsc.getText(), Double.parseDouble(txtServiceCharge.getText()));

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Update?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            boolean isUpdated = false;
            try {
                isUpdated = serviceBO.updateService(service);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated!!!").show();
                clearDetails(event);
            }
        }
    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }
    void setCellValueFactory() {
        colServiceCode.setCellValueFactory(new PropertyValueFactory<>("service_code"));
        colDsc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colServiceCharge.setCellValueFactory(new PropertyValueFactory<>("service_charge"));
    }
    void getAll() {
        try {
            List<ServiceDto> suppList = null;
            try {
                suppList = serviceBO.getAllServices();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ObservableList<ServiceTM> obList = FXCollections.observableArrayList();

            for(ServiceDto service : suppList) {
                obList.add(new ServiceTM(
                        service.getService_code(),
                        service.getDescription(),
                        service.getService_charge()
                ));
            }
            tblService.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    @FXML
    void clickTblCol(MouseEvent event) {
        ServiceTM selectedItem = tblService.getSelectionModel().getSelectedItem();

        txtServiceCode.setText(selectedItem.getService_code());
        txtServiceDsc.setText(selectedItem.getDescription());
        txtServiceCharge.setText(String.valueOf(selectedItem.getService_charge()));
    }
}
