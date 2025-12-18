package models.data_handling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.entity_models.Courier;

public class CourierModel {

    private static Connection conn = DatabaseConnector.getConnection();

    // Returns courier by id.

    public static Courier getCourierByID(int idCourier) {
        String query = "SELECT * FROM users WHERE idUser = ? AND role = 'courier'";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idCourier);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Courier(
                    rs.getString("idUser"),
                    rs.getString("fullName"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getString("gender"),
                    rs.getString("vehicleType"),
                    rs.getString("vehiclePlate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //Returns all couriers.


    public static List<Courier> getAllCouriers() {
        List<Courier> couriers = new ArrayList<>();
        String query = "SELECT * FROM users WHERE role = 'courier'";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                couriers.add(new Courier(
                    rs.getString("idUser"),
                    rs.getString("fullName"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getString("gender"),
                    rs.getString("vehicleType"),
                    rs.getString("vehiclePlate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return couriers;
    }

}