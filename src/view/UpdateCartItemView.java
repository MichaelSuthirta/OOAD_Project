package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UpdateCartItemView {

    Stage stage;
    Label productLabel;
    TextField qtyField;
    Button updateButton, cancelButton;
    GridPane gp;
    Scene scene;

    public UpdateCartItemView(Stage stage) {
        this.stage = stage;
    }

    public void show(String productName, int currentQty) {
        gp = new GridPane();
        gp.setPadding(new Insets(20));
        gp.setVgap(10);
        gp.setHgap(10);

        productLabel = new Label(productName);
        qtyField = new TextField(String.valueOf(currentQty));

        updateButton = new Button("Update");
        cancelButton = new Button("Cancel");

        gp.add(new Label("Product:"), 0, 0);
        gp.add(productLabel, 1, 0);

        gp.add(new Label("New Quantity:"), 0, 1);
        gp.add(qtyField, 1, 1);

        gp.add(updateButton, 1, 2);
        gp.add(cancelButton, 1, 3);

        scene = new Scene(gp, 350, 200);
        stage.setScene(scene);
        stage.setTitle("Update Item");
        stage.show();
    }

    public String getNewQty() { return qtyField.getText(); }
    public Button getUpdateButton() { return updateButton; }
    public Button getCancelButton() { return cancelButton; }
}
