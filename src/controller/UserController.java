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
	
}
