package com.example.grocerydeliverymanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginUIController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private DataManager dataManager;
    private Stage primaryStage;

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Image icon  = new Image("logo.png");
        primaryStage.getIcons().add(icon);
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user=dataManager.authenticate(username,password);
        if (user!=null) {
            if (user instanceof Shop){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ShopUserUI.fxml"));
                    Parent root = loader.load();

                    ShopUserUIController controller = loader.getController();
                    controller.setDataManager(dataManager);
                    Stage stage = new Stage();
                    controller.setStages(stage, primaryStage);
                    controller.setUser((Shop) user);

                    stage.setTitle("Manager Interface - " + ((Shop) user).getShopName());
                    stage.setScene(new Scene(root));
                    controller.show();
                    primaryStage.hide();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (user instanceof Customer) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ShopSelectionUI.fxml"));
                    Parent root = loader.load();

                    ShopSelectionUIController controller = loader.getController();
                    controller.setCustomer((Customer) user);
                    controller.setDataManager(dataManager);
                    Stage stage = new Stage();
                    controller.setStages(stage, primaryStage);

                    stage.setTitle("Select Shop");
                    stage.setScene(new Scene(root));
                    controller.show();
                    primaryStage.hide();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid credentials!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void handleCreateAccount() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountCreationUI.fxml"));
            Parent root = loader.load();

            AccountCreationUIController controller = loader.getController();
            controller.setDataManager(dataManager);
            Stage stage = new Stage();
            controller.setStages(stage, primaryStage);

            stage.setTitle("Create Account");
            stage.setScene(new Scene(root));
            stage.show();
            primaryStage.hide();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
