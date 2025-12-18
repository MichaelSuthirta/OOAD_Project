package controller;

import models.data_handling.CartModel;
import models.data_handling.ProductModel;
import models.entity_models.Product;

public class CartController {
	//Adds an item.
	 

	public static void addItem(String userID, String productID, int amount) {
		
		Product product = ProductModel.findProductByID(Integer.parseInt(productID));
		
		if(product == null) {
			return;
		}
		
		if(amount < 1 || amount > product.getStock()) {
			return;
		}
		
		CartModel.insertCartItem(userID, productID, amount);
	}
	
	// Updates the item.


	public static void updateItem(String customerId, String productId, int newQty) {
		if (newQty < 1) return;

		Product product = ProductModel.findProductByID(Integer.parseInt(productId));
		if (product == null) return;
		if (newQty > product.getStock()) return;

		CartModel.updateCartItem(customerId, productId, newQty);
    }
}