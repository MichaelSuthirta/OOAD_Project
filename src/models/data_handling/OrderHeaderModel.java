package models.data_handling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.entity_models.OrderHeader;

public class OrderHeaderModel {

    private static Connection conn = DatabaseConnector.getConnection();

    public static OrderHeader createOrderHeader(String idCustomer, String idPromo, double totalAmount) {
        String query = " INSERT INTO orderheader(idCustomer, idPromo, status, orderedAt, totalAmount) VALUES(?, ?, 'Pending', NOW(), ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, idCustomer);

            if (idPromo == null) {
                ps.setNull(2, java.sql.Types.INTEGER);
            } else {
                ps.setString(2, idPromo);
            }

            ps.setDouble(3, totalAmount);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return getOrderHeader(String.valueOf(rs.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static OrderHeader getOrderHeader(String idOrder) {
        String query = "SELECT * FROM orderheader WHERE idOrder = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, idOrder);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new OrderHeader(
                    rs.getString("idOrder"),
                    rs.getString("idCustomer"),
                    rs.getString("idPromo"),
                    rs.getString("status"),
                    rs.getDate("orderedAt"),
                    rs.getDouble("totalAmount")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean editOrderHeaderStatus(String idOrder, String status) {
        String query = "UPDATE orderheader SET status = ? WHERE idOrder = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, status);
            ps.setString(2, idOrder);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void updateTotalAmount(String idOrder, double totalAmount) {
        String query = "UPDATE orderheader SET totalAmount = ? WHERE idOrder = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1, totalAmount);
            ps.setString(2, idOrder);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
