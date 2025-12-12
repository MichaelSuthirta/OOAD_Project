package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegisterView {

    Stage stage;
    TextField nameField, emailField, phoneField, addressField;
    PasswordField passField, confirmField;
    ComboBox<String> genderBox;
    Button registerButton;
    GridPane gp;
    Scene scene;
    

    public RegisterView(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        gp = new GridPane();
        gp.setPadding(new Insets(20));
        gp.setVgap(10);
        gp.setHgap(10);

        nameField = new TextField();
        emailField = new TextField();
        passField = new PasswordField();
        confirmField = new PasswordField();
        phoneField = new TextField();
        addressField = new TextField();

        genderBox = new ComboBox<>();
        genderBox.getItems().addAll("Male", "Female");

        registerButton = new Button("Register");

        gp.add(new Label("Full Name:"), 0, 0);
        gp.add(nameField, 1, 0);

        gp.add(new Label("Email:"), 0, 1);
        gp.add(emailField, 1, 1);

        gp.add(new Label("Password:"), 0, 2);
        gp.add(passField, 1, 2);

        gp.add(new Label("Confirm Password:"), 0, 3);
        gp.add(confirmField, 1, 3);

        gp.add(new Label("Phone:"), 0, 4);
        gp.add(phoneField, 1, 4);

        gp.add(new Label("Address:"), 0, 5);
        gp.add(addressField, 1, 5);

        gp.add(new Label("Gender:"), 0, 6);
        gp.add(genderBox, 1, 6);

        gp.add(registerButton, 1, 7);

        scene = new Scene(gp, 450, 400);
        stage.setScene(scene);
        stage.setTitle("Register");
        stage.show();
    }

    public String getName() { return nameField.getText(); }
    public String getEmail() { return emailField.getText(); }
    public String getPassword() { return passField.getText(); }
    public String getConfirmPassword() { return confirmField.getText(); }
    public String getPhone() { return phoneField.getText(); }
    public String getAddress() { return addressField.getText(); }
    public String getGender() { return genderBox.getValue(); }

    public Button getRegisterButton() {
        return registerButton;
    }
}
