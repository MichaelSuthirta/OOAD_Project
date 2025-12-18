package models.data_handling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.entity_models.Delivery;

public class DeliveryModel {

    private static Connection conn = DatabaseConnector.getConnection();

    //  Performs the assign courier to order operation.


    public static boolean assignCourierToOrder(String idOrder, String idCourier) {
        idOrder = idOrder.trim();
        idCourier = idCourier.trim();

        Delivery existing = getDelivery(idOrder);

        try {
            if (existing == null) {
                String insert = "INSERT INTO delivery(idOrder, idCourier, status) VALUES (?, ?, 'Pending')";
                PreparedStatement ps = conn.prepareStatement(insert);
                ps.setString(1, idOrder);
                ps.setString(2, idCourier);
                return ps.executeUpdate() > 0;
            } else {
                String update = "UPDATE delivery SET idCourier = ?, status = 'Pending' WHERE idOrder = ?";
                PreparedStatement ps = conn.prepareStatement(update);
                ps.setString(1, idCourier);
                ps.setString(2, idOrder);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Returns delivery.


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

    //  Edits the delivery status.
 

    public static boolean editDeliveryStatus(String idOrder, String idCourier, String status) {
        String query = "UPDATE delivery SET status= ? WHERE idOrder= ? AND idCourier= ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setString(2, idOrder);
            ps.setString(3, idCourier);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Returns assigned order ids.


    public static java.util.List<String> getAssignedOrderIds(String idCourier) {
        java.util.List<String> list = new java.util.ArrayList<>();
        String q = "SELECT idOrder FROM delivery WHERE idCourier = ?";

        try (PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setString(1, idCourier);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("idOrder"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


}