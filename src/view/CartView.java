package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CartView {

    Stage stage;
    TableView<Object> cartTable;
    Button updateButton, checkoutButton, backButton;
    BorderPane bp ;
    HBox hbox;
    Scene scene;
    Label title;

    public CartView(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        bp = new BorderPane();
        bp.setPadding(new Insets(15));

        title = new Label("Your Cart");

        cartTable = new TableView<>();

        TableColumn<Object, String> nameCol = new TableColumn<>("Product");
        nameCol.setPrefWidth(150);

        TableColumn<Object, Integer> qtyCol = new TableColumn<>("Quantity");
        qtyCol.setPrefWidth(80);

        TableColumn<Object, Integer> priceCol = new TableColumn<>("Price");
        priceCol.setPrefWidth(100);

        TableColumn<Object, Integer> totalCol = new TableColumn<>("Total");
        totalCol.setPrefWidth(100);

        cartTable.getColumns().addAll(nameCol, qtyCol, priceCol, totalCol);

        updateButton = new Button("Update Item");
        checkoutButton = new Button("Checkout");
        backButton = new Button("Back");

        hbox = new HBox(10, updateButton, checkoutButton, backButton);

        bp.setTop(title);
        bp.setCenter(cartTable);
        bp.setBottom(hbox);

        scene = new Scene(bp, 550, 350);
        stage.setScene(scene);
        stage.setTitle("Cart");
        stage.show();
    }

    public TableView<Object> getCartTable() { return cartTable; }
    public Button getUpdateButton() { return updateButton; }
    public Button getCheckoutButton() { return checkoutButton; }
    public Button getBackButton() { return backButton; }
}
