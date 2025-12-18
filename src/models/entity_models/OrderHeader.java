package models.entity_models;

import java.util.Date;

public class OrderHeader {
	private String idOrder, idCustomer, idPromo, status;
	private Date orderedAt;
	private double totalAmount;
	
	//Creates a new OrderHeader instance.


	public OrderHeader(String idOrder, String idCustomer, String idPromo, String status,
			Date orderDate, double totalAmount) {
		super();
		this.idOrder = idOrder;
		this.idCustomer = idCustomer;
		this.idPromo = idPromo;
		this.status = status;
		this.orderedAt = orderDate;
		this.totalAmount = totalAmount;
	}

	public String getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(String idOrder) {
		this.idOrder = idOrder;
	}

	public String getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getIdPromo() {
		return idPromo;
	}

	public void setIdPromo(String idPromo) {
		this.idPromo = idPromo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOrderedAt() {
		return orderedAt;
	}

	public void setOrderedAt(Date orderedAt) {
		this.orderedAt = orderedAt;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}