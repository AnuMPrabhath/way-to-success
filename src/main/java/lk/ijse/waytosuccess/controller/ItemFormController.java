package lk.ijse.waytosuccess.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
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
import lk.ijse.waytosuccess.bo.custom.ItemBO;
import lk.ijse.waytosuccess.dto.ItemDto;
import lk.ijse.waytosuccess.dto.tm.ItemTM;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    @FXML
    private JFXTextField txtItemUnitPrice;

    @FXML
    private JFXTextField txtItemDsc;

    @FXML
    private JFXTextField txtItemQty;

    @FXML
    private JFXTextField txtItemType;

    @FXML
    private JFXRadioButton rbtnPart;

    @FXML
    private JFXRadioButton rbtnBicycle;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private TableView<ItemTM> tblItem;

    @FXML
    private TableColumn<ItemTM, String> colItemCode;

    @FXML
    private TableColumn<ItemTM, String> colItemType;

    @FXML
    private TableColumn<ItemTM, String> colItemDesc;

    @FXML
    private TableColumn<ItemTM, Double> colItemUnitPrice;

    @FXML
    private TableColumn<ItemTM, Integer> colItemQty;

    @FXML
    private JFXTextField txtSearchItem;

    @FXML
    private JFXButton btnAddItem;

    @FXML
    private JFXButton btnUpdateItem;

    @FXML
    private JFXButton btnDeleteItem;

    @FXML
    private JFXButton btnClearDetails;

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    @FXML
    void BicycleOnAction(ActionEvent event) {
        txtItemType.setText("Bicycle");
        rbtnPart.setSelected(false);
    }

    @FXML
    void addItem(ActionEvent event) throws SQLException {
        var item = new ItemDto(txtItemCode.getText(), txtItemType.getText(), txtItemDsc.getText(), Double.parseDouble(txtItemUnitPrice.getText()),
                Integer.parseInt(txtItemQty.getText()));

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Save?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            boolean isSaved = false;
            try {
                isSaved = itemBO.addItem(item);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer saved!!!").show();
                clearDetails(event);
            }
        }
        setCellValueFactory(); getAll();
    }

    @FXML
    void clearDetails(ActionEvent event) {
        txtItemCode.setText(null);
        txtItemType.setText(null);
        txtItemDsc.setText(null);
        txtItemUnitPrice.setText(null);
        txtItemQty.setText(null);
        rbtnBicycle.setSelected(false);
        rbtnPart.setSelected(false);
    }

    @FXML
    void deleteItem(ActionEvent event) throws SQLException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Delete?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            boolean isSaved = false;
            try {
                isSaved = itemBO.deleteItem(txtItemCode.getText());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted!!!").show();
                clearDetails(event);
            }
        }
        setCellValueFactory(); getAll();
    }

    @FXML
    void partOnAction(ActionEvent event) {
        txtItemType.setText("Spare Part");
        rbtnBicycle.setSelected(false);
    }

    @FXML
    void searchItem(KeyEvent event) throws SQLException {
        List<ItemDto> filterItemList = null;
        try {
            filterItemList = itemBO.searchItem(txtSearchItem.getText());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<ItemTM> obList = FXCollections.observableArrayList();

        for(ItemDto itemDto : filterItemList) {
            obList.add(new ItemTM(
                    itemDto.getItem_code(),
                    itemDto.getItem_type(),
                    itemDto.getDescription(),
                    itemDto.getUnit_price(),
                    itemDto.getQty()
            ));
        }
        tblItem.setItems(obList);
    }

    @FXML
    void updateItem(ActionEvent event) throws SQLException {
        var item = new ItemDto(txtItemCode.getText(), txtItemType.getText(), txtItemDsc.getText(), Double.parseDouble(txtItemUnitPrice.getText()),
                Integer.parseInt(txtItemQty.getText()));

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Update?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            boolean isUpdated = false;
            try {
                isUpdated = itemBO.updateItem(item);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated!!!").show();
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
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("Item_code"));
        colItemType.setCellValueFactory(new PropertyValueFactory<>("Item_type"));
        colItemDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("Unit_price"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
    }
    void getAll() {
        try {
            ObservableList<ItemTM> obList = FXCollections.observableArrayList();
            List<ItemDto> itemList = null;
            try {
                itemList = itemBO.getAllItems();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            for(ItemDto item : itemList) {
                obList.add(new ItemTM(
                        item.getItem_code(),
                        item.getItem_type(),
                        item.getDescription(),
                        item.getUnit_price(),
                        item.getQty()
                ));
            }
            tblItem.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    @FXML
    void clickTblCol(MouseEvent event) {
        ItemTM selectedItem = tblItem.getSelectionModel().getSelectedItem();

        txtItemCode.setText(selectedItem.getItem_code());
        txtItemType.setText(selectedItem.getItem_type());
        if(selectedItem.getItem_type().equals("Bicycle")){
            rbtnBicycle.setSelected(true);
            rbtnPart.setSelected(false);
        }else {rbtnPart.setSelected(true); rbtnBicycle.setSelected(false);}
        txtItemDsc.setText(selectedItem.getDescription());
        txtItemUnitPrice.setText(String.valueOf(selectedItem.getUnit_price()));
        txtItemQty.setText(String.valueOf(selectedItem.getQty()));
    }
}
