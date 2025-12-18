package view;

import java.lang.reflect.Field;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.entity_models.Courier;

public class AllCouriersView {

    private Stage stage;
    private Scene scene;
    private BorderPane bp;

    private Label title;
    private TableView<Courier> courierTable;
    private Button backButton;

    // Creates a new AllCouriersView instance.
     

    public AllCouriersView(Stage stage) {
        this.stage = stage;
    }

    //Displays the view.
     

    public void show() {
        bp = new BorderPane();
        bp.setPadding(new Insets(15));

        title = new Label("All Couriers");

        courierTable = new TableView<>();
        courierTable.setPlaceholder(new Label("No couriers found."));

        TableColumn<Courier, String> idCol = new TableColumn<>("Courier ID");
        idCol.setPrefWidth(90);
        idCol.setCellValueFactory(c ->
            new ReadOnlyStringWrapper(getFieldDeep(c.getValue(), "idUser"))
        );

        TableColumn<Courier, String> nameCol = new TableColumn<>("Full Name");
        nameCol.setPrefWidth(170);
        nameCol.setCellValueFactory(c ->
            new ReadOnlyStringWrapper(safe(c.getValue().getFullName()))
        );

        TableColumn<Courier, String> emailCol = new TableColumn<>("Email");
        emailCol.setPrefWidth(200);
        emailCol.setCellValueFactory(c ->
            new ReadOnlyStringWrapper(safe(c.getValue().getEmail()))
        );

        TableColumn<Courier, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setPrefWidth(130);
        phoneCol.setCellValueFactory(c ->
            new ReadOnlyStringWrapper(safe(c.getValue().getPhone()))
        );

        TableColumn<Courier, String> vehicleTypeCol = new TableColumn<>("Vehicle Type");
        vehicleTypeCol.setPrefWidth(110);
        vehicleTypeCol.setCellValueFactory(c ->
            new ReadOnlyStringWrapper(getFieldDeep(c.getValue(), "vehicleType"))
        );

        TableColumn<Courier, String> plateCol = new TableColumn<>("Plate");
        plateCol.setPrefWidth(110);
        plateCol.setCellValueFactory(c ->
            new ReadOnlyStringWrapper(getFieldDeep(c.getValue(), "vehiclePlate"))
        );

        courierTable.getColumns().addAll(idCol, nameCol, emailCol, phoneCol, vehicleTypeCol, plateCol);

        backButton = new Button("Back");
        HBox bottom = new HBox(10, backButton);
        bottom.setPadding(new Insets(10, 0, 0, 0));

        bp.setTop(title);
        bp.setCenter(courierTable);
        bp.setBottom(bottom);

        scene = new Scene(bp, 850, 380);
        stage.setScene(scene);
        stage.setTitle("All Couriers");
        stage.show();
    }

    // ===== helpers =====
   

    private String safe(String s) {
        return s == null ? "" : s;
    }

    // Returns field deep.
 

    private String getFieldDeep(Object obj, String fieldName) {
        if (obj == null) return "";
        Class<?> cls = obj.getClass();
        while (cls != null) {
            try {
                Field f = cls.getDeclaredField(fieldName);
                f.setAccessible(true);
                Object val = f.get(obj);
                return val == null ? "" : String.valueOf(val);
            } catch (NoSuchFieldException e) {
                cls = cls.getSuperclass();
            } catch (Exception e) {
                return "";
            }
        }
        return "";
    }


    // Returns courier table.
   

    public TableView<Courier> getCourierTable() {
        return courierTable;
    }

    //Returns back button.
 

    public Button getBackButton() {
        return backButton;
    }
}