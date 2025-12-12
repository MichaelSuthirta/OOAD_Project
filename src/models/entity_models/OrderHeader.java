package models.entity_models;

import java.util.Date;

public class OrderHeader {
	private String idOrder, idCustomer, idPromo, status;
	private Date orderedAt;
	private double totalAmount;
	
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
	
}
