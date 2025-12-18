package models.data_handling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.entity_models.CartItem;

public class CartModel {
    private static final Connection conn = DatabaseConnector.getConnection();

    // For TableView display: enrich cart items with product name and price from the products table.
    //Returns cart items for table.
 

    public static ObservableList<CartItem> getCartItemsForTable(int customerId) {
        ObservableList<CartItem> items = FXCollections.observableArrayList();

        String query =
            "SELECT ci.idProduct, p.name, p.price, ci.count " +
            "FROM cart_items ci JOIN products p ON ci.idProduct = p.idProduct " +
            "WHERE ci.idCustomer = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // CartItem constructor: (idProduct, name, quantity, price)
                    items.add(new CartItem(
                        rs.getString("idProduct"),
                        rs.getString("name"),
                        rs.getInt("count"),
                        rs.getDouble("price")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }


    //Performs the clear cart operation.


    public static boolean clearCart(int customerId) {
        String query = "DELETE FROM cart_items WHERE idCustomer = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, customerId);
            return ps.executeUpdate() >= 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Performs the insert cart item operation.


    public static CartItem insertCartItem(String userID, String idProduct, int count) {
        String query =
            "INSERT INTO cart_items(idCustomer, idProduct, count) VALUES(?, ?, ?) " +
            "ON DUPLICATE KEY UPDATE count = count + VALUES(count)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, userID);
            ps.setString(2, idProduct);
            ps.setInt(3, count);
            ps.executeUpdate();
            return new CartItem(userID, idProduct, count);
        } catch (SQLException e) {
            System.out.println("Failed to prepareQuery");
            e.printStackTrace();
            return null;
        }
    }

    //Updates the cart item.


    public static CartItem updateCartItem(String userID, String productId, int count) {
        String query = "UPDATE cart_items SET count = ? WHERE idCustomer = ? AND idProduct = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, count);
            ps.setString(2, userID);
            ps.setString(3, productId);
            ps.executeUpdate();
            return new CartItem(userID, productId, count);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    //Removes the cart item.


    public static boolean deleteCartItem(String customerId, String productId) {
        String query = "DELETE FROM cart_items WHERE idCustomer = ? AND idProduct = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, customerId);
            ps.setString(2, productId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}