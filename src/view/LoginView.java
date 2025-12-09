package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginView {

    Stage stage;
    TextField emailField;
    PasswordField passwordField;
    Button loginButton;
    GridPane gp;
    Scene scene;
    Label emailLabel, passLabel;

    public LoginView(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        gp = new GridPane();
        gp.setPadding(new Insets(20));
        gp.setVgap(10);
        gp.setHgap(10);

        emailLabel = new Label("Email:");
        emailField = new TextField();

        passLabel = new Label("Password:");
        passwordField = new PasswordField();

        loginButton = new Button("Login");

        gp.add(emailLabel, 0, 0);
        gp.add(emailField, 1, 0);

        gp.add(passLabel, 0, 1);
        gp.add(passwordField, 1, 1);

        gp.add(loginButton, 1, 2);

        scene = new Scene(gp, 350, 200);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }
    
    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public Button getLoginButton() {
        return loginButton;
    }
}
