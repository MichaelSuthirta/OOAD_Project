package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Product;

public class ProductRepository {
	private static Connection conn = DatabaseConnector.getConnection();
	
	public static Product findProductByID(int ID) {
		String query = "SELECT * FROM products WHERE idProduct = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, ID);
			ResultSet result = ps.executeQuery();
			
			Product product = new Product(
					Integer.toString(result.getInt("idProduct")),
					result.getString("name"),
					result.getString("category"),
					result.getDouble("price"),
					result.getInt("stock")
					);
			
			return product;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static Product updateStock(int ID, int newStock) {
		String query = "UPDATE products SET stock = ? WHERE idProduct = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, newStock);
			ps.setInt(2, ID);
			int res = ps.executeUpdate();
			return findProductByID(ID);
		} catch (SQLException e) {
			System.out.println("Failure in connection to database.");
			e.printStackTrace();
			return null;
		}
		
	}
}
