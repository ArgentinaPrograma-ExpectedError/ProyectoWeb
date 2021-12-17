package services;

import java.util.LinkedList;
import java.util.List;

import model.AbsolutePromotion;
import model.Attraction;
import model.AxBPromotion;
import model.PercentagePromotion;
import model.Promotion;
import persistence.PromotionDAO;
import persistence.commons.DAOFactory;

public class PromotionService {

	public List<Promotion> list() {
		return DAOFactory.getPromotionDAO().findAll();
	}

	public Promotion create(String promotionType, String name, String attractionType, List<String> atracciones,
			Integer valor, String description, Boolean state,String url) {

		List<Attraction> attractions = new LinkedList<Attraction>();
		for (String s : atracciones) {
			for (Attraction a : DAOFactory.getAttractionDAO().findAll()) {
				if (a.getName().equals(s)) {
					attractions.add(a);
				}
			}
		}

		Promotion promotion = null;

		if (promotionType.equals("ABSOLUTA")) {
			promotion = new AbsolutePromotion(-1, name, attractionType, promotionType, attractions, description, state,
					valor,url);
		}
		if (promotionType.equals("AXB")) {
			promotion = new AxBPromotion(-1, name, attractionType, promotionType, attractions, description, state,url);
		}
		if (promotionType.equals("PORCENTUAL")) {
			promotion = new PercentagePromotion(-1, name, attractionType, promotionType, attractions, description,
					state, valor,url);
		}

		if (promotion.isValid()) {
			PromotionDAO promotionDAO = DAOFactory.getPromotionDAO();
			promotionDAO.insert(promotion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return promotion;
	}

	public Promotion update(Integer id, String name, Integer valor, String description) {

		PromotionDAO promotionDAO = DAOFactory.getPromotionDAO();
		Promotion promotion = promotionDAO.find(id);

		promotion.setName(name);
		promotion.setCost(valor);
		promotion.setDescription(description);

		if (promotion.isValid()) {
			promotionDAO.update(promotion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return promotion;
	}

	public void delete(Integer id) {

		Promotion promotion = new AbsolutePromotion(id, null, null, null, null, null, null, 0,null);

		PromotionDAO promotionDAO = DAOFactory.getPromotionDAO();
		promotionDAO.delete(promotion);
	}

	public Promotion find(Integer id) {
		PromotionDAO promotionDAO = DAOFactory.getPromotionDAO();
		return promotionDAO.find(id);
	}

	public void enable(Integer id) {
		PromotionDAO promotionDAO = DAOFactory.getPromotionDAO();
		Promotion promotion = promotionDAO.find(id);
		promotionDAO.enable(promotion);

	}

}
