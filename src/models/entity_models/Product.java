package models.entity_models;

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

	public String getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}
