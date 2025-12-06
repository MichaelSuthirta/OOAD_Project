package controller;

import models.User;

public class UserController {

	//Customer is the one who can register
	public static User registerUser(String name, String email, String password, String phone,
			String address, String gender) {
		
		//Insert data into DB
		return null;
	}
	
	public static User getUserByEmail(String email) {
		//find email in DB
		return null;
	}
	
	public static User login(String email, String password) {
		User user = getUserByEmail(email);
		
		if(user == null) {
			return null;
		}
		else {
			//Validate password, create user, return user
			return null;
		}
	}
}
