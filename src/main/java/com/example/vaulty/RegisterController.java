package com.example.vaulty;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class RegisterController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label messageLabel;

    @FXML
    protected void onRegisterClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirm = confirmPasswordField.getText();

        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            messageLabel.setText("Vyplň všetky polia!");
            return;
        }

        if (!password.equals(confirm)) {
            messageLabel.setText("Heslá sa nezhodujú!");
            return;
        }

        if (UserStorage.userExists(username)) {
            messageLabel.setText("Používateľ už existuje!");
            return;
        }

        UserStorage.saveUser(username, password);
        messageLabel.setStyle("-fx-text-fill: green;");
        messageLabel.setText("Účet vytvorený! Môžeš sa prihlásiť.");
    }

    @FXML
    protected void onBackClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Stage stage = (Stage) usernameField.getScene().getWindow();
        Scene scene = new Scene(loader.load(), 360, 250);
        scene.getStylesheets().add(App.class.getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Vaulty");
    }
}