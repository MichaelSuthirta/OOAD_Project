package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.data_handling.ProductModel;
import models.entity_models.Product;

public class ProductListView {

    Stage stage;
    TableView<Product> productTable;
    Button addToCartButton, backButton;
    BorderPane bp;
    HBox hbox;
    Label title;

    // Creates a new ProductListView instance.
     

    public ProductListView(Stage stage) {
        this.stage = stage;
    }

    // Displays the view.
     

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
        
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        productTable.setItems(ProductModel.getAllProducts());


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

    // Returns product table.
     

    public TableView<Product> getProductTable() { return productTable; }
    public Button getAddToCartButton() { return addToCartButton; }
    // Returns back button.
     
    public Button getBackButton() { return backButton; }
}