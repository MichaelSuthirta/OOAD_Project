package models.entity_models;

import java.sql.Timestamp;

public class AllOrdersRow {
    private final String orderId;
    private final String customer;
    private final double totalAmount;
    private final String orderStatus;
    private final Timestamp orderedAt;

    //Creates a new AllOrdersRow instance.


    public AllOrdersRow(String orderId, String customer, double totalAmount, String orderStatus, Timestamp orderedAt) {
        this.orderId = orderId;
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.orderedAt = orderedAt;
    }

    //Returns order id.
 

    public String getOrderId() { return orderId; }
    public String getCustomer() { return customer; }
    // Returns total amount.


    public double getTotalAmount() { return totalAmount; }
    public String getOrderStatus() { return orderStatus; }
    //Returns ordered at.


    public Timestamp getOrderedAt() { return orderedAt; }
}