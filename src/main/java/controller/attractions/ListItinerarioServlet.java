package controller.attractions;

import java.io.IOException;
import java.util.ArrayList;
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
import model.Itinerario;
import model.User;
import persistence.UserDAO;
import persistence.commons.DAOFactory;
import persistence.impl.UserDAOImpl;
import services.ItinerarioService;

@WebServlet("/itinerario/index.do")
public class ListItinerarioServlet extends HttpServlet implements Servlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7524968895244606690L;
	/**
	 * 
	 */
	private ItinerarioService itinerarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.itinerarioService = new ItinerarioService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * Integer id = Integer.parseInt(req.getParameter("id")); User user =
		 * DAOFactory.getUserDAO().find(id); req.setAttribute("user", user);
		 * List<Attraction> attractions = itinerarioService.list(id);
		 * 
		 */
		
		req.getSession().getAttribute("user");
		List<Attraction> attractions = 	UserDAOImpl.cargarAtraccionesItinerarioStatic((User)req.getSession().getAttribute("user"));
		req.getSession().setAttribute("attractions", attractions);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/itinerario/index.jsp");
		dispatcher.forward(req, resp);

	}

}
