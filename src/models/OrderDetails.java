package models;

public class OrderDetails {
	String idOrder, idProduct;
	int qty;
	public OrderDetails(String idOrder, String idProduct, int qty) {
		super();
		this.idOrder = idOrder;
		this.idProduct = idProduct;
		this.qty = qty;
	}
	
	
}
