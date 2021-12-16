package services;

import java.util.HashMap;
import java.util.Map;

import model.Attraction;
import model.User;
import persistence.AttractionDAO;
import persistence.UserDAO;
import persistence.commons.DAOFactory;

public class BuyAttractionService {

	AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
	UserDAO userDAO = DAOFactory.getUserDAO();

	public Map<String, String> buy(Integer userId, Integer attractionId) {
		Map<String, String> errors = new HashMap<String, String>();

		User user = userDAO.find(userId);
		Attraction attraction = attractionDAO.find(attractionId);

		if (!attraction.canHost(1)) {
			errors.put("attraction", "No hay cupo disponible");
		}
		if (!user.canAfford(attraction.getCost())) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.canAttend(attraction.getDuration())) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			user.addToItinerary(attraction);
		    
			userDAO.agregarAItinerario(user,attraction);	
		    userDAO.update(user);
		    
		    attraction.host(1);
			attractionDAO.update(attraction);
			
		}

		return errors;

	}

}
