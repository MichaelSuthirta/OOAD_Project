package controller;

import models.data_handling.UserModel;
import models.entity_models.Admin;
import models.entity_models.Customer;
import models.entity_models.User;

public class UserController {

	//Returns user by id.

	public static User getUserByID(String ID) {
		User result = UserModel.findUserByID(Integer.parseInt(ID));
		return result;
	}
	
	// Performs the register customer operation.


	public static Customer registerCustomer(String username, String email, String password, String confirmPass,
			String phone, String address, String gender) {
		
		//Validation
		if(username.isBlank() || email.isBlank() || password.isBlank() || phone.isBlank() || 
				address.isBlank() || gender.isBlank()) {
			System.out.println("Empty field(s)");
			return null;
		}
		if(!email.endsWith("@gmail.com")) {
			System.out.println("Invalid email");
			return null;
		}
		if(UserModel.findIDByEmail(email) != -1) {
			System.out.println("Email already registered");
			return null;
		}
		if(password.length() < 6) {
			System.out.println("Password is too short");
			return null;
		}
		if(!confirmPass.equals(password)) {
			System.out.println("Password doesn't match");
			return null;
		}
		
		//Check if phone is numeric
		try {
			Long.parseLong(phone);
		}
		catch(Exception e) {
			System.out.println("Phone is not numeric");
			return null;
		}
		if(phone.length() < 10 || phone.length() > 13) {
			System.out.println("Phone length invalid");
			return null;
		}
		
		Customer customer = UserModel.insertUser(username, email, password, phone, address, gender);
		
		return customer;
	}
	
	public static User loginUser(String email, String password) {
		int id = UserModel.findIDByEmail(email);
		
		if(id == -1) {
			return null;
		}
		
		User user = UserModel.findUserByID(id);
			
		if(user != null && user.getPassword().equals(password)) {
			return user;
		}
		else return null;
	}
	
	public static Customer topUpBalance(String userID, String topUpAmt) {
		double topUp;
		int id;
		
		//Parse user ID
		try {
			id = Integer.parseInt(userID);
		}
		catch(Exception e) {
			System.out.println("ID is wrong");
			return null;
		}
		
		//Parse top up
		try {
			topUp = Double.parseDouble(topUpAmt);
		}
		catch(Exception e) {
			System.out.println("Wrong input");
			return null;
		}
		
		if(topUp < 10000) {
			System.out.println("Top up must be at least 10,000");
			return null;
		}
		
		return UserModel.topUp(id, topUp);
	}
	
	public static String editProfile(int idUser, String fullName, String email,
            String password, String phone, String address) {

		if (fullName == null || fullName.trim().isEmpty()) return "Name must be filled!";
		if (email == null || !email.contains("@")) return "Email is invalid!";
		if (phone == null || phone.trim().isEmpty()) return "Phone must be filled!";
		if (address == null || address.trim().isEmpty()) return "Address must be filled!";

		Customer updated = UserModel.editProfile(
				idUser,
				fullName.trim(),
				email.trim(),
				password == null ? "" : password.trim(),
				phone.trim(),
				address.trim()
				);

		if (updated == null) {
			return "Failed to update profile!";
		}

		return null; // null means success
}

 
}