package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AxBPromotion extends Promotion {

	private int cost;
	private Map<String, String> errors;

	public AxBPromotion(Integer id, String name, String attractionType, String promotionType, List<Attraction> attractions, String description,
			Boolean enable,String url) {
		super(id, name, attractionType, promotionType, attractions, description, enable,url);
		this.cost = this.calculateCost();
	}
	
	

	public int calculateCost() {
		List<Attraction> atracciones = super.getAttractions();
		Collections.sort(atracciones);
		try {
			Integer descuento = atracciones.get(0).getCost();
			return super.getNetCost() - descuento;
		} catch (Exception e) {
			return 0;
		}
	
	}

	public Integer getCost() {
		return this.cost;
	}

	public String getData() {
		return super.getName() + "\nTipo: " + super.getAttractionType() + "\nAtracciones incluidas: " + super.getNameAttractions()
				+ "\nCosto: " + this.getCost() + " monedas de oro" + "\nDuración: " + super.getDuration() + " horas";
	}

	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}

	public void validate() {
		errors = new HashMap<String, String>();

		if (!super.getPromotionType().equals("ABSOLUTA") && !super.getPromotionType().equals("AXB")
				&& !super.getPromotionType().equals("PORCENTUAL")) {
			errors.put("promotionType", "Debe ser un tipo válido");
		}
		if (!super.getAttractionType().equals("AVENTURA") && !super.getAttractionType().equals("DEGUSTACION")
				&& !super.getAttractionType().equals("PAISAJE")) {
			errors.put("attractionType", "Debe ser un tipo válido");
		}
		if (!validAttractions()) {
			errors.put("sameType", "Las atracciones deben ser del mismo tipo declarado.");
		}
	}

public Map<String, String> getErrors() {
	return errors;
}

	@Override
	public void setCost(int i) {
		this.cost = i;
	}
}
