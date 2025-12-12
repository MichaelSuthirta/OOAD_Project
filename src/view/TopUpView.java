package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TopUpView {

    Stage stage;
    TextField amountField;
    Button topUpButton, backButton;
    GridPane gp;
    Scene scene;

    public TopUpView(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        gp = new GridPane();
        gp.setPadding(new Insets(20));
        gp.setVgap(10);
        gp.setHgap(10);

        amountField = new TextField();
        topUpButton = new Button("Top Up");
        backButton = new Button("Back");

        gp.add(new Label("Top Up Amount:"), 0, 0);
        gp.add(amountField, 1, 0);
        gp.add(topUpButton, 1, 1);
        gp.add(backButton, 1, 2);

        scene = new Scene(gp, 350, 200);
        stage.setScene(scene);
        stage.setTitle("Top Up Balance");
        stage.show();
    }

    public String getAmount() { return amountField.getText(); }
    public Button getTopUpButton() { return topUpButton; }
    public Button getBackButton() { return backButton; }
}
