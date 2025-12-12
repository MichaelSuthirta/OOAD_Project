package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class OrderHistoryView {

    Stage stage;
    TableView<Object> orderHistoryTable;
    Button backButton;
    Label title;
    BorderPane bp;
    HBox hbox;
    Scene scene;

    public OrderHistoryView(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        bp = new BorderPane();
        bp.setPadding(new Insets(15));

        title = new Label("Order History");

        orderHistoryTable = new TableView<>();

        TableColumn<Object, String> orderIDCol = new TableColumn<>("Order ID");
        orderIDCol.setPrefWidth(100);

        TableColumn<Object, String> productCol = new TableColumn<>("Product");
        productCol.setPrefWidth(200);

        TableColumn<Object, Number> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setPrefWidth(80);

        TableColumn<Object, String> statusCol = new TableColumn<>("Status");
        statusCol.setPrefWidth(100);

        orderHistoryTable.getColumns().addAll(orderIDCol, productCol, quantityCol, statusCol);

        backButton = new Button("Back");

        hbox = new HBox(10, backButton);

        bp.setTop(title);
        bp.setCenter(orderHistoryTable);
        bp.setBottom(hbox);

        scene = new Scene(bp, 550, 350);
        stage.setScene(scene);
        stage.setTitle("Order History");
        stage.show();
    }

    public TableView<Object> getOrderHistoryTable() { return orderHistoryTable; }
    public Button getBackButton() { return backButton; }
}
