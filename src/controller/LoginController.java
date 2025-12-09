package controller;

import javafx.stage.Stage;
import view.LoginView;

public class LoginController {

    private LoginView view;

    public LoginController(Stage stage) {
        view = new LoginView(stage);
        view.show();

        view.getLoginButton().setOnAction(e -> handleLogin());
    }

    private void handleLogin() {
        String email = view.getEmail();
        String pass = view.getPassword();

        System.out.println("Login attempt: " + email + " / " + pass);
    }
}
