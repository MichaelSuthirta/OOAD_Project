package models.data_handling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.entity_models.Admin;
import models.entity_models.Customer;
import models.entity_models.User;

public class UserModel {
	static Connection conn = DatabaseConnector.getConnection();
	
	public static int findIDByEmail(String email) {
		String query = "SELECT * FROM users WHERE 'email'= ?";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, email);
			ResultSet result = st.executeQuery();
			return result.getInt("id");
		} catch (SQLException e) {
			System.out.println("Failed to prepare query");
			e.printStackTrace();
		}
		return -1;
	}
	
	public static User findUserByID(int id) {
		String query = "SELECT * FROM users WHERE 'id'= ?";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, id);
			ResultSet result = st.executeQuery();
			return new User(
					Integer.toString(result.getInt("idUser")),
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
	
	public static Customer insertUser(String username, String email, String password, String phone,
			String address, String gender) {
		String query = "INSERT INTO users(fullName, email, password, phone, address, gender, role, balance) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, 0)";
		
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, username);
			st.setString(2, email);
			st.setString(3, password);
			st.setString(4, phone);
			st.setString(5, address);
			st.setString(6, gender);
			st.setString(7, "customer");
			
			int res = st.executeUpdate();
			
			if(res == 1) {
				return new Customer(
						Integer.toString(findIDByEmail(email)),
						username,
						email,
						password,
						phone,
						address,
						gender,
						0
						);
			}
			else return null;
		} catch (SQLException e) {
			System.out.println("Failed to prepare query");
			e.printStackTrace();
		}
		return null;
	}
	
	public static Customer topUp(int userID, double topUpAmt) {
		User user = findUserByID(userID);
		
		if(user.getRole().toLowerCase().equals("customer")) {
			Customer customer = (Customer) user;
			customer.setBalance(customer.getBalance() + topUpAmt);
			
			String query = "UPDATE users SET balance = ? WHERE idUser = ?";
			
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setDouble(1, customer.getBalance());
				ps.setInt(2, userID);
				int affectedRows = ps.executeUpdate();
				
				if(affectedRows == 0) {
					System.out.println("Failed to top up");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			
			return customer;
		}
		
		else {
			System.out.println("This user isn't a customer, cannot top up.");
			return null;
		}
	}
	
	public static Customer editProfile(int userID, String fullName, String phone, String address) {
	    User user = findUserByID(userID);

	    if(user == null || !user.getRole().equalsIgnoreCase("customer")) {
	        System.out.println("This user isn't a customer.");
	        return null;
	    }

	    String query = "UPDATE users SET fullName = ?, phone = ?, address = ? WHERE idUser = ?";

	    try {
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setString(1, fullName);
	        ps.setString(2, phone);
	        ps.setString(3, address);
	        ps.setInt(4, userID);

	        int affectedRows = ps.executeUpdate();
	        if(affectedRows > 0) {
	            return (Customer) findUserByID(userID);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return UserModel.editProfile(userID, fullName, phone, address);
	}
	
	
	
}
