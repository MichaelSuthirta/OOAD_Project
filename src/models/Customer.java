package models;

public class Customer extends User{
	private double balance;
	
	public Customer(String idUser, String fullName, String email, String password, String phone, String address, double balance) {
		super(idUser, fullName, email, password, phone, address, "user");
		this.balance = 0;
	}

	
}
