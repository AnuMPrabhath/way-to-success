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
import lk.ijse.waytosuccess.bo.custom.EmployeeBO;
import lk.ijse.waytosuccess.dto.EmployeeDto;
import lk.ijse.waytosuccess.dto.tm.EmployeeTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeMainFormController implements Initializable {

    @FXML
    private JFXTextField txtEmpId;

    @FXML
    private JFXTextField txtEmpName;

    @FXML
    private JFXTextField txtEmpContact;

    @FXML
    private JFXTextField txtEmpAddress;

    @FXML
    private DatePicker txtEmpDate;

    @FXML
    private TableView<EmployeeTM> tblEmployee;

    @FXML
    private TableColumn<EmployeeTM, String> colEmpId;

    @FXML
    private TableColumn<EmployeeTM, String> colEmpName;

    @FXML
    private TableColumn<EmployeeTM, String> colEmpContact;

    @FXML
    private TableColumn<EmployeeTM, String> colEmpAddress;

    @FXML
    private TableColumn<EmployeeTM, String> colEmpDate;

    @FXML
    private JFXTextField txtSearchEmployee;

    @FXML
    private JFXButton btnAddEmp;

    @FXML
    private JFXButton btnUpdateEmp;

    @FXML
    private JFXButton btnDeleteEmp;

    @FXML
    private JFXButton btnClearDetails;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    @FXML
    void addEmployee(ActionEvent event) throws SQLException {
        var employee = new EmployeeDto(txtEmpId.getText(), txtEmpName.getText(), txtEmpContact.getText(),
                txtEmpAddress.getText(), txtEmpDate.getValue().toString());

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Save?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            boolean isSaved = false;
            try {
                isSaved = employeeBO.addEmployee(employee);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!!!").show();
                clearDetails(event);
            }
        }
        setCellValueFactory(); getAll();
    }

    @FXML
    void clearDetails(ActionEvent event) {
        txtEmpId.setText(null);
        txtEmpName.setText(null);
        txtEmpContact.setText(null);
        txtEmpAddress.setText(null);
        txtEmpDate.setPromptText("Date");
    }

    @FXML
    void deleteEmployee(ActionEvent event) throws SQLException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Delete?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            boolean isSaved = false;
            try {
                isSaved = employeeBO.deleteEmployee(txtEmpId.getText());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Deleted!!!").show();
                clearDetails(event);
            }
        }
        setCellValueFactory(); getAll();
    }

    @FXML
    void searchEmployee(KeyEvent event) throws SQLException {
        List<EmployeeDto> filterEmpList = null;
        try {
            filterEmpList = employeeBO.searchEmployee(txtSearchEmployee.getText());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<EmployeeTM> obList = FXCollections.observableArrayList();

        for(EmployeeDto employeeDto : filterEmpList) {
            obList.add(new EmployeeTM(
                    employeeDto.getEmp_id(),
                    employeeDto.getEmp_name(),
                    employeeDto.getContact(),
                    employeeDto.getAddress(),
                    employeeDto.getDate()
            ));
        }
        tblEmployee.setItems(obList);
    }

    @FXML
    void updateEmployee(ActionEvent event) throws SQLException {
        var employee = new EmployeeDto(txtEmpId.getText(), txtEmpName.getText(), txtEmpContact.getText(),
                txtEmpAddress.getText(), txtEmpDate.getValue().toString());

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Update?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            boolean isUpdated = false;
            try {
                isUpdated = employeeBO.updateEmployee(employee);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Updated!!!").show();
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
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
        colEmpContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmpAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmpDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
    void getAll() {
        try {
            ObservableList<EmployeeTM> obList = FXCollections.observableArrayList();
            List<EmployeeDto> empList = null;
            try {
                empList = employeeBO.getAllEmployee();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            for(EmployeeDto employee : empList) {
                obList.add(new EmployeeTM(
                        employee.getEmp_id(),
                        employee.getEmp_name(),
                        employee.getContact(),
                        employee.getAddress(),
                        employee.getDate()
                ));
            }
            tblEmployee.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    @FXML
    void clickTblCol(MouseEvent event) {
        EmployeeTM selectedItem = tblEmployee.getSelectionModel().getSelectedItem();

        txtEmpId.setText(selectedItem.getEmp_id());
        txtEmpName.setText(selectedItem.getEmp_name());
        txtEmpContact.setText(selectedItem.getContact());
        txtEmpAddress.setText(selectedItem.getAddress());
        txtEmpDate.setValue(LocalDate.parse(selectedItem.getDate()));
    }
}
