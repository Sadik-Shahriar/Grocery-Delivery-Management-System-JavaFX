package com.example.grocerydeliverymanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    private DataManager dataManager;

    public Main() {
        dataManager = new DataManager();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginUI.fxml"));
        Parent root = loader.load();

        LoginUIController controller = loader.getController();
        controller.setDataManager(dataManager);
        controller.setPrimaryStage(primaryStage);
        Image icon  = new Image("logo.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

