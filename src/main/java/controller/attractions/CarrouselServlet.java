package controller.attractions;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Attraction;
import model.Ordenador;
import model.Promotion;
import model.Suggestion;
import model.User;
import services.AttractionService;
import services.PromotionService;

@WebServlet("/index.do")
public class CarrouselServlet extends HttpServlet implements Servlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4535650878263491893L;
	private PromotionService promotionService;
	private AttractionService attractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promotionService = new PromotionService();
		this.attractionService = new AttractionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Promotion> promotions = promotionService.list();
		List<Attraction> attractions = attractionService.list();
		List<Suggestion> suggestions = new LinkedList<Suggestion>();

		suggestions.addAll(attractions);
		suggestions.addAll(promotions);
		
		Collections.sort(suggestions, new Ordenador());

		suggestions = sugerir((User) req.getSession().getAttribute("user"), suggestions);


		req.setAttribute("suggestions", suggestions);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);

	}

	public List<Suggestion> sugerir(User u, List<Suggestion> sugerencias) {
		List<Suggestion> ordenadas = new LinkedList<Suggestion>();

		for (Suggestion s : sugerencias) {
			if (s instanceof Promotion && s.getAttractionType() == u.getType() && s.getCost() <= u.getCoins()
					&& s.getDuration() <= u.getTime() && u.canBuyPromotion(s.getId()) && s.canHost(s.getId())) {
				ordenadas.add(s);
			} else if (s instanceof Attraction && s.getAttractionType() == u.getType() && s.getCost() <= u.getCoins()
					&& s.getDuration() <= u.getTime() && u.canBuy(s.getId()) && s.canHost(s.getId())) {
				ordenadas.add(s);
				

			}
		}
		for (Suggestion s : sugerencias) {
			if (s instanceof Promotion && s.getAttractionType() != u.getType() && s.getCost() <= u.getCoins()
					&& s.getDuration() <= u.getTime() && u.canBuyPromotion(s.getId()) && s.canHost(s.getId())) {
				ordenadas.add(s);
			} else if (s instanceof Attraction && s.getAttractionType() != u.getType() && s.getCost() <= u.getCoins()
					&& s.getDuration() <= u.getTime() && u.canBuy(s.getId()) && s.canHost(s.getId())) {
				ordenadas.add(s);

			}
		}

		return ordenadas;
	}

}
