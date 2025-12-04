package models;

public class Admin extends User{
	private String emergencyContact;
	
	public Admin(String idUser, String fullName, String email, String password, String phone, String address, String contact) {
		super(idUser, fullName, email, password, phone, address, "admin");
		this.emergencyContact = contact;
	}
	
}
