package lk.ijse.waytosuccess.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.waytosuccess.bo.BOFactory;
import lk.ijse.waytosuccess.bo.custom.*;
import lk.ijse.waytosuccess.dto.*;
import lk.ijse.waytosuccess.model.*;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ReturnItemFormController implements Initializable {

    @FXML
    private Label lblReturnCode;

    @FXML
    private Label lblDate;

    @FXML
    private JFXComboBox<String> comboOrderId;

    @FXML
    private JFXComboBox<String> comboItemCode;

    @FXML
    private Label lblItemType;

    @FXML
    private Label lblItemDsc;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblQty;

    @FXML
    private JFXTextField txtReturnQty;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblCustId;

    @FXML
    private Label lblCustName;

    @FXML
    private JFXButton btnPlaceReturn;

    @FXML
    private Label lblCustContact;

    @FXML
    private Label lblCustAddress;

    static String selectedOrderId;

    ReturnItemBO returnItemBO = (ReturnItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RETURN_ITEM);
    OrderDetailBO orderDetailBO = (OrderDetailBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER_DETAIL);
    PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACE_ORDER);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    @FXML
    void placeReturnItem(ActionEvent event) throws SQLException {
        ReturnItemDto returnItemDto = new ReturnItemDto(lblReturnCode.getText(),
                comboItemCode.getSelectionModel().getSelectedItem(), Integer.parseInt(txtReturnQty.getText()),
                comboOrderId.getSelectionModel().getSelectedItem());

        boolean isPlaced = returnItemBO.placeReturn(returnItemDto);
        if(isPlaced) {
            new Alert(Alert.AlertType.CONFIRMATION, "Return Success!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Return Not Success!").show();
        }
    }

    @FXML
    void selectItemCode(ActionEvent event) throws SQLException {
        String selectItemCode = comboItemCode.getSelectionModel().getSelectedItem();
        Order_DetailsDto order_detailsDto = orderDetailBO.selectOrder(selectedOrderId);
        ItemDto itemDto = itemBO.selectItemCodes(selectItemCode);

        lblItemType.setText(itemDto.getItem_type());
        lblItemDsc.setText(itemDto.getDescription());
        lblUnitPrice.setText(String.valueOf(itemDto.getUnit_price()));
        lblQty.setText(String.valueOf(order_detailsDto.getQty()));
    }

    @FXML
    void selectOrderId(ActionEvent event) throws SQLException {
        selectedOrderId = comboOrderId.getSelectionModel().getSelectedItem();
        Place_orderDto place_orderDto = placeOrderBO.selectOrder(selectedOrderId);

        CustomerDto customerDto = customerBO.selectCustomerId(place_orderDto.getCust_id());

        lblOrderDate.setText(place_orderDto.getDate());
        lblCustId.setText(place_orderDto.getCust_id());
        lblCustName.setText(customerDto.getCust_name());
        lblCustContact.setText(customerDto.getContact());
        lblCustAddress.setText(customerDto.getAddress());

        setItemCodes(selectedOrderId);
    }

    void setItemCodes(String selectOrderId) throws SQLException {
        List<String> ids = orderDetailBO.getIds(selectOrderId);
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (String id : ids) {
            obList.add(id);
        }
        comboItemCode.setItems(obList);
    }

    private void setReturnCodeAndDate() throws SQLException {
        String orderId = returnItemBO.generateNextReturnCode();
        lblReturnCode.setText(orderId);

        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    void setOrderIds() throws SQLException {
        List<String> ids = placeOrderBO.getIds();
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (String id : ids) {
            obList.add(id);
        }
        comboOrderId.setItems(obList);
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setReturnCodeAndDate();
        setOrderIds();
    }
}
