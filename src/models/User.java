package models;

public abstract class User {
	private String idUser, fullName, email, password, phone, address, gender, role;

	public User(String idUser, String fullName, String email, String password, String phone, String address,
			String gender, String role) {
		super();
		this.idUser = idUser;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.gender = gender;
		this.role = role;
	}

	
}
