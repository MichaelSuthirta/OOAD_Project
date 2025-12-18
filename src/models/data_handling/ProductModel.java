package models.data_handling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.entity_models.Product;

public class ProductModel {
	private static Connection conn = DatabaseConnector.getConnection();
	
	//inds a product by id.


	public static Product findProductByID(int ID) {
	    String query = "SELECT * FROM products WHERE idProduct = ?";

	    try {
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, ID);
	        ResultSet result = ps.executeQuery();

	        if (result.next()) { // ✅ WAJIB
	            Product product = new Product(
	                Integer.toString(result.getInt("idProduct")),
	                result.getString("name"),
	                result.getString("category"),
	                result.getDouble("price"),
	                result.getInt("stock")
	            );
	            return product;
	        }

	        return null; 

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	
	//Updates the stock.


	public static Product updateStock(int ID, int newStock) {
		String query = "UPDATE products SET stock = ? WHERE idProduct = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, newStock);
			ps.setInt(2, ID);
			
			//Update the product
			int res = ps.executeUpdate();
			
			//Return the product
			return findProductByID(ID);
		} catch (SQLException e) {
			System.out.println("Failure in connection to database.");
			e.printStackTrace();
			return null;
		}
		
	}
	
	// Returns all products.


	public static ObservableList<Product> getAllProducts() {
	    ObservableList<Product> list = FXCollections.observableArrayList();
	    String query = "SELECT * FROM products";

	    try {
	        PreparedStatement ps = conn.prepareStatement(query);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Product p = new Product(
	                Integer.toString(rs.getInt("idProduct")),
	                rs.getString("name"),
	                rs.getString("category"),
	                rs.getDouble("price"),
	                rs.getInt("stock")
	            );
	            list.add(p);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}

}