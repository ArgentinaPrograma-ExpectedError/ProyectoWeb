package persistence;

import java.util.List;

import model.Attraction;
import model.Suggestion;
import model.User;
import persistence.commons.GenericDAO;

public interface UserDAO extends GenericDAO<User> {

	public abstract User findByUsername(String username);
	public abstract void agregarAItinerario(User user, Suggestion suggestion);
	public abstract List<String> cargarAtraccionesCompradas(User user) ;
}
