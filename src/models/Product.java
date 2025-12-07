package models;

public class Product {
	private String idProduct, name, category;
	private double price;
	private int stock;
	
	public Product(String idProduct, String name, String category, double price, int stock) {
		super();
		this.idProduct = idProduct;
		this.name = name;
		this.category = category;
		this.price = price;
		this.stock = stock;
	}
	
	
}
