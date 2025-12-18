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
import models.entity_models.OrderHistoryRow;

public class OrderHistoryView {

    private Stage stage;
    private TableView<OrderHistoryRow> orderHistoryTable;
    private Button backButton;
    private Label title;
    private BorderPane bp;
    private Scene scene;

    // Creates a new OrderHistoryView instance.
     

    public OrderHistoryView(Stage stage) {
        this.stage = stage;
    }

    // Displays the view.
    

    public void show() {
        bp = new BorderPane();
        bp.setPadding(new Insets(20));

        title = new Label("Order History");

        orderHistoryTable = new TableView<>();

        TableColumn<OrderHistoryRow, String> colId = new TableColumn<>("Order ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("idOrder"));
        colId.setPrefWidth(90);

        TableColumn<OrderHistoryRow, String> colDate = new TableColumn<>("Ordered At");
        colDate.setCellValueFactory(new PropertyValueFactory<>("orderedAtFormatted"));
        colDate.setPrefWidth(150);

        TableColumn<OrderHistoryRow, Double> colTotal = new TableColumn<>("Total");
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        colTotal.setPrefWidth(90);

        TableColumn<OrderHistoryRow, String> colOrderStatus = new TableColumn<>("Order Status");
        colOrderStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
        colOrderStatus.setPrefWidth(110);

        TableColumn<OrderHistoryRow, String> colDeliveryStatus = new TableColumn<>("Delivery Status");
        colDeliveryStatus.setCellValueFactory(new PropertyValueFactory<>("deliveryStatus"));
        colDeliveryStatus.setPrefWidth(130);

        orderHistoryTable.getColumns().addAll(colId, colDate, colTotal, colOrderStatus, colDeliveryStatus);

        backButton = new Button("Back");

        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(10));
        hbox.getChildren().add(backButton);

        bp.setTop(title);
        bp.setCenter(orderHistoryTable);
        bp.setBottom(hbox);

        scene = new Scene(bp, 650, 350);
        stage.setScene(scene);
        stage.setTitle("Order History");
        stage.show();
    }

    // Returns order history table.
     

    public TableView<OrderHistoryRow> getOrderHistoryTable() {
        return orderHistoryTable;
    }

    // Returns back button.
     

    public Button getBackButton() {
        return backButton;
    }
}