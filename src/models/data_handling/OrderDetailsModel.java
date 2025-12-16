package models.data_handling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.entity_models.OrderDetails;

public class OrderDetailsModel {

    private static Connection conn = DatabaseConnector.getConnection();

    public static boolean createOrderDetail(String idOrder, String idProduct, int qty) {
        String query = "INSERT INTO orderdetails(idOrder, idProduct, qty) VALUES(?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, idOrder);
            ps.setString(2, idProduct);
            ps.setInt(3, qty);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static OrderDetails getOrderDetail(String idOrder, String idProduct) {
        String query = "SELECT * FROM orderdetails WHERE idOrder = ? AND idProduct = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, idOrder);
            ps.setString(2, idProduct);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new OrderDetails(
                    rs.getString("idOrder"),
                    rs.getString("idProduct"),
                    rs.getInt("qty")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static OrderDetails saveOrderDetail() {
		return null;
    }
}
