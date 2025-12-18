package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EditProfileView {

    private Stage stage;
    private Scene scene;
    private GridPane gp;

    private TextField fullNameField, emailField, phoneField, addressField;
    private PasswordField passwordField;

    private Button saveButton, backButton;

    // Creates a new EditProfileView instance.
     

    public EditProfileView(Stage stage) {
        this.stage = stage;
    }

    // Displays the view.
     

    public void show(String fullName, String email, String phone, String address) {
        gp = new GridPane();
        gp.setPadding(new Insets(20));
        gp.setVgap(10);
        gp.setHgap(10);

        Label title = new Label("Edit Profile");

        fullNameField = new TextField(fullName == null ? "" : fullName);
        emailField = new TextField(email == null ? "" : email);
        passwordField = new PasswordField();
        passwordField.setPromptText("New password (leave blank to keep)");
        phoneField = new TextField(phone == null ? "" : phone);
        addressField = new TextField(address == null ? "" : address);

        saveButton = new Button("Save");
        backButton = new Button("Back");

        gp.add(title, 0, 0, 2, 1);

        gp.add(new Label("Full Name:"), 0, 1);
        gp.add(fullNameField, 1, 1);

        gp.add(new Label("Email:"), 0, 2);
        gp.add(emailField, 1, 2);

        gp.add(new Label("Password:"), 0, 3);
        gp.add(passwordField, 1, 3);

        gp.add(new Label("Phone:"), 0, 4);
        gp.add(phoneField, 1, 4);

        gp.add(new Label("Address:"), 0, 5);
        gp.add(addressField, 1, 5);

        gp.add(saveButton, 1, 6);
        gp.add(backButton, 1, 7);

        scene = new Scene(gp, 420, 320);
        stage.setScene(scene);
        stage.setTitle("Edit Profile");
        stage.show();
    }

    // Getters used by Main/Controller to read user input and wire button actions.
    public String getFullName() { return fullNameField.getText(); }
    public String getEmail() { return emailField.getText(); }
    public String getPassword() { return passwordField.getText(); }
    public String getPhone() { return phoneField.getText(); }
    public String getAddress() { return addressField.getText(); }

    // Returns save button.
     

    public Button getSaveButton() { return saveButton; }
    public Button getBackButton() { return backButton; }
}