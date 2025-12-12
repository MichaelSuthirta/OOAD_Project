package controller;

import data_access.UserRepository;
import models.Customer;
import models.User;

public class UserController {

	public static User getUserByID(String ID) {
		User result = UserRepository.findUserByID(Integer.parseInt(ID));
		return result;
	}
	
	public static Customer registerCustomer(String username, String email, String password, String confirmPass,
			String phone, String address, String gender) {
		
		//Validation
		if(username.isBlank() || email.isBlank() || password.isBlank() || phone.isBlank() || 
				address.isBlank() || gender.isBlank()) {
			System.out.println("Empty field(s)");
			return null;
		}
		if(!email.contains("@gmail.com")) {
			System.out.println("Invalid email");
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
			Integer.parseInt(phone);
		}
		catch(Exception e) {
			System.out.println("Phone is not numeric");
			return null;
		}
		
		Customer customer = UserRepository.insertUser(username, email, password, phone, address, gender);
		
		return customer;
	}
	
	public static User loginUser(String email, String password) {
		int id = UserRepository.findIDByEmail(email);
		
		if(id == -1) {
			return null;
		}
		
		User user = UserRepository.findUserByID(id);
		
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
		
		return UserRepository.topUp(id, topUp);
	}
}
