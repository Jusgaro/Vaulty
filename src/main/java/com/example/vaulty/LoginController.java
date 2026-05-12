package com.example.vaulty;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    @FXML
    protected void onLoginClick() throws IOException{
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("The username and password need to be filled.");
            return;
        }

        if (UserStorage.verifyPassword(username, password)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
            Scene mainScene = new Scene(loader.load());
            mainScene.getStylesheets().add(App.class.getResource("styles.css").toExternalForm());
            MainController controller = loader.getController();
            controller.initData(username, password);
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(mainScene);
            stage.setTitle("Vaulty");

        } else {
            messageLabel.setStyle("-fx-text-fill: #ef2121");
            messageLabel.setText("Username or password is incorrect.");
        }
    }

    @FXML
    protected void onRegisterClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register-view.fxml"));
        Stage stage = (Stage) usernameField.getScene().getWindow();
        Scene scene = new Scene(loader.load(), 360, 250);
        scene.getStylesheets().add(getClass().getResource("/com/example/vaulty/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Vaulty - Registrácia");
    }



}
