package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AllOrdersView {

    Stage stage;
    TableView<Object> orderTable;
    Button backButton;
    BorderPane bp;
    Label title;
    HBox hbox;
    Scene scene;

    public AllOrdersView(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        bp = new BorderPane();
        bp.setPadding(new Insets(15));

        title = new Label("All Orders");

        orderTable = new TableView<>();

        TableColumn<Object, String> orderIDCol = new TableColumn<>("Order ID");
        orderIDCol.setPrefWidth(100);

        TableColumn<Object, String> customerCol = new TableColumn<>("Customer");
        customerCol.setPrefWidth(200);

        TableColumn<Object, Number> totalAmountCol = new TableColumn<>("Total Amount");
        totalAmountCol.setPrefWidth(120);

        orderTable.getColumns().addAll(orderIDCol, customerCol, totalAmountCol);

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

    public TableView<Object> getOrderTable() { return orderTable; }
    public Button getBackButton() { return backButton; }
}
