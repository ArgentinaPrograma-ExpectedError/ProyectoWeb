package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import persistence.AttractionDAO;
import persistence.commons.DAOFactory;

public class Attraction implements Suggestion, Comparable<Attraction> {

	private Integer id;
	private String name;
	private Integer cost;
	private Double duration;
	private Integer capacity;
	private String attractionType;
	private String description;
	private Boolean enable;
	private Map<String, String> errors;

	public Attraction(Integer id, String name, Integer cost, Double duration, Integer capacity, String attractionType,
			String description, Boolean eneable) {
		super();
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.duration = duration;
		this.capacity = capacity;
		this.attractionType = attractionType;
		this.description = description;
		this.enable = eneable;
	}

	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}

	public void validate() {
		errors = new HashMap<String, String>();

		AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
		List<AttractionType> attractionTypes = attractionDAO.findAttractionTypes();
		List<String> types = new LinkedList<String>();
		for (AttractionType at : attractionTypes) {
			types.add(at.getType());
		}

		if (cost <= 0) {
			errors.put("cost", "Debe ser positivo");
		}
		if (duration <= 0) {
			errors.put("duration", "Debe ser positivo");
		}
		if (capacity <= 0) {
			errors.put("capacity", "Debe ser positivo");
		}
		if (!types.contains(attractionType)) {
			errors.put("attractionType", "Debe ser un tipo válido");
		}
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getAttractionType() {
		return attractionType;
	}

	public void setAttractionType(String attractionType) {
		this.attractionType = attractionType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean eneable) {
		this.enable = eneable;
	}

	public Boolean isEnable() {
		return enable;
	}

	@Override
	public String toString() {
		return "Attraction [id=" + id + ", name=" + name + ", cost=" + cost + ", duration=" + duration + ", capacity="
				+ capacity + "]";
	}

	public boolean canHost(int i) {
		return capacity >= i;
	}

	public void host(int i) {
		this.capacity -= i;
	}

	@Override
	public int hashCode() {
		return Objects.hash(capacity, cost, description, duration, enable, id, name, attractionType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attraction other = (Attraction) obj;
		return Objects.equals(capacity, other.capacity) && Objects.equals(cost, other.cost)
				&& Objects.equals(description, other.description) && Objects.equals(duration, other.duration)
				&& Objects.equals(enable, other.enable) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(attractionType, other.attractionType);
	}

	public int compareTo(Attraction otraAtraccion) {
		if (this.getCost().compareTo(otraAtraccion.getCost()) == 0) {
			return this.getDuration().compareTo(otraAtraccion.getDuration());
		}
		return this.getCost().compareTo(otraAtraccion.getCost());
	}

	@Override
	public List<Attraction> getAttractions() {
		List<Attraction> atracciones = new LinkedList<Attraction>();
		atracciones.add(this);
		return atracciones;
	}

}
