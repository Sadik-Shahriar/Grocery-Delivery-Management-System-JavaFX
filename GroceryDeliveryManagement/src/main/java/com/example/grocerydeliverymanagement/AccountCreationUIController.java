package com.example.grocerydeliverymanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AccountCreationUIController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private ComboBox<String> userTypeComboBox;
    @FXML
    private TextField shopNameField;
    @FXML
    private TextField phoneNumberField;

    private DataManager dataManager;
    private Stage stage;
    private Stage loginStage;

    public void initialize(){
        userTypeComboBox.getItems().addAll("Customer","Shop Manager");
        userTypeComboBox.setOnAction(e->{
            if("Shop Manager".equals(userTypeComboBox.getValue())){
                shopNameField.setDisable(false);
            }
            else shopNameField.setDisable(true);
        });
    }

    public void setDataManager(DataManager DataManager) {
        this.dataManager = DataManager;
    }

    public void setStages(Stage stage, Stage loginStage) {
        this.stage = stage;
        this.loginStage = loginStage;
        Image icon  = new Image("logo.png");
        stage.getIcons().add(icon);
    }

    @FXML
    private void handleCreateAccount() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String userType = userTypeComboBox.getValue();
        String shopName = shopNameField.getText();
        String phoneNumber=phoneNumberField.getText();

        if (userType == null) {
            showAlert("Please select an account type.");
            return;
        }
        if(username.isEmpty()){
            showAlert("Please enter Username");
            return;
        }
        if(password.isEmpty()){
            showAlert("Please enter Password");
            return;
        }
        if(!(password.equals(confirmPassword))){
            showAlert("Passwords do not match.");
            return;
        }
        if(phoneNumber.isEmpty()){
            showAlert("Please enter phone number");
            return;
        }
        if(dataManager.checkDuplicateAccount(username)){
            showAlert("Account already exists.");
            return;
        }
        else if(dataManager.checkDuplicateShop(shopName)){
            showAlert("Shop name taken.");
            return;
        }
        else{
            if ("Shop Manager".equals(userType)) {
                dataManager.addUser(new Shop(username,password,phoneNumber,shopName));
            } else if ("Customer".equals(userType)) {
                dataManager.addUser(new Customer(username,password,phoneNumber));
            }}

        stage.close();
        loginStage.show();
    }
    @FXML
    private void handleLogout() {
        stage.close();
        loginStage.show();
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}