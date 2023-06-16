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
import javafx.scene.input.MouseEvent;
import lk.ijse.waytosuccess.DB.DBConnection;
import lk.ijse.waytosuccess.bo.BOFactory;
import lk.ijse.waytosuccess.bo.custom.CustomerBO;
import lk.ijse.waytosuccess.dto.CustomerDto;
import lk.ijse.waytosuccess.dto.tm.CustomerTM;
import lk.ijse.waytosuccess.util.Regex;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerMainFormController implements Initializable {
    @FXML
    public DatePicker txtCustomerDate;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtCustomerContact;

    @FXML
    private JFXTextField txtCustomerAddress;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TableColumn<CustomerTM, String> colCustomerId;

    @FXML
    private TableColumn<CustomerTM, String> colCustomerName;

    @FXML
    private TableColumn<CustomerTM, String> colContact;

    @FXML
    private TableColumn<CustomerTM, String> colAddress;

    @FXML
    private TableColumn<CustomerTM, String> colDate;

    @FXML
    private TableColumn<CustomerTM, String> colAction;

    @FXML
    private JFXTextField txtSearchCustomer;

    @FXML
    private JFXButton addCustomerBtn;

    @FXML
    private JFXButton updateCustomerBtn;

    @FXML
    private JFXButton deleteCustomerBtn;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    @FXML
    void addCustomer(ActionEvent event) throws SQLException {
        if (Regex.validateCustomerCID(txtCustomerId.getText()) && Regex.validateMobile(txtCustomerContact.getText())) {
            var customer = new CustomerDto(txtCustomerId.getText(), txtCustomerName.getText(), txtCustomerContact.getText(),
                    txtCustomerAddress.getText(), txtCustomerDate.getValue().toString());

            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Save?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                boolean isSaved = false;
                try {
                    isSaved = customerBO.addCustomer(customer);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer saved!!!").show();
                    clearTextField(event);
                }
            }
            setCellValueFactory();
            getAll();
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Input!").show();
        }
    }

    @FXML
    void deleteCustomer(ActionEvent event) throws SQLException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Delete?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            boolean isSaved = false;
            try {
                isSaved = customerBO.deleteCustomer(txtCustomerId.getText());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted!!!").show();
                clearTextField(event);
            }
        }
        setCellValueFactory(); getAll();
    }

    @FXML
    void updateCustomer(ActionEvent event) throws SQLException {
        var customer = new CustomerDto(txtCustomerId.getText(), txtCustomerName.getText(), txtCustomerContact.getText(),
                txtCustomerAddress.getText(), txtCustomerDate.getValue().toString());

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Update?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            boolean isUpdated = false;
            try {
                isUpdated = customerBO.updateCustomer(customer);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated!!!").show();
                clearTextField(event);
            }
        }
        setCellValueFactory(); getAll();
    }

    @FXML
    void clearTextField(ActionEvent event) {
        txtCustomerId.setText(null);
        txtCustomerName.setText(null);
        txtCustomerContact.setText(null);
        txtCustomerAddress.setText(null);
        txtCustomerDate.setPromptText("Date");
    }

    @FXML
    void getReport(ActionEvent event) throws SQLException, JRException {
        try {
            JasperDesign design = JRXmlLoader.load(new File("F:\\2nd semester\\Architector\\MVC Final Project Convert Leyered\\codes\\src\\main\\resources\\jspReport\\CustomerReport.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DBConnection.getInstance().getConnection());
//            JasperPrintManager.printReport(jasperPrint, true);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }
    void setCellValueFactory() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("cust_id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("cust_name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
    void getAll() {
        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
        List<CustomerDto> cusList = null;
        try {
            cusList = customerBO.getAllCustomers();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for(CustomerDto customer : cusList) {
            obList.add(new CustomerTM(
                    customer.getCust_id(),
                    customer.getCust_name(),
                    customer.getContact(),
                    customer.getAddress(),
                    customer.getDate()
            ));
        }
        tblCustomer.setItems(obList);
    }

    @FXML
    public void searchCustomer(javafx.scene.input.KeyEvent keyEvent) throws SQLException {
        List<CustomerDto> filterCustList = null;
        try {
            filterCustList = customerBO.searchCustomer(txtSearchCustomer.getText());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();

        for(CustomerDto customer : filterCustList) {
            obList.add(new CustomerTM(
                    customer.getCust_id(),
                    customer.getCust_name(),
                    customer.getContact(),
                    customer.getAddress(),
                    customer.getDate()
            ));
        }
        tblCustomer.setItems(obList);
    }

    @FXML
    void clickTblCol(MouseEvent event) {
        CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();

        txtCustomerId.setText(selectedItem.getCust_id());
        txtCustomerName.setText(selectedItem.getCust_name());
        txtCustomerContact.setText(selectedItem.getContact());
        txtCustomerAddress.setText(selectedItem.getAddress());
        txtCustomerDate.setValue(LocalDate.parse(selectedItem.getDate()));
    }

    @FXML
    void txtCustIdOnAction(ActionEvent event) {
        txtCustomerName.requestFocus();
    }
    @FXML
    void txtCustNameOnAction(ActionEvent event) {
        txtCustomerContact.requestFocus();
    }
    @FXML
    void txtCustContactOnAction(ActionEvent event) {
        txtCustomerAddress.requestFocus();
    }
    @FXML
    void txtCustAddressOnAction(ActionEvent event) {
        addCustomerBtn.fire();
    }
    @FXML
    void txtCustDateOnAction(ActionEvent event) throws SQLException {
        txtCustomerId.requestFocus();
    }
}
