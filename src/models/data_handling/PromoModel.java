package models.data_handling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.entity_models.Promo;

public class PromoModel {

	private static Connection conn = DatabaseConnector.getConnection();
	
	//Returns promo.

	public static Promo getPromo(String code) {
		String query = "SELECT * FROM promo WHERE code = ?";
		
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Promo(
	                    rs.getString("idPromo"),
	                    rs.getString("code"),
	                    rs.getString("headline"),
	                    rs.getDouble("discountPercentage")
	                );
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}