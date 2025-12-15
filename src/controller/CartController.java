package controller;

import models.data_handling.ProductModel;
import models.entity_models.Product;

public class CartController {
	public static void addItem(String userID, String productID, int amount) {
		Product product = ProductModel.findProductByID(Integer.parseInt(productID));
		
		if(product == null) {
			return;
		}
		
		if(amount < 1 || amount > product.getStock()) {
			return;
		}
		
		
	}
}
