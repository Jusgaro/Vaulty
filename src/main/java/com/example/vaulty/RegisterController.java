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

        // Check for empty fields
        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            messageLabel.setText("Please fill in all fields!");
            return;
        }

        // Check if passwords match
        if (!password.equals(confirm)) {
            messageLabel.setText("Passwords do not match!");
            return;
        }

        // Check if username is taken
        if (UserStorage.userExists(username)) {
            messageLabel.setText("User already exists!");
            return;
        }

        // Save user and show success message
        UserStorage.saveUser(username, password);
        messageLabel.setStyle("-fx-text-fill: green;");
        messageLabel.setText("Account created! You can now log in.");
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
