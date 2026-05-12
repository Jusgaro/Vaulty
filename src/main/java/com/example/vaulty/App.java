package com.example.vaulty;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 360, 250);
        stage.setResizable(false);
        stage.setTitle("Vaulty");
        stage.setScene(scene);
        scene.getStylesheets().add(App.class.getResource("styles.css").toExternalForm());
        stage.show();

    }
}
