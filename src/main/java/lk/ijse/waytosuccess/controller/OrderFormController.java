package lk.ijse.waytosuccess.controller;

import com.google.zxing.WriterException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lk.ijse.waytosuccess.DB.DBConnection;
import lk.ijse.waytosuccess.bo.BOFactory;
import lk.ijse.waytosuccess.bo.custom.*;
import lk.ijse.waytosuccess.dto.*;
import lk.ijse.waytosuccess.dto.tm.OrderItemTM;
import lk.ijse.waytosuccess.dto.tm.OrderServiceTM;
import lk.ijse.waytosuccess.model.*;
import lk.ijse.waytosuccess.qr.QrGenerator;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class OrderFormController implements Initializable {

    @FXML
    private JFXComboBox<String> comboItemCode;

    @FXML
    private Label txtItemType;

    @FXML
    private Label txtItemDsc;

    @FXML
    private Label txtItemUnitPrice;

    @FXML
    private Label txtQtyOnHand;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXButton btnItemAddToCart;

    @FXML
    private JFXButton btnNewService;

    @FXML
    private JFXComboBox<String> comboServiceCode;

    @FXML
    private Label txtServiceCharge;

    @FXML
    private Label txtServiceDsc;

    @FXML
    private JFXButton btnServiceAddToCart;

    @FXML
    private Label txtOrderId;

    @FXML
    private Label txtOrderDate;

    @FXML
    private Label txtCustName;

    @FXML
    private JFXComboBox<String> comboCustId;

    @FXML
    private JFXButton btnCustAdd;

    @FXML
    private TableView<OrderItemTM> tblItemCart;

    @FXML
    private TableColumn<OrderItemTM, String> colItemCode;

    @FXML
    private TableColumn<OrderItemTM, String> colItemType;

    @FXML
    private TableColumn<OrderItemTM, String> colItemDsc;

    @FXML
    private TableColumn<OrderItemTM, Double> colUnitPrice;

    @FXML
    private TableColumn<OrderItemTM, Integer> colQty;

    @FXML
    private TableColumn<OrderItemTM, Double> colTotal;

    @FXML
    private TableColumn<OrderItemTM, JFXButton> colItemAction;

    @FXML
    private TableView<OrderServiceTM> tblServiceCart;

    @FXML
    private TableColumn<OrderServiceTM, String> colServiceCode;

    @FXML
    private TableColumn<OrderServiceTM, String> colServiceDsc;

    @FXML
    private TableColumn<OrderServiceTM, Double> colCharge;

    @FXML
    private TableColumn<OrderServiceTM, JFXButton> colServiceAction;

    @FXML
    private Label txtNetTotal;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnQr;

    private ObservableList<OrderItemTM> obList = FXCollections.observableArrayList();
    private ObservableList<OrderServiceTM> serviceObList = FXCollections.observableArrayList();

    PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACE_ORDER);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);
    ServiceBO serviceBO = (ServiceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SERVICE);
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);

    @FXML
    void OrderServiceAddToCart(ActionEvent event) {
        String code = comboServiceCode.getValue();
        String dsc = txtServiceDsc.getText();
        double charge = Double.parseDouble(txtServiceCharge.getText());
        JFXButton btnSRemove = new JFXButton("Remove");
        btnSRemove.setStyle("-fx-background-color: #FDF7C3");

        setSRemoveBtnOnAction(btnSRemove);

        /*if (!tblServiceCart.getItems().isEmpty()) {
            for (int i = 0; i < tblServiceCart.getItems().size(); i++) {
                if (colServiceCode.getCellData(i).equals(code)) {
                    charge +=  colCharge.getCellData(i);

                    serviceObList.get(i).setService_charge(charge);

                    tblServiceCart.refresh();
                    calculateNetTotal();
                    return;
                }
            }
        }*/

        OrderServiceTM tm = new OrderServiceTM(code, dsc, charge, btnSRemove);

        serviceObList.add(tm);
        tblServiceCart.setItems(serviceObList);
        calculateNetTotal();
    }

    @FXML
    void addCustomer(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddCustomerForm.fxml"))));
        stage.show();
    }

    @FXML
    void addNewService(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddServiceForm.fxml"))));
        stage.show();
    }

    @FXML
    void orderItemAddToCart(ActionEvent event) {
        if (Integer.parseInt(txtQtyOnHand.getText()) > Integer.parseInt(txtQty.getText())) {
            String code = comboItemCode.getValue();
            String itemType = txtItemType.getText();
            String description = txtItemDsc.getText();
            double unitPrice = Double.parseDouble(txtItemUnitPrice.getText());
            int qty = Integer.parseInt(txtQty.getText());
            double total = qty * unitPrice;
            JFXButton btnRemove = new JFXButton("Remove");
            btnRemove.setStyle("-fx-background-color: #FDF7C3");

            setRemoveBtnOnAction(btnRemove);

            if (!tblItemCart.getItems().isEmpty()) {
                for (int i = 0; i < tblItemCart.getItems().size(); i++) {
                    if (colItemCode.getCellData(i).equals(code)) {
                        qty += (int) colQty.getCellData(i);
                        total = qty * unitPrice;

                        obList.get(i).setQty(qty);
                        obList.get(i).setTotal(total);

                        tblItemCart.refresh();
                        calculateNetTotal();
                        return;
                    }
                }
            }

            OrderItemTM tm = new OrderItemTM(code, itemType, description, unitPrice, qty, total, btnRemove);

            obList.add(tm);
            tblItemCart.setItems(obList);
            calculateNetTotal();
        }else {
            new Alert(Alert.AlertType.ERROR, "Out Of Stock!").show();
        }
    }

    private void calculateNetTotal() {
        Double netTotal = 0.0;
        for (int i = 0; i < tblItemCart.getItems().size(); i++) {
            Double total  = (Double) colTotal.getCellData(i);
            netTotal += total;
        }
        for (int i = 0; i < tblServiceCart.getItems().size(); i++) {
            Double total  = (Double) colCharge.getCellData(i);
            netTotal += total;
        }
        txtNetTotal.setText(String.valueOf(netTotal));
    }

    @FXML
    void showQr(ActionEvent event) {
        if (!txtNetTotal.getText().isEmpty()) {
            QrGenerator qrGenerator = new QrGenerator();
            qrGenerator.setData("Net Total : "+txtNetTotal.getText());
            try {
                QrGenerator.getGenerator();
            } catch (IOException | WriterException e) {
                new Alert(Alert.AlertType.ERROR, String.valueOf(e)).show();
            }
            File file = new File(qrGenerator.getPath());
            Image image = new Image(file.toURI().toString());
            QrScannerController.image = image;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/QrScanner.fxml"));
            try{
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Qr");
                stage.centerOnScreen();
                stage.setResizable(false);
                stage.show();
            }catch (IOException e){
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Input Data First! ").show();
        }
    }

    @FXML
    void placeOrder(ActionEvent event) throws JRException, SQLException {
        String oId = txtOrderId.getText();
        String cusId = comboCustId.getValue();

        List<CartItemDto> cartItemDTOList = new ArrayList<>();
        List<CartServiceDto> cartSDTOList = new ArrayList<>();

        for (int i = 0; i < tblItemCart.getItems().size(); i++) {
            OrderItemTM tm = obList.get(i);

            CartItemDto cartItemDTO = new CartItemDto(tm.getItem_code(), tm.getUnit_price(),tm.getQty());
            cartItemDTOList.add(cartItemDTO);
        }

        for (int i = 0; i < tblServiceCart.getItems().size(); i++) {
            OrderServiceTM tm = serviceObList.get(i);

            CartServiceDto cartsDTO = new CartServiceDto(tm.getService_code(), tm.getService_charge());
            cartSDTOList.add(cartsDTO);
        }

        boolean isPlaced = placeOrderBO.placeOrder(oId, cusId, cartItemDTOList, cartSDTOList);
        if(isPlaced) {
            new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Order Not Placed!").show();
        }
        calculateNetTotal();
        generateReceipt(oId);
    }

    private void generateReceipt(String oId) throws JRException, SQLException {
        JasperDesign jasDesign = JRXmlLoader.load("src/main/resources/jspReport/Bill.jrxml");
        JasperReport jasReport = JasperCompileManager.compileReport(jasDesign);

        Map<String,Object> data = new HashMap<>();
        data.put("orderId",oId);

        JasperPrint jasPrint = JasperFillManager.fillReport(jasReport, data, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasPrint,false);
    }

    @FXML
    void selectCustId(ActionEvent event) throws SQLException {
        String selectedItem = comboCustId.getSelectionModel().getSelectedItem();
        CustomerDto cust = customerBO.selectCustomerId(selectedItem);

        txtCustName.setText(cust.getCust_name());
    }

    @FXML
    void selectItemCode(ActionEvent event) throws SQLException {
        String selectedItem = String.valueOf(comboItemCode.getSelectionModel().getSelectedItem());
        ItemDto item = itemBO.selectItemCodes(selectedItem);

        txtItemType.setText(item.getItem_type());
        txtItemDsc.setText(item.getDescription());
        txtItemUnitPrice.setText(String.valueOf(item.getUnit_price()));
        txtQtyOnHand.setText(String.valueOf(item.getQty()));
    }

    @FXML
    void selectServiceCode(ActionEvent event) throws SQLException {
        String selectedService = comboServiceCode.getSelectionModel().getSelectedItem();
        ServiceDto service = serviceBO.selectService(selectedService);

        txtServiceDsc.setText(service.getDescription());
        txtServiceCharge.setText(String.valueOf(service.getService_charge()));
    }
    void selectCustId() throws SQLException {
        List<String> ids = customerBO.getIds();
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (String id : ids) {
            obList.add(id);
        }
        comboCustId.setItems(obList);
    }

    void selectItemCodes() throws SQLException {
        List<String> ids = itemBO.getIds();
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (String id : ids) {
            obList.add(id);
        }
        comboItemCode.setItems(obList);
    }

    void selectServiceCodes() throws SQLException {
        List<String> ids = serviceBO.getIds();
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (String id : ids) {
            obList.add(id);
        }
        comboServiceCode.setItems(obList);
    }

    private void setOrderIdAndDate() throws SQLException {
        String orderId = orderBO.generateNextOrderId();
        txtOrderId.setText(orderId);

        txtOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    private void setRemoveBtnOnAction(JFXButton button) {
        button.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {

                int index = tblItemCart.getSelectionModel().getSelectedIndex();
                obList.remove(index);

                tblItemCart.refresh();
                calculateNetTotal();
            }

        });
    }

    private void setSRemoveBtnOnAction(JFXButton button) {
        button.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {

                int index = tblServiceCart.getSelectionModel().getSelectedIndex();
                serviceObList.remove(index);

                tblServiceCart.refresh();
                calculateNetTotal();
            }

        });
    }
    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setOrderIdAndDate();
        selectCustId();
        selectItemCodes();
        selectServiceCodes();
        setCellValueFactory();
    }
    void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("item_code"));
        colItemType.setCellValueFactory(new PropertyValueFactory<>("item_type"));
        colItemDsc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colItemAction.setCellValueFactory(new PropertyValueFactory<>("button"));

        colServiceCode.setCellValueFactory(new PropertyValueFactory<>("service_code"));
        colServiceDsc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCharge.setCellValueFactory(new PropertyValueFactory<>("service_charge"));
        colServiceAction.setCellValueFactory(new PropertyValueFactory<>("button"));
    }
}
