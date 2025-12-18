package models.data_handling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.entity_models.Admin;
import models.entity_models.Customer;
import models.entity_models.Courier;
import models.entity_models.User;

public class UserModel {
	static Connection conn = DatabaseConnector.getConnection();
	
	// Finds an idby email.


	public static int findIDByEmail(String email) {
	    String query = "SELECT idUser FROM users WHERE email = ?";
	    try {
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setString(1, email);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("idUser");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1;
	}


	
	// Finds an user by id.


	public static User findUserByID(int id) {
	    String query = "SELECT * FROM users WHERE idUser= ?";
	    try {
	        PreparedStatement st = conn.prepareStatement(query);
	        st.setInt(1, id);
	        ResultSet result = st.executeQuery();

	        if (result.next()) {
	            String role = result.getString("role");
	            String idUser = Integer.toString(result.getInt("idUser"));
	            String fullName = result.getString("fullName");
	            String email = result.getString("email");
	            String password = result.getString("password");
	            String phone = result.getString("phone");
	            String address = result.getString("address");
	            String gender = result.getString("gender");

	            if (role != null) {
	                switch (role.toLowerCase()) {
	                case "customer":
	                    return new Customer(idUser, fullName, email, password, phone, address, gender,
	                            result.getDouble("balance"));
	                case "admin":
	                    return new Admin(idUser, fullName, email, password, phone, address, gender,
	                            result.getString("emergencyContact"));
	                case "courier":
	                    return new Courier(idUser, fullName, email, password, phone, address, gender,
	                            result.getString("vehicleType"), result.getString("vehiclePlate"));
	                default:
	                    break;
	                }
	            }

	            // fallback bila role null / tidak dikenal
	            return new User(idUser, fullName, email, password, phone, address, gender, role);
	        }

	    } catch (SQLException e) {
	        System.out.println("Failed to prepare query");
	        e.printStackTrace();
	    }
	    return null;
	}

	
	//Performs the insert user operation.


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
		
		if(user instanceof Customer) {
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
	
	public static Customer editProfile(int idUser, String fullName, String email,
            String password, String phone, String address) {

			User user = findUserByID(idUser);

				if (user == null || !user.getRole().equalsIgnoreCase("customer")) {
					System.out.println("This user isn't a customer.");
					return null;
				}

			boolean changePassword = (password != null && !password.trim().isEmpty());

			String query = changePassword
					? "UPDATE users SET fullName=?, email=?, password=?, phone=?, address=? WHERE idUser=?"
							: "UPDATE users SET fullName=?, email=?, phone=?, address=? WHERE idUser=?";

			try {
				PreparedStatement ps = conn.prepareStatement(query);

				int i = 1;
				ps.setString(i++, fullName);
				ps.setString(i++, email);

				if (changePassword) ps.setString(i++, password);

				ps.setString(i++, phone);
				ps.setString(i++, address);
				ps.setInt(i++, idUser);

				int affectedRows = ps.executeUpdate();
				if (affectedRows > 0) {
					return (Customer) findUserByID(idUser); // return data terbaru
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
				return null;
			}

	
	
	public static double getCustomerBalance(int userID) {
	    String query = "SELECT balance FROM users WHERE idUser = ?";

	    try {
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, userID);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            return rs.getDouble("balance");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}

	public static boolean updateBalance(int userID, double newBalance) {
	    String query = "UPDATE users SET balance = ? WHERE idUser = ?";

	    try {
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setDouble(1, newBalance);
	        ps.setInt(2, userID);
	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	
	
}