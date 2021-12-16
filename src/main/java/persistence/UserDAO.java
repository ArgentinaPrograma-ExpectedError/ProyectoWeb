package persistence;

import java.util.List;

import model.Attraction;
import model.User;
import persistence.commons.GenericDAO;

public interface UserDAO extends GenericDAO<User> {

	public abstract User findByUsername(String username);
	public abstract int agregarAItinerario(User user, Attraction attraction);
	public abstract List<String> cargarAtraccionesCompradas(User user) ;
}
