package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.User;

public class UserRepository {
	static Connection conn = DatabaseConnector.getConnection();
	
	public static User findUserByID(int id) {
		String query = "SELECT * FROM users WHERE 'email'=?";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, id);
			ResultSet result = st.executeQuery();
			return new User(
					Integer.toString(result.getInt("id")),
					result.getString("fullName"),
					result.getString("email"),
					result.getString("password"),
					result.getString("phone"),
					result.getString("address"),
					result.getString("gender"),
					result.getString("role")
					);
		} catch (SQLException e) {
			System.out.println("Failed to prepare query");
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean insertUser(User user) {
		String query = "INSERT INTO users(fullName, email, password, phone, address, gender, role) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, user.getFullName());
			st.setString(2, user.getEmail());
			st.setString(3, user.getPassword());
			st.setString(4, user.getPhone());
			st.setString(5, user.getAddress());
			st.setString(6, user.getGender());
			st.setString(7, user.getRole());
		} catch (SQLException e) {
			System.out.println("Failed to prepare query");
			e.printStackTrace();
		}
		return false;
	}
}
