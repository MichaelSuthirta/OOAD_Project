package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AssignCourierView {

    Stage stage;
    ComboBox<String> orderIDBox;
    ComboBox<String> courierBox;
    Button assignButton, backButton;
    GridPane gp;
    Scene scene;

    public AssignCourierView(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        gp = new GridPane();
        gp.setPadding(new Insets(20));
        gp.setVgap(10);
        gp.setHgap(10);

        orderIDBox = new ComboBox<>();
        courierBox = new ComboBox<>();
        assignButton = new Button("Assign Courier");
        backButton = new Button("Back");

        gp.add(new Label("Order ID:"), 0, 0);
        gp.add(orderIDBox, 1, 0);

        gp.add(new Label("Courier:"), 0, 1);
        gp.add(courierBox, 1, 1);

        gp.add(assignButton, 1, 2);
        gp.add(backButton, 1, 3);

        scene = new Scene(gp, 350, 250);
        stage.setScene(scene);
        stage.setTitle("Assign Courier to Order");
        stage.show();
    }

    public ComboBox<String> getOrderIDBox() { return orderIDBox; }
    public ComboBox<String> getCourierBox() { return courierBox; }
    public Button getAssignButton() { return assignButton; }
    public Button getBackButton() { return backButton; }
}
