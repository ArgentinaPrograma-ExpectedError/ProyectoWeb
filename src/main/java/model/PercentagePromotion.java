package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PercentagePromotion extends Promotion {

	private int discount;
	private int cost;
	private Map<String, String> errors;

	public PercentagePromotion(Integer id, String name, String attractionType, String promotionType,
			List<Attraction> attractions, String description, Boolean enable, Integer discount,String url) {
		super(id, name, attractionType, promotionType, attractions, description, enable,url);
		this.setDiscount(discount);
		this.cost = this.calculateCost();
	}

	public int getDiscount() {
		try {
			return discount;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	private boolean descuentoCorrecto(double valor) {
		return valor < 100 && valor > 0;
	}

	private void setDiscount(int discount) {
		if (descuentoCorrecto(discount)) {
			this.discount = discount;
		}
	}

	public int calculateCost() {
		return (int) (super.calculateNetCost() * (100 - this.getDiscount()) / 100);
	}

	public Integer getCost() {
		return this.cost;
	}

	public String getData() {
		return super.getName() + "\nTipo: " + super.getAttractionType() + "\nAtracciones incluidas: "
				+ super.getAttractions() + "\nCosto: " + getCost() + " monedas de oro" + "\nDuración: "
				+ super.getDuration() + " horas";
	}

	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}

	public void validate() {
		errors = new HashMap<String, String>();

		if (!descuentoCorrecto(discount)) {
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

	@Override
	public void setCost(int i) {
		this.cost = i;

	}

	public Map<String, String> getErrors() {
		return errors;
	}
}
