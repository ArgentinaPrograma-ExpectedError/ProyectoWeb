package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class Promotion implements Suggestion, Comparable<Promotion> {

	private Integer id;
	private String name;
	private String attractionType;
	private String promotionType;
	private List<Attraction> attractions;
	private String description;
	private Double duration;
	private Integer netCost;
	private Boolean enable;

	public Promotion(Integer id, String name, String attractionType, String promotionType, List<Attraction> attractions,
			String description, Boolean enable) {
		this.id = id;
		this.name = name;
		this.attractionType = attractionType;
		this.promotionType = promotionType;
		this.attractions = attractions;
		this.description = description;
		this.enable = enable;
		this.duration = this.calculateDuration();
		this.netCost = this.calculateNetCost();
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAttractionType() {
		return attractionType;
	}

	public List<Attraction> getAttractions() {
		return attractions;
	}

	public Integer getNetCost() {
		return netCost;
	}

	public Double getDuration() {
		return duration;
	}

	public String getDescription() {
		return description;
	}

	public Boolean getEnable() {
		return enable;
	}

	public String getPromotionType() {
		return promotionType;
	}

	public double calculateDuration() {
		double tiempo = 0;
		try {
			for (Attraction atraccion : attractions) {
				tiempo += atraccion.getDuration();
			}
			return tiempo;
		} catch (Exception e) {
			return tiempo;
		}
	}

	public int calculateNetCost() {
		int costo = 0;
		try {
			for (Attraction atraccion : attractions) {
				costo += atraccion.getCost();
			}
			return costo;
		} catch (Exception e) {
			return costo;
		}
	}

	public boolean canHost(int i) {
		for (Attraction a : this.attractions) {
			if (!a.canHost(i)) {
				return false;
			}
		}
		return true;
	}

	public List<String> getNameAttractions() {
		List<String> nombres = new LinkedList<String>();
		for (Attraction a : this.attractions) {
			nombres.add(a.getName());
		}
		return nombres;
	}

	public boolean validAttractions() {
		for (Attraction a : attractions) {
			if (!this.getAttractionType().equals(a.getAttractionType())) {
				return false;
			}
		}
		return true;
	}

	public abstract int calculateCost();

	public abstract Integer getCost();
	public abstract void setCost(int i);

	public abstract String getData();

	public int compareTo(Promotion otraPromocion) {
		if (this.getCost().compareTo(otraPromocion.getCost()) == 0) {
			return this.getDuration().compareTo(otraPromocion.getDuration());
		}
		return this.getCost().compareTo(otraPromocion.getCost());
	}

	@Override
	public int hashCode() {
		return Objects.hash(attractions, description, duration, enable, id, name, netCost, attractionType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promotion other = (Promotion) obj;
		return Objects.equals(attractions, other.attractions) && Objects.equals(description, other.description)
				&& Objects.equals(duration, other.duration) && Objects.equals(enable, other.enable)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(netCost, other.netCost) && Objects.equals(attractionType, other.attractionType);
	}

	public abstract boolean isValid();

	public void setName(String name2) {
		this.name = name2;

	}

	public void setDescription(String description2) {
		this.description = description2;
		
	}

}
