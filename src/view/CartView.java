package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.data_handling.CartModel;
import models.entity_models.CartItem;

public class CartView {

    private int customerId;
    Stage stage;
    TableView<CartItem> cartTable;
    Button updateButton, checkoutButton, backButton, removeButton;
    BorderPane bp;
    HBox hbox;
    Scene scene;
    Label title;

    // Creates a new CartView instance.
     

    public CartView(Stage stage) {
        this.stage = stage;
    }

    // Displays the view.
     

    public void show(int customerId) {
        
        this.customerId = customerId;

        bp = new BorderPane();
        bp.setPadding(new Insets(15));

        title = new Label("Your Cart");

        cartTable = new TableView<>();

        TableColumn<CartItem, String> nameCol = new TableColumn<>("Product");
        nameCol.setPrefWidth(150);

        TableColumn<CartItem, Integer> qtyCol = new TableColumn<>("Quantity");
        qtyCol.setPrefWidth(80);

        TableColumn<CartItem, Double> priceCol = new TableColumn<>("Price");
        priceCol.setPrefWidth(100);

        TableColumn<CartItem, Double> totalCol = new TableColumn<>("Total");
        totalCol.setPrefWidth(100);

        cartTable.getColumns().addAll(nameCol, qtyCol, priceCol, totalCol);

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

        cartTable.setItems(CartModel.getCartItemsForTable(customerId));

        updateButton = new Button("Update Item");
        checkoutButton = new Button("Checkout");
        backButton = new Button("Back");
        removeButton = new Button("Remove Item");
        

        
        updateButton.setOnAction(e -> handleUpdate());
        removeButton.setOnAction(e -> handleRemove());


        hbox = new HBox(10, updateButton, checkoutButton, backButton, removeButton);

        bp.setTop(title);
        bp.setCenter(cartTable);
        bp.setBottom(hbox);

        scene = new Scene(bp, 550, 350);
        stage.setScene(scene);
        stage.setTitle("Cart");
        stage.show();
    }

    
    // Performs the handle update operation.
     

    private void handleUpdate() {
        CartItem selected = cartTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        TextInputDialog dialog = new TextInputDialog(
            String.valueOf(selected.getQuantity())
        );
        dialog.setTitle("Update Quantity");
        dialog.setHeaderText(null);
        dialog.setContentText("New Quantity:");

        dialog.showAndWait().ifPresent(input -> {
            try {
                int newQty = Integer.parseInt(input);
                if (newQty < 1) return;

                // UPDATE DB
                CartModel.updateCartItem(
                    String.valueOf(customerId),
                    selected.getIdProduct(),
                    newQty
                );

                // RELOAD TABLE
                cartTable.setItems(
                    CartModel.getCartItemsForTable(customerId)
                );

            } catch (NumberFormatException ex) {
                // ignore
            }
        });
    }
    
    /**
     * Performs the handle remove operation.
     */

    private void handleRemove() {
        CartItem selected = cartTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Remove Item");
        confirm.setHeaderText(null);
        confirm.setContentText("Remove " + selected.getName() + " from cart?");

        confirm.showAndWait().ifPresent(btn -> {
            if (btn == ButtonType.OK) {
                // HAPUS dari DB
                CartModel.deleteCartItem(
                    String.valueOf(customerId),
                    selected.getIdProduct()
                );

                // RELOAD TABLE
                cartTable.setItems(CartModel.getCartItemsForTable(customerId));
            }
        });
    }


    // Returns cart table.
     

    public TableView<CartItem> getCartTable() { return cartTable; }
    public Button getUpdateButton() { return updateButton; }
    // Returns checkout button.
     

    public Button getCheckoutButton() { return checkoutButton; }
    public Button getBackButton() { return backButton; }
}