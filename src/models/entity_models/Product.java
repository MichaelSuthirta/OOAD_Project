package models.entity_models;

public class Product {
	private String idProduct, name, category;
	private double price;
	private int stock;
	
	//Creates a new Product instance.

	public Product(String idProduct, String name, String category, double price, int stock) {
		super();
		this.idProduct = idProduct;
		this.name = name;
		this.category = category;
		this.price = price;
		this.stock = stock;
	}

	//Returns id product.


	public String getIdProduct() {
		return idProduct;
	}

	//Sets the id product.


	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}

	// Returns name.


	public String getName() {
		return name;
	}

	//Sets the name.

	public void setName(String name) {
		this.name = name;
	}

	//Returns category.


	public String getCategory() {
		return category;
	}

	// Sets the category.

	public void setCategory(String category) {
		this.category = category;
	}

	// Returns price.

	public double getPrice() {
		return price;
	}

	//Sets the price.


	public void setPrice(double price) {
		this.price = price;
	}

	//Returns stock.


	public int getStock() {
		return stock;
	}

	// Sets the stock.


	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}