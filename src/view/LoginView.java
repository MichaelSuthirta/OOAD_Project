package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
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
    Hyperlink signupLink;

    // Creates a new LoginView instance.
     

    public LoginView(Stage stage) {
        this.stage = stage;
    }

    // Displays the view.
     

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

        signupLink = new Hyperlink("Don’t have an account? Sign up");

        gp.add(emailLabel, 0, 0);
        gp.add(emailField, 1, 0);

        gp.add(passLabel, 0, 1);
        gp.add(passwordField, 1, 1);

        gp.add(loginButton, 1, 2);

        gp.add(signupLink, 1, 3); 

        signupLink.setOnAction(e -> {
            System.out.println("Sign Up clicked!");
            new RegisterView(stage).show();
        });

        scene = new Scene(gp, 350, 220);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    
    // Returns email.
     

    public String getEmail() {
        return emailField.getText();
    }

    //Returns password.
     

    public String getPassword() {
        return passwordField.getText();
    }

    // Returns login button.
     

    public Button getLoginButton() {
        return loginButton;
    }
}