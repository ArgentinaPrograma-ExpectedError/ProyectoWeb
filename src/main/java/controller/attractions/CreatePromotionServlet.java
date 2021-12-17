package controller.attractions;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Promotion;
import services.PromotionService;

@WebServlet("/promotions/create.do")
public class CreatePromotionServlet extends HttpServlet {

	private static final long serialVersionUID = 7410926137357253937L;
	private PromotionService promotionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promotionService = new PromotionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promotions/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String promotionType = req.getParameter("promotionType");
		String name = req.getParameter("name");
		String attractionType = req.getParameter("attractionType");
		List<String> attractions = new LinkedList<String>(Arrays.asList(req.getParameter("attractions").split(",")));
		Integer valor = null;
		if (promotionType.equals("ABSOLUTA")) {
			valor = Integer.parseInt(req.getParameter("price"));
		}
		if (promotionType.equals("PORCENTUAL")) {
			valor = Integer.parseInt(req.getParameter("discount"));
		}
		String description = req.getParameter("description");
		Boolean eneable = Boolean.parseBoolean(req.getParameter("eneable"));

		String url= req.getParameter("url");
		
		Promotion promotion = promotionService.create(promotionType, name, attractionType, attractions, valor,
				description, eneable,url);
		if (promotion.isValid()) {
			resp.sendRedirect("/turismo/promotions/index.do");
		} else {
			req.setAttribute("promotion", promotion);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promotions/create.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
