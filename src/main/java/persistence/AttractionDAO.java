package persistence;

import java.util.List;

import model.Attraction;
import model.AttractionType;
import persistence.commons.GenericDAO;

public interface AttractionDAO extends GenericDAO<Attraction> {

	public List<AttractionType> findAttractionTypes();

	public List<Attraction> findAttractions(Integer id);

}