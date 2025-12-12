package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EditDeliveryStatusView {

    Stage stage;
    ComboBox<String> orderBox;
    ComboBox<String> statusBox;
    Button updateButton,backButton;
    GridPane gp;
    Scene scene;

    public EditDeliveryStatusView(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        gp = new GridPane();
        gp.setPadding(new Insets(20));
        gp.setVgap(10);
        gp.setHgap(10);

        orderBox = new ComboBox<>();
        statusBox = new ComboBox<>();
        statusBox.getItems().addAll("Pending", "In Progress", "Delivered");
        updateButton = new Button("Update Status");
        backButton = new Button("Back");

        gp.add(new Label("Order:"), 0, 0);
        gp.add(orderBox, 1, 0);

        gp.add(new Label("Delivery Status:"), 0, 1);
        gp.add(statusBox, 1, 1);

        gp.add(updateButton, 1, 2);
        gp.add(backButton, 1, 3);

        scene = new Scene(gp, 350, 250);
        stage.setScene(scene);
        stage.setTitle("Edit Delivery Status");
        stage.show();
    }

    public ComboBox<String> getOrderBox() { return orderBox; }
    public String getStatus() { return statusBox.getValue(); }
    public Button getUpdateButton() { return updateButton; }
    public Button getBackButton() { return backButton; }
}
