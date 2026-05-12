package com.example.vaulty;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {

    @FXML private Button btnPassword;
    @FXML private Button btnAddPassword;
    @FXML private StackPane contentArea;

    private String currentUser;
    private String masterPassword;
    private ObservableList<PasswordEntry> passwords = FXCollections.observableArrayList();

    public void initData(String username, String password) {
        this.currentUser = username;
        this.masterPassword = password;
        passwords = FXCollections.observableArrayList(
                PasswordStorage.load(username, password)
        );
    }

    private void setActiveButton(Button active) {
        String inactive = "-fx-background-color: transparent; -fx-text-fill: #7a6352; -fx-padding: 10 16; -fx-background-radius: 8; -fx-cursor: hand; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;";
        String activeStyle = "-fx-background-color: #C4A882; -fx-text-fill: #4a3728; -fx-padding: 10 16; -fx-background-radius: 8; -fx-cursor: hand; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;";
        btnPassword.setStyle(inactive);
        btnAddPassword.setStyle(inactive);
        active.setStyle(activeStyle);
    }

    @FXML
    protected void showPassword() {
        setActiveButton(btnPassword);

        TableView<PasswordEntry> table = new TableView<>();
        table.setStyle(
                "-fx-background-color: #EDE0CC;" +
                        "-fx-border-color: #C4A882;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;"
        );
        table.setFixedCellSize(40);
        table.setItems(passwords);

        TableColumn<PasswordEntry, String> nameCol = new TableColumn<>("Názov");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(150);
        nameCol.setStyle("-fx-alignment: CENTER-LEFT; -fx-font-size: 13px;");

        TableColumn<PasswordEntry, String> usernameCol = new TableColumn<>("Používateľ");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameCol.setPrefWidth(150);
        usernameCol.setStyle("-fx-alignment: CENTER-LEFT; -fx-font-size: 13px;");

        TableColumn<PasswordEntry, String> passwordCol = new TableColumn<>("Heslo");
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        passwordCol.setPrefWidth(150);
        passwordCol.setStyle("-fx-alignment: CENTER-LEFT; -fx-font-size: 13px;");

        TableColumn<PasswordEntry, Void> deleteCol = new TableColumn<>("");
        deleteCol.setPrefWidth(80);
        deleteCol.setCellFactory(col -> new TableCell<PasswordEntry, Void>() {
            private final Button deleteBtn = new Button("Vymazať");
            {
                deleteBtn.setOnAction(e -> {
                    PasswordEntry entry = getTableView().getItems().get(getIndex());
                    passwords.remove(entry);
                    PasswordStorage.save(new ArrayList<>(passwords), currentUser, masterPassword);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteBtn);
                }
            }
        });

        table.getColumns().addAll(nameCol, usernameCol, passwordCol, deleteCol);
        contentArea.getChildren().clear();
        contentArea.getChildren().add(table);
    }

    @FXML
    protected void showAddPassword() {
        setActiveButton(btnAddPassword);

        VBox form = new VBox(14);
        form.setStyle("-fx-padding: 30; -fx-max-width: 400;");

        Label title = new Label("Pridať heslo");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #6b5040; -fx-padding: 0 0 10 0;");

        TextField nameField = new TextField();
        nameField.setPromptText("Názov (napr. Gmail)");
        nameField.setMaxWidth(300);
        nameField.setStyle("-fx-background-color: #E5D5BC; -fx-background-radius: 8; -fx-border-color: #C4A882; -fx-border-radius: 8; -fx-padding: 10; -fx-font-size: 13px;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Používateľské meno");
        usernameField.setMaxWidth(300);
        usernameField.setStyle("-fx-background-color: #E5D5BC; -fx-background-radius: 8; -fx-border-color: #C4A882; -fx-border-radius: 8; -fx-padding: 10; -fx-font-size: 13px;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Heslo");
        passwordField.setMaxWidth(300);
        passwordField.setStyle("-fx-background-color: #E5D5BC; -fx-background-radius: 8; -fx-border-color: #C4A882; -fx-border-radius: 8; -fx-padding: 10; -fx-font-size: 13px;");

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-font-size: 13px;");

        Button saveBtn = new Button("Uložiť");
        saveBtn.setStyle("-fx-background-color: #C4A882; -fx-text-fill: #4a3728; -fx-font-size: 13px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 8; -fx-cursor: hand;");
        saveBtn.setOnAction(e -> {
            String name = nameField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                messageLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: red;");
                messageLabel.setText("Vyplň všetky polia!");
                return;
            }

            passwords.add(new PasswordEntry(name, username, password));
            PasswordStorage.save(new ArrayList<>(passwords), currentUser, masterPassword);
            messageLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: green;");
            messageLabel.setText("Heslo uložené!");
            nameField.clear();
            usernameField.clear();
            passwordField.clear();
        });

        form.getChildren().addAll(title, nameField, usernameField, passwordField, saveBtn, messageLabel);

        contentArea.getChildren().clear();
        contentArea.getChildren().add(form);
    }

    @FXML
    protected void onLogout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Stage stage = (Stage) contentArea.getScene().getWindow();
        Scene scene = new Scene(loader.load(), 400, 380);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Vaulty");
    }
}