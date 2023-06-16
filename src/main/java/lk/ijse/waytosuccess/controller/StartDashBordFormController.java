package lk.ijse.waytosuccess.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;
import lk.ijse.waytosuccess.bo.BOFactory;
import lk.ijse.waytosuccess.bo.custom.*;
import lk.ijse.waytosuccess.model.*;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

public class StartDashBordFormController implements Initializable {

    @FXML
    private Label lblTime;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblCudCount;

    @FXML
    private Label lblSupCount;

    @FXML
    private Label lblEmpCount;

    @FXML
    private Label lblOrderCount;

    @FXML
    private Label lblItemCount;

    private String comDate;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);
    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);
    PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACE_ORDER);

    private void initClock() {
        lblDate.setText(String.valueOf(LocalDate.now()));
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
                event -> lblTime.setText("  "+new SimpleDateFormat("hh:mm:ss").format(Calendar.getInstance().getTime()))),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTotalEmployee();
        setTotalCustomer();
        setTotalSupplier();
        setTotalOrder();
        setTotalItem();
        initClock();
    }

    void setTotalEmployee() throws SQLException {
        try {
            lblEmpCount.setText(String.valueOf((employeeBO.getAllEmployee()).size()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    void setTotalCustomer() throws SQLException {
        try {
            lblCudCount.setText(String.valueOf((customerBO.getAllCustomers()).size()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    void setTotalSupplier() throws SQLException {
        try {
            lblSupCount.setText(String.valueOf((supplierBO.getAllSuppliers()).size()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    void setTotalOrder() throws SQLException {
        lblOrderCount.setText(String.valueOf((placeOrderBO.getIds()).size()));
    }
    void setTotalItem() throws SQLException {
        try {
            lblItemCount.setText(String.valueOf(itemBO.getAllItems().size()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
