package models.entity_models;

public class OrderDetails {
	private String idOrder, idProduct;
	private int qty;
	
	//Creates a new OrderDetails instance.


	public OrderDetails(String idOrder, String idProduct, int qty) {
		super();
		this.idOrder = idOrder;
		this.idProduct = idProduct;
		this.qty = qty;
	}
	
}