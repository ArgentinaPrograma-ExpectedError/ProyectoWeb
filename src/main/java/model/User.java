package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import persistence.UserDAO;
import persistence.commons.DAOFactory;
import utils.Crypt;

public class User {

	private Integer id;
	private String username, password;
	private Boolean admin;
	private Integer coins;
	private Double time;
	private Integer dinero_gastado;
	private Double tiempo_gastado;
	
	private String type;
	private Map<String, String> errors;

	public User(Integer id, String username, String type, Integer coins, Double time, String password, Boolean admin,Integer dinero_gastado,Double tiempo_gastado) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.coins = coins;
		this.time = time;
		this.type = type;
		this.admin = admin;
		this.dinero_gastado=dinero_gastado;
		this.tiempo_gastado=tiempo_gastado;
		
	}

	public void addToItinerary(Suggestion suggestion) {
		this.coins -= suggestion.getCost();
		this.time -= suggestion.getDuration();
		this.dinero_gastado+=suggestion.getCost();
		this.tiempo_gastado+=suggestion.getDuration();
	}

	public Integer getDinero_gastado() {
		return dinero_gastado;
	}

	public void setDinero_gastado(Integer dinero_gastado) {
		this.dinero_gastado = dinero_gastado;
	}

	public Double getTiempo_gastado() {
		return tiempo_gastado;
	}

	public void setTiempo_gastado(Double tiempo_gastado) {
		this.tiempo_gastado = tiempo_gastado;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public boolean canAfford(Suggestion suggestion) {
		return suggestion.getCost() <= this.coins;
	}

	public boolean canAttend(Suggestion suggestion) {
		return suggestion.getDuration() <= this.time;
	}

	public boolean canBuy(Integer id) {
		UserDAO userDAO = DAOFactory.getUserDAO();
		Attraction attraction = DAOFactory.getAttractionDAO().find(id);
		List<String> compradas = userDAO.cargarAtraccionesCompradas(this);
		if (compradas.contains(attraction.getName())) {
			return false;
		}
		return true;

	}

	public boolean canBuyPromotion(Integer id) {
		UserDAO userDAO = DAOFactory.getUserDAO();
		List<Attraction> attractions = DAOFactory.getPromotionDAO().find(id).getAttractions();
		List<String> compradas = userDAO.cargarAtraccionesCompradas(this);
		for (Attraction a : attractions) {
			for (String s : compradas) {
				if (s.equals(a.getName())) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkPassword(String password) {
		// this.password en realidad es el hash del password
		return Crypt.match(password, this.password);
	}

	public Integer getCoins() {
		return coins;
	}

	public Integer getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public Double getTime() {
		return time;
	}

	public String getUsername() {
		return username;
	}

	public boolean isNull() {
		return false;
	}

	public Boolean isAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public void setCoins(Integer coins) {
		this.coins = coins;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = Crypt.hash(password);
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", admin=" + admin + ", coins="
				+ coins + ", time=" + time + ", type=" + type + ", errors=" + errors + "]";
	}

	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}

	public void validate() {
		errors = new HashMap<String, String>();

		if (coins <= 0) {
			errors.put("coins", "Debe ser positivo");
		}
		if (time <= 0.0) {
			errors.put("time", "Debe ser positivo");
		}
		if (!type.equals("AVENTURA") && !type.equals("DEGUSTACION") && !type.equals("PAISAJE")) {
			errors.put("type", "Debe ser un tipo válido");
		}

	}

	public Map<String, String> getErrors() {
		return errors;
	}

}
