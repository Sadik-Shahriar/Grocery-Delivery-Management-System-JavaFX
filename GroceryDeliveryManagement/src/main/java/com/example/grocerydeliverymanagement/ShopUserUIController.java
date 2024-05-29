package com.example.grocerydeliverymanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ShopUserUIController {
    @FXML
    private Label shopLabel;
    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField stockField;

    @FXML
    private ListView<String> productListView;

    @FXML
    private ListView<String> receiptListView;

    private Shop shop;
    private DataManager dataManager;
    private Stage stage;
    private Stage loginStage;
    private ObservableList<String> productList;
    private ObservableList<String> receiptList;

    public void initialize() {
        productList = FXCollections.observableArrayList();
        productListView.setItems(productList);

        receiptList = FXCollections.observableArrayList();
        receiptListView.setItems(receiptList);

        productListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setStages(Stage stage, Stage loginStage) {
        this.stage = stage;
        this.loginStage = loginStage;
        Image icon  = new Image("logo.png");
        stage.getIcons().add(icon);
    }

    public void setUser(Shop user) {
        dataManager.loginShop(user);      //called this method to fix shop null exception.
        for (Shop s : dataManager.getShops()) {
            if (s.getUsername().equals(user.getUsername())) {
                this.shop = s;
                shopLabel.setText(shop.getShopName()+" -Manager");
                break;
            }
        }
    }

    @FXML
    private void handleAddProduct() {
        if(nameField.getText().isEmpty()){
            showAlert("Please Enter Product Name!!!!");
            return;
        }
        if(priceField.getText().isEmpty()){
            showAlert("Please Enter Product Price");
            return;
        }
        if(stockField.getText().isEmpty()){
            showAlert("Please Enter the Stock Amount of Product.");
            return;
        }
        try{
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());
            Product product = new Product(name, price, stock);
            shop.addProduct(product);
            dataManager.addShop(shop);
        }
        catch (Exception ex){
            showAlert("Enter the correct format of price and stocks.");
        }
        finally{
        refreshProductList();}
    }

    @FXML
    private void handleRemoveProduct() {
        int selectedIndex = productListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            String productName = shop.getProducts().get(selectedIndex).getName();
            shop.removeProduct(productName);
            dataManager.addShop(shop);

            refreshProductList();
        }
    }

    @FXML
    private void handleLogout() {
        stage.close();
        loginStage.show();
    }

    public void show() {
        refreshProductList();
        refreshReceiptList();
        stage.show();
    }

    private void refreshProductList() {
        productList.clear();
        for (Product product : shop.getProducts()) {
            productList.add(product.toString());
        }
    }

    private void refreshReceiptList() {
        receiptList.clear();
        for (Receipt receipt : dataManager.getReceiptsForShop(shop.getShopName())) {
            receiptList.add(receipt.toString());
        }
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}

