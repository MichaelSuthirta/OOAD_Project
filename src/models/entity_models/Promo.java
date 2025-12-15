package models.entity_models;

public class Promo {
	private String idPromo, code, headline;
	private double discountPercentage;
	
	public Promo(String idPromo, String code, String headline, double discountPercentage) {
		super();
		this.idPromo = idPromo;
		this.code = code;
		this.headline = headline;
		this.discountPercentage = discountPercentage;
	}
	
	
}
