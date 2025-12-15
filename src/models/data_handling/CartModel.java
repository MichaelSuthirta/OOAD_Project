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

	public static boolean upsertCartItem(int customerId, int productId, int count) {
		// Jika sudah ada, update. Jika belum, insert.
		String check = "SELECT 1 FROM cart_items WHERE idCustomer = ? AND idProduct = ? LIMIT 1";
		try (PreparedStatement psCheck = conn.prepareStatement(check)) {
			psCheck.setInt(1, customerId);
			psCheck.setInt(2, productId);
			try (ResultSet rs = psCheck.executeQuery()) {
				if (rs.next()) {
					String update = "UPDATE cart_items SET count = ? WHERE idCustomer = ? AND idProduct = ?";
					try (PreparedStatement psUpdate = conn.prepareStatement(update)) {
						psUpdate.setInt(1, count);
						psUpdate.setInt(2, customerId);
						psUpdate.setInt(3, productId);
						return psUpdate.executeUpdate() > 0;
					}
				} else {
					String insert = "INSERT INTO cart_items(idCustomer, idProduct, count) VALUES(?, ?, ?)";
					try (PreparedStatement psInsert = conn.prepareStatement(insert)) {
						psInsert.setInt(1, customerId);
						psInsert.setInt(2, productId);
						psInsert.setInt(3, count);
						return psInsert.executeUpdate() > 0;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
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

	public static boolean clearCart(int customerId) {
		String query = "DELETE FROM cart_items WHERE idCustomer = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, customerId);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
