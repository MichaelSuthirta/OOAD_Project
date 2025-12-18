package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EditProductStockView {

    Stage stage;
    ComboBox<String> productBox;
    TextField stockField;
    Button saveButton, backButton;
    GridPane gp;
    Scene scene;

    // Creates a new EditProductStockView instance.
     

    public EditProductStockView(Stage stage) {
        this.stage = stage;
    }

    // Displays the view.
     

    public void show() {
        gp = new GridPane();
        gp.setPadding(new Insets(20));
        gp.setVgap(10);
        gp.setHgap(10);

        productBox = new ComboBox<>();
        stockField = new TextField();
        saveButton = new Button("Save Changes");
        backButton = new Button("Back");

        gp.add(new Label("Product:"), 0, 0);
        gp.add(productBox, 1, 0);

        gp.add(new Label("New Stock:"), 0, 1);
        gp.add(stockField, 1, 1);

        gp.add(saveButton, 1, 2);
        gp.add(backButton, 1, 3);

        scene = new Scene(gp, 350, 250);
        stage.setScene(scene);
        stage.setTitle("Edit Product Stock");
        stage.show();
    }

    // Returns product box.
     

    public ComboBox<String> getProductBox() { return productBox; }
    public String getStock() { return stockField.getText(); }
    // Returns save button.
     

    public Button getSaveButton() { return saveButton; }
    public Button getBackButton() { return backButton; }
}