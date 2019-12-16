package org.ivesonchen.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ivesonchen.model.User;
import org.ivesonchen.services.ShoppingCartServices;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		
		ShoppingCartServices service = new ShoppingCartServices();
		int result = service.addUserRecord(new User(firstName, lastName, password));
		if(result == -1 || result == 0) {
			response.sendRedirect("error.jsp");
		} else {
			request.setAttribute("message", "Account created!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
