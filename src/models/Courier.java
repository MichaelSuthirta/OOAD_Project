package models;

public class Courier extends User{

	private String vehicleType, vehiclePlate;
	
	public Courier(String idUser, String fullName, String email, String password, String phone, String address,
			String gender, String vehicleType, String vehiclePlate) {
		super(idUser, fullName, email, password, phone, address, gender, "courier");
		this.vehiclePlate = vehiclePlate;
		this.vehicleType = vehicleType;
	}

}
