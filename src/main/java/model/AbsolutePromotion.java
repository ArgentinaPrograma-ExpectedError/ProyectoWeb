package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbsolutePromotion extends Promotion {

	private int cost;
	private Map<String, String> errors;

	public AbsolutePromotion(Integer id, String name, String attractionType, String promotionType,
			List<Attraction> attractions, String description, Boolean enable, Integer cost) {
		super(id, name, attractionType, promotionType, attractions, description, enable);
		setCost(cost);
	}

	public boolean validCost(double valor) {
		return valor < super.getNetCost();
	}

	public void setCost(int valor) {
		if (validCost(valor)) {
			this.cost = valor;
		}
	}

	public int calculateCost() {
		return this.cost;
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

		if (!validCost(cost)) {
			errors.put("cost", "No puede ser mayor que el neto");
		}
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
}
