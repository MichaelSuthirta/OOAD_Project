package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.entity_models.Product;

public class ProductListView {

    Stage stage;
    TableView<Product> productTable;
    Button addToCartButton, backButton;
    BorderPane bp;
    HBox hbox;
    Label title;

    public ProductListView(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        bp= new BorderPane();
        bp.setPadding(new Insets(15));

        title = new Label("Product List");

      
        productTable = new TableView<>();

        TableColumn<Product, String> nameCol = new TableColumn<>("Product Name");
        nameCol.setPrefWidth(200);
        TableColumn<Product, Integer> priceCol = new TableColumn<>("Price");
        priceCol.setPrefWidth(120);
        TableColumn<Product, Integer> stockCol = new TableColumn<>("Stock");
        stockCol.setPrefWidth(80);

        productTable.getColumns().addAll(nameCol, priceCol, stockCol);

        addToCartButton = new Button("Add to Cart");
        backButton = new Button("Back");

        hbox = new HBox(10, addToCartButton, backButton);

        bp.setTop(title);
        bp.setCenter(productTable);
        bp.setBottom(hbox);

        Scene scene = new Scene(bp, 500, 400);
        stage.setScene(scene);
        stage.setTitle("Products");
        stage.show();
    }

    public TableView<Product> getProductTable() { return productTable; }
    public Button getAddToCartButton() { return addToCartButton; }
    public Button getBackButton() { return backButton; }
}
