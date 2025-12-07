package models;

public class OrderDetails {
	private String idOrder, idProduct;
	private int qty;
	
	public OrderDetails(String idOrder, String idProduct, int qty) {
		super();
		this.idOrder = idOrder;
		this.idProduct = idProduct;
		this.qty = qty;
	}
	
}
