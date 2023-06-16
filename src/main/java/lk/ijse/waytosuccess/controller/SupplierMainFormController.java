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
import javafx.scene.layout.AnchorPane;
import lk.ijse.waytosuccess.bo.BOFactory;
import lk.ijse.waytosuccess.bo.custom.SupplierBO;
import lk.ijse.waytosuccess.dto.SupplierDto;
import lk.ijse.waytosuccess.dto.tm.SupplierTM;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupplierMainFormController implements Initializable {

    @FXML
    private AnchorPane supplierForm;

    @FXML
    private JFXTextField txtSupplierId;

    @FXML
    private JFXTextField txtSupplierName;

    @FXML
    private JFXTextField txtSupplierCompany;

    @FXML
    private JFXTextField txtSupplierContact;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private TableColumn<SupplierTM, String> colSupplierId;

    @FXML
    private TableColumn<SupplierTM, String> colSupplierName;

    @FXML
    private TableColumn<SupplierTM, String> colSupplierCompany;

    @FXML
    private TableColumn<SupplierTM, String> colSupplierContact;

    @FXML
    private JFXTextField txtSearchSupplier;

    @FXML
    private JFXButton btnAddSupplier;

    @FXML
    private JFXButton btnUpdateSupplier;

    @FXML
    private JFXButton btnDeleteSupplier;

    @FXML
    private JFXButton btnClearDetails;

    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    @FXML
    void addSupplier(ActionEvent event) throws SQLException {
        var supplier = new SupplierDto(txtSupplierId.getText(), txtSupplierName.getText(), txtSupplierCompany.getText(),
                txtSupplierContact.getText());

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Save?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            boolean isSaved = false;
            try {
                isSaved = supplierBO.addSupplier(supplier);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier saved!!!").show();
                clearDetails(event);
            }
        }
        setCellValueFactory(); getAll();
    }

    @FXML
    void clearDetails(ActionEvent event) {
        txtSupplierId.setText(null);
        txtSupplierName.setText(null);
        txtSupplierCompany.setText(null);
        txtSupplierContact.setText(null);
    }

    @FXML
    void deleteSupplier(ActionEvent event) throws SQLException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Delete?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            boolean isSaved = false;
            try {
                isSaved = supplierBO.deleteSupplier(txtSupplierId.getText());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Deleted!!!").show();
                clearDetails(event);
            }
        }
        setCellValueFactory(); getAll();
    }

    @FXML
    void searchSupplier(KeyEvent event) throws SQLException {
        List<SupplierDto> filterSuppList = null;
        try {
            filterSuppList = supplierBO.searchSupplier(txtSearchSupplier.getText());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<SupplierTM> obList = FXCollections.observableArrayList();

        for(SupplierDto supplierDto : filterSuppList) {
            obList.add(new SupplierTM(
                    supplierDto.getSupp_id(),
                    supplierDto.getSupp_name(),
                    supplierDto.getCompany(),
                    supplierDto.getContact()
            ));
        }
        tblSupplier.setItems(obList);
    }

    @FXML
    void updateSupplier(ActionEvent event) throws SQLException {
        var supplier = new SupplierDto(txtSupplierId.getText(), txtSupplierName.getText(), txtSupplierCompany.getText(),
                txtSupplierContact.getText());

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Update?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            boolean isUpdated = false;
            try {
                isUpdated = supplierBO.updateSupplier(supplier);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Updated!!!").show();
                clearDetails(event);
            }
        }
        setCellValueFactory(); getAll();
    }

    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }
    void setCellValueFactory() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supp_id"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("supp_name"));
        colSupplierCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colSupplierContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }
    void getAll() {
        try {
            ObservableList<SupplierTM> obList = FXCollections.observableArrayList();
            List<SupplierDto> suppList = null;
            try {
                suppList = supplierBO.getAllSuppliers();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            for(SupplierDto supplier : suppList) {
                obList.add(new SupplierTM(
                        supplier.getSupp_id(),
                        supplier.getSupp_name(),
                        supplier.getCompany(),
                        supplier.getContact()
                ));
            }
            tblSupplier.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    @FXML
    void clickTblCol(MouseEvent event) {
        SupplierTM selectedItem = tblSupplier.getSelectionModel().getSelectedItem();

        txtSupplierId.setText(selectedItem.getSupp_id());
        txtSupplierName.setText(selectedItem.getSupp_name());
        txtSupplierContact.setText(selectedItem.getContact());
        txtSupplierCompany.setText(selectedItem.getCompany());
    }
}
