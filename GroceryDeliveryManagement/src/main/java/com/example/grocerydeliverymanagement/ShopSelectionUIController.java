package com.example.grocerydeliverymanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ShopSelectionUIController {
    @FXML
    private ListView<String> shopListView;

    private Customer customer;
    private DataManager dataManager;
    private Stage stage;
    private Stage loginStage;
    private ObservableList<String> shopList;

    public void initialize() {
        shopList = FXCollections.observableArrayList();
        shopListView.setItems(shopList);
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
        refreshShopList();
    }

    public void setStages(Stage stage, Stage loginStage) {
        this.stage = stage;
        this.loginStage = loginStage;
        Image icon  = new Image("logo.png");
        stage.getIcons().add(icon);
    }

    @FXML
    private void handleSelectShop() {
        int selectedIndex = shopListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Shop selectedShop = dataManager.getShops().get(selectedIndex);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerUI.fxml"));
                Parent root = loader.load();

                CustomerUIController controller = loader.getController();
                controller.setCustomer(customer);
                controller.setShop(selectedShop);
                controller.setDataManager(dataManager);
                Stage customerStage = new Stage();
                controller.setStages(customerStage, loginStage);

                customerStage.setTitle("Customer Interface - " + selectedShop.getShopName());
                customerStage.setScene(new Scene(root));
                controller.show();
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a shop.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void handleLogout() {
        stage.close();
        loginStage.show();
    }

    private void refreshShopList() {
        shopList.clear();
        for (Shop shop : dataManager.getShops()) {
            shopList.add(shop.getShopName());
        }
    }

    public void show() {
        stage.show();
    }
}
