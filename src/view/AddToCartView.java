package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddToCartView {

    Stage stage;
    Label productNameLabel;
    TextField countField;
    Button addButton, backButton;
    GridPane gp;
    Scene scene;

    // Creates a new AddToCartView instance.


    public AddToCartView(Stage stage) {
        this.stage = stage;
    }

    // Displays the view.
   

    public void show(String productName) {
        gp = new GridPane();
        gp.setPadding(new Insets(20));
        gp.setVgap(10);
        gp.setHgap(10);

        productNameLabel = new Label(productName);
        countField = new TextField();
        addButton = new Button("Add to Cart");
        backButton = new Button("Back");

        gp.add(new Label("Product:"), 0, 0);
        gp.add(productNameLabel, 1, 0);

        gp.add(new Label("Quantity:"), 0, 1);
        gp.add(countField, 1, 1);

        gp.add(addButton, 1, 2);
        gp.add(backButton, 1, 3);

        scene = new Scene(gp, 350, 200);
        stage.setScene(scene);
        stage.setTitle("Add to Cart");
        stage.show();
    }

    public String getQuantity() { return countField.getText(); }
    public Button getAddButton() { return addButton; }
    // Returns back button.
    

    public Button getBackButton() { return backButton; }
}