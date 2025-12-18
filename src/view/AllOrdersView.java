package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import models.entity_models.AllOrdersRow;

public class AllOrdersView {

    Stage stage;
    TableView<AllOrdersRow> orderTable;
    Button backButton;
    BorderPane bp;
    Label title;
    HBox hbox;
    Scene scene;

    // Creates a new AllOrdersView instance.


    public AllOrdersView(Stage stage) {
        this.stage = stage;
    }

    // Displays the view.
   

    public void show() {
        bp = new BorderPane();
        bp.setPadding(new Insets(15));

        title = new Label("All Orders");

        orderTable = new TableView<>();
        orderTable.setPlaceholder(new Label("No orders yet."));

        TableColumn<AllOrdersRow, String> orderIDCol = new TableColumn<>("Order ID");
        orderIDCol.setPrefWidth(90);
        orderIDCol.setCellValueFactory(new PropertyValueFactory<>("orderId"));

        TableColumn<AllOrdersRow, String> customerCol = new TableColumn<>("Customer");
        customerCol.setPrefWidth(230);
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customer"));

        TableColumn<AllOrdersRow, Number> totalAmountCol = new TableColumn<>("Total Amount");
        totalAmountCol.setPrefWidth(110);
        totalAmountCol.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        TableColumn<AllOrdersRow, String> statusCol = new TableColumn<>("Status");
        statusCol.setPrefWidth(90);
        statusCol.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

        orderTable.getColumns().addAll(orderIDCol, customerCol, totalAmountCol, statusCol);

        backButton = new Button("Back");

        hbox = new HBox(10, backButton);

        bp.setTop(title);
        bp.setCenter(orderTable);
        bp.setBottom(hbox);

        scene = new Scene(bp, 550, 350);
        stage.setScene(scene);
        stage.setTitle("All Orders");
        stage.show();
    }

    // Returns order table.
     

    public TableView<AllOrdersRow> getOrderTable() { return orderTable; }
    public Button getBackButton() { return backButton; }
}