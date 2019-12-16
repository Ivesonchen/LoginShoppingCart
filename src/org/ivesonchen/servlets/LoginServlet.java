package org.ivesonchen.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ivesonchen.model.User;
import org.ivesonchen.services.ShoppingCartServices;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
//		System.out.println(firstName + " " + lastName + " " + password);
		
		ShoppingCartServices service = new ShoppingCartServices();
		int result = service.userAuthenticatation(new User(firstName, lastName, password));
		
		if(result == -1 || result == 0) {
			response.sendRedirect("error.jsp");
		} else {
			
			HttpSession session = request.getSession(true);
			session.setAttribute("userId", result);
			session.setAttribute("userFirstName", firstName);
			session.setAttribute("userLastName", lastName);
			session.setAttribute("userPassword", password);
			
			System.out.println("session: " + session.getAttribute("userId") );
			
			response.sendRedirect("store.jsp");
		}
	}

}
