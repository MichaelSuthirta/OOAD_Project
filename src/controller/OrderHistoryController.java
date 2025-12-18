package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.data_handling.DeliveryModel;
import models.data_handling.OrderHeaderModel;
import models.entity_models.Delivery;
import models.entity_models.OrderHeader;
import models.entity_models.OrderHistoryRow;

public class OrderHistoryController {

    //Returns order history.
     

    public static ObservableList<OrderHistoryRow> getOrderHistory(int customerId) {
        List<OrderHeader> headers = OrderHeaderModel.getOrderHeadersByCustomer(String.valueOf(customerId));
        List<OrderHistoryRow> rows = new ArrayList<>();

        if (headers != null) {
            for (OrderHeader oh : headers) {
                String deliveryStatus = "Not assigned";
                Delivery d = DeliveryModel.getDelivery(oh.getIdOrder());
                if (d != null && d.getStatus() != null) {
                    deliveryStatus = d.getStatus();
                }

                rows.add(new OrderHistoryRow(
                    oh.getIdOrder(),
                    oh.getOrderedAt(),
                    oh.getTotalAmount(),
                    oh.getStatus(),
                    deliveryStatus
                ));
            }
        }

        return FXCollections.observableArrayList(rows);
    }
}