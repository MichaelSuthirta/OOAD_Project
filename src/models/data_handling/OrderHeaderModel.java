package models.data_handling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import models.entity_models.AllOrdersRow;

import models.entity_models.OrderHeader;

public class OrderHeaderModel {

    private static Connection conn = DatabaseConnector.getConnection();

    //Creates an order header.


    public static OrderHeader createOrderHeader(String idCustomer, String idPromo, double totalAmount) {
        String query = " INSERT INTO orderheader(idCustomer, idPromo, status, orderedAt, totalAmount) VALUES(?, ?, 'Pending', NOW(), ?)";

        try {
			PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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

    //Returns order header.


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

    /**
     * Edits the order header status.
     *
     * @param idOrder the order ID.
     * @param status the status.
     *
     * @return true if the operation succeeds; otherwise, false.
     */

    public static boolean editOrderHeaderStatus(String idOrder, String status) {
        String query = "UPDATE orderheader SET status = ? WHERE idOrder = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setString(2, idOrder.trim());

            int updated = ps.executeUpdate();
            System.out.println("[DEBUG] editOrderHeaderStatus idOrder=" + idOrder + " status=" + status + " updatedRows=" + updated);
            return updated > 0;

        } catch (SQLException e) {
            System.out.println("[DEBUG] editOrderHeaderStatus ERROR");
            e.printStackTrace();
            return false;
        }
    }

    
    /**
     * Updates the total amount.
     *
     * @param idOrder the order ID.
     * @param totalAmount the total amount.
     */

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


/**
 * Returns order headers by customer.
 *
 * @param idCustomer the customer ID.
 *
 * @return the java.util.List<OrderHeader> result; may be {@code null} if the operation fails or no data is found.
 */

public static java.util.List<OrderHeader> getOrderHeadersByCustomer(String idCustomer) {
    String query = "SELECT * FROM orderheader WHERE idCustomer = ? ORDER BY orderedAt DESC";
    java.util.List<OrderHeader> list = new java.util.ArrayList<>();

    try {
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, idCustomer);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(new OrderHeader(
                rs.getString("idOrder"),
                rs.getString("idCustomer"),
                rs.getString("idPromo"),
                rs.getString("status"),
                rs.getTimestamp("orderedAt"),
                rs.getDouble("totalAmount")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}



    /**
     * Returns all orders for admin.
     *
     * @return the List<AllOrdersRow> result; may be {@code null} if the operation fails or no data is found.
     */

    public static List<AllOrdersRow> getAllOrdersForAdmin() {
        List<AllOrdersRow> list = new ArrayList<>();
        String query = "SELECT oh.idOrder, oh.idCustomer, u.fullName AS customerName, oh.totalAmount, oh.status, oh.orderedAt " +
                       "FROM orderheader oh " +
                       "JOIN users u ON u.idUser = oh.idCustomer " +
                       "ORDER BY oh.orderedAt DESC";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String customer = rs.getString("customerName") + " (ID " + rs.getString("idCustomer") + ")";
                list.add(new AllOrdersRow(
                    rs.getString("idOrder"),
                    customer,
                    rs.getDouble("totalAmount"),
                    rs.getString("status"),
                    rs.getTimestamp("orderedAt")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}