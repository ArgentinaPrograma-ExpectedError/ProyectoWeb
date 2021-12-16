package services;

import java.util.List;

import model.Attraction;
import persistence.commons.DAOFactory;

public class ItinerarioService {
	
	public List<Attraction> list(Integer id) {
		return DAOFactory.getAttractionDAO().findAttractions(id);
	}
}
