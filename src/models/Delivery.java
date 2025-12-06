package models;

public class Delivery {
	private String idOrder, idCourier, status;

	public Delivery(String idOrder, String idCourier, String status) {
		super();
		this.idOrder = idOrder;
		this.idCourier = idCourier;
		this.status = status;
	}
	
}
