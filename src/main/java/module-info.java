module com.example.vaulty {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.vaulty to javafx.fxml;
    exports com.example.vaulty;
}