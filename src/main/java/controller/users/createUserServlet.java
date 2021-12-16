package controller.users;

import java.io.IOException;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import services.UserService;

@WebServlet("/users/create.do")
public class createUserServlet  extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3731037095124112789L;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/users/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("username").toLowerCase();
		Integer coins = Integer.parseInt(req.getParameter("coins"));
		Double time = Double.parseDouble(req.getParameter("time"));
		String type = req.getParameter("type");
		Boolean isAdmin =  Boolean.parseBoolean(req.getParameter("isAdmin"));
		System.out.println(username+coins+time+type+isAdmin);
		User user = userService.create(username,type, coins, time,password , isAdmin);
		if (user.isValid()) {
			resp.sendRedirect("/turismo/users/index.do");
		} else {
			req.setAttribute("user", user);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/users/form.jsp");
			dispatcher.forward(req, resp);
		}

	}

}

