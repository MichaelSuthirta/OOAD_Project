package models;

public class CartItem {
	private String idCustomer, idProduct;
	private int count;
	public CartItem(String idCustomer, String idProduct, int count) {
		super();
		this.idCustomer = idCustomer;
		this.idProduct = idProduct;
		this.count = count;
	}
	
	
}
