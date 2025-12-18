package models.entity_models;

public class Promo {
	private String idPromo, code, headline;
	private double discountPercentage;
	
	//Creates a new Promo instance.


	public Promo(String idPromo, String code, String headline, double discountPercentage) {
		super();
		this.idPromo = idPromo;
		this.code = code;
		this.headline = headline;
		this.discountPercentage = discountPercentage;
	}

	//Returns id promo.


	public String getIdPromo() {
		return idPromo;
	}

	//Sets the id promo.

	public void setIdPromo(String idPromo) {
		this.idPromo = idPromo;
	}

	//Returns code.


	public String getCode() {
		return code;
	}

	//Sets the code.


	public void setCode(String code) {
		this.code = code;
	}

	//Returns headline.


	public String getHeadline() {
		return headline;
	}

	//Sets the headline.

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	//Returns discount percentage.


	public double getDiscountPercentage() {
		return discountPercentage;
	}

	//Sets the discount percentage.


	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	
	
}