package models.entity_models;

public class Delivery {
	private String idOrder, idCourier, status;

	//Creates a new Delivery instance.


	public Delivery(String idOrder, String idCourier, String status) {
		super();
		this.idOrder = idOrder;
		this.idCourier = idCourier;
		this.status = status;
	}
	

//Returns id order.


	public String getIdOrder() {
    return idOrder;
	}

//Sets the id order.


	public void setIdOrder(String idOrder) {
    this.idOrder = idOrder;
	}

//Returns id courier.

	public String getIdCourier() {
    return idCourier;
	}

//Sets the id courier.


	public void setIdCourier(String idCourier) {
    this.idCourier = idCourier;
	}

//Returns status.


	public String getStatus() {
    return status;
	}

//Sets the status.

	public void setStatus(String status) {
	this.status = status;
	}

}