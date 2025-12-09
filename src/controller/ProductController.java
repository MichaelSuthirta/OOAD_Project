package controller;

import data_access.ProductRepository;
import models.Product;

public class ProductController {
	
	public static Product editStock(String id, String newStock) {
		int stock;
		
		try {
			stock = Integer.parseInt(newStock);
		}
		catch(Exception e) {
			System.out.println("Stock must be numeric");
			return null;
		}
		
		if(stock < 0) {
			return null;
		}
		
		return ProductRepository.updateStock(Integer.parseInt(id), stock);
	}

}
