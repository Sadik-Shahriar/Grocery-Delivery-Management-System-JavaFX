module com.example.grocerydeliverymanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.grocerydeliverymanagement to javafx.fxml;
    exports com.example.grocerydeliverymanagement;
}