package controller;

import data_access.UserRepository;
import models.User;

public class UserController {

	public static User getUserByID(String ID) {
		User result = UserRepository.findUserByID(Integer.parseInt(ID));
		return result;
	}
}
