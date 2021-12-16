package controller.attractions;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Promotion;
import services.PromotionService;

@WebServlet("/promotions/edit.do")
public class EditPromotionServlet extends HttpServlet {

	private static final long serialVersionUID = -8461830711418950454L;
	private PromotionService promotionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promotionService = new PromotionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		Promotion promotion = promotionService.find(id);
		req.setAttribute("promotion", promotion);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promotions/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String promotionType = req.getParameter("promotionType");
		String name = req.getParameter("name");
		Integer valor = 0;
		if (promotionType.equals("ABSOLUTA")) {
			valor = Integer.parseInt(req.getParameter("price"));
		}
		if (promotionType.equals("PORCENTUAL")) {
			valor = Integer.parseInt(req.getParameter("discount"));
		}
		String description = req.getParameter("description");

		Promotion promotion = promotionService.update(id, name, valor, description);
		System.out.println(name + valor + description);

		if (promotion.isValid()) {
			resp.sendRedirect("/turismo/promotions/index.do");
		} else {
			req.setAttribute("promotion", promotion);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promotions/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
