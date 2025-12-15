package models.data_handling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.entity_models.CartItem;

//Akses DB untuk tabel cart_items.
 
public class CartModel {
	private static final Connection conn = DatabaseConnector.getConnection();

	public static List<CartItem> getCartItems(int customerId) {
		List<CartItem> items = new ArrayList<>();
		String query = "SELECT * FROM cart_items WHERE idCustomer = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, customerId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					items.add(new CartItem(
							Integer.toString(rs.getInt("idCustomer")),
							Integer.toString(rs.getInt("idProduct")),
							rs.getInt("count")
					));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	public static CartItem insertCartItem(String userID, String idProduct, int count) {
		String query = "INSERT INTO cart(idUser, idProduct, count) VALUES(?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, userID);
			ps.setString(2, idProduct);
			ps.setInt(3, count);
		} catch (SQLException e) {
			System.out.println("Failed to prepareQuery");
			e.printStackTrace();
		}
		return new CartItem(userID, idProduct, count);
	}

	public static CartItem updateCartItem(String userID, String productId, int count) {
		//insert.
		String query = "UPDATE cart_items SET count = ? WHERE idCustomer = ? AND idProduct = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, count);
			ps.setString(2, userID);
			ps.setString(3, productId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return CartModel.updateCartItem(userID, productId, count);
	}

	public static boolean deleteCartItem(int customerId, int productId) {
		String query = "DELETE FROM cart_items WHERE idCustomer = ? AND idProduct = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, customerId);
			ps.setInt(2, productId);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
