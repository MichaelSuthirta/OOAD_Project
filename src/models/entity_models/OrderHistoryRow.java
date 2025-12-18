package models.entity_models;

import java.text.SimpleDateFormat;
import java.util.Date;

//Simple row for displaying order history in TableView.

public class OrderHistoryRow {
    private String idOrder;
    private Date orderedAt;
    private double totalAmount;
    private String orderStatus;
    private String deliveryStatus;

    //Creates a new OrderHistoryRow instance.


    public OrderHistoryRow(String idOrder, Date orderedAt, double totalAmount, String orderStatus, String deliveryStatus) {
        this.idOrder = idOrder;
        this.orderedAt = orderedAt;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.deliveryStatus = deliveryStatus;
    }

    //Returns id order.


    public String getIdOrder() { return idOrder; }

    //Returns ordered at.


    public Date getOrderedAt() { return orderedAt; }

    //Returns ordered at formatted.


    public String getOrderedAtFormatted() {
        if (orderedAt == null) return "-";
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(orderedAt);
    }

    //Returns total amount.


    public double getTotalAmount() { return totalAmount; }

    //Returns order status.
 
    public String getOrderStatus() { return orderStatus; }

    //Returns delivery status.
  

    public String getDeliveryStatus() { return deliveryStatus; }
}