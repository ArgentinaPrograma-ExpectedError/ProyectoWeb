package controller.attractions;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Attraction;
import model.User;
import persistence.commons.DAOFactory;
import services.ItinerarioService;

@WebServlet("/itinerario/index.do")
public class ListItinerarioServlet extends HttpServlet implements Servlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -94285164660687178L;
	private ItinerarioService itinerarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.itinerarioService = new ItinerarioService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		User user= DAOFactory.getUserDAO().find(id);
		List<Attraction> attractions = itinerarioService.list(id);
	    req.setAttribute("user", user);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/itinerario/index.jsp");
		dispatcher.forward(req, resp);
		

	}

}

