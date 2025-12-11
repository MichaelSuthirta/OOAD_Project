package models;

public class Customer extends User{
	private double balance;
	
	public Customer(String idUser, String fullName, String email, String password, String phone, String address,
			String gender, double balance) {
		super(idUser, fullName, email, password, phone, address, gender, "customer");
		this.balance = 0;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	
	
}
