package models.data_handling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.entity_models.Delivery;

public class DeliveryModel {

    private static Connection conn = DatabaseConnector.getConnection();

    // createDelivery(idOrder, idCourier)
    public static Delivery createDelivery(String idOrder, String idCourier) {
        String query = "INSERT INTO delivery(idOrder, idCourier, status) VALUES (?, ?, 'Pending')";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, idOrder);
            ps.setString(2, idCourier);
            ps.executeUpdate();

            return getDelivery(idOrder);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // getDelivery(idOrder)
    public static Delivery getDelivery(String idOrder) {
        String query = "SELECT * FROM delivery WHERE idOrder = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, idOrder);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Delivery(
                    rs.getString("idOrder"),
                    rs.getString("idCourier"),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // editDeliveryStatus(idOrder, idCourier, status)
    public static boolean editDeliveryStatus(String idOrder, String idCourier, String status) {
        String query = "UPDATE delivery  SET status = ? WHERE idOrder = ? AND idCourier = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, status);
            ps.setString(2, idOrder);
            ps.setString(3, idCourier);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
