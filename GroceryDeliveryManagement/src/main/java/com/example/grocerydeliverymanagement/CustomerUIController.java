package com.example.grocerydeliverymanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CustomerUIController {
    @FXML
    private Label shopLabel;
    @FXML
    private ListView<String> productListView;
    @FXML
    private ListView<String> receiptListView;
    @FXML
    private ListView<String> cartListView;

    private Customer customer;
    private Shop shop;
    private DataManager dataManager;
    private Stage stage;
    private Stage loginStage;
    private ObservableList<String> productList;
    private ObservableList<String> cartList;
    private ObservableList<String> receiptList;
    private double totalAmount = 0;

    public void initialize() {
        productList = FXCollections.observableArrayList();
        productListView.setItems(productList);

        cartList = FXCollections.observableArrayList();
        cartListView.setItems(cartList);
        receiptList = FXCollections.observableArrayList();
        receiptListView.setItems(receiptList);
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
        shopLabel.setText(shop.getShopName());
        refreshProductList();
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setStages(Stage stage, Stage loginStage) {
        this.stage = stage;
        this.loginStage = loginStage;
        Image icon = new Image("logo.png");
        stage.getIcons().add(icon);
    }

    @FXML
    private void handleAddToCart() {
        int selectedIndex = productListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Product selectedProduct = shop.getProducts().get(selectedIndex);
            int availableStock = selectedProduct.getStock();
            try {
                customer.setCart(new Product(selectedProduct.getName(), selectedProduct.getPrice(), 1), availableStock);
                refreshCartList();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Sorry, we have only " + availableStock + " units of " + selectedProduct.getName() + " in our store.", ButtonType.OK);
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a product to add to cart.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void handlePlaceOrder() {
        if (customer.getCart().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cart is empty. Add products to cart before placing an order.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        TextInputDialog addressDialog = new TextInputDialog();
        addressDialog.setTitle("Enter Delivery Address");
        addressDialog.setHeaderText("Enter Delivery Address");
        addressDialog.setContentText("Address:");
        String address = addressDialog.showAndWait().orElse("");

        if (address.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Delivery address cannot be empty.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Receipt receipt = new Receipt(shop.getShopName(), new ArrayList<>(customer.getCart()), customer.getUsername(), customer.getPhoneNumber(), address, totalAmount);
        dataManager.addReceipt(receipt);
        for (Product product : customer.getCart()) {
            shop.updateStock(product.getName(), product.getStock());
        }
        dataManager.addShop(shop);
        customer.clearCart();
        refreshCartList();
        refreshProductList();
        updateReceiptList();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order placed successfully!\n" + receipt, ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    private void handleLogout() {
        stage.close();
        loginStage.show();
    }

    private void refreshProductList() {
        productList.clear();
        for (Product product : shop.getProducts()) {
            productList.add(product.toString());
        }
    }

    private void refreshCartList() {
        cartList.clear();
        double totalCost = 0;
        for (Product product : customer.getCart()) {
            cartList.add(product.toString());
            totalCost += product.getPrice() * product.getStock();
        }
        cartList.add("Total Cost: BDT" + totalCost);
        setTotalAmount(totalCost);
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void show() {
        updateReceiptList();
        stage.show();
    }

    private void updateReceiptList() {
        receiptList.clear();
        for (Receipt receipt : dataManager.getReceiptsForCustomer(customer.getUsername())) {
            receiptList.add(receipt.toString());
        }
    }
}

