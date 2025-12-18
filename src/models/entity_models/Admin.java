package models.entity_models;

public class Admin extends User{
	private String emergencyContact;
	
	// Creates a new Admin instance.


	public Admin(String idUser, String fullName, String email, String password, String phone, String address, 
			String gender, String contact) {
		super(idUser, fullName, email, password, phone, address, gender, "admin");
		this.emergencyContact = contact;
	}
	
}