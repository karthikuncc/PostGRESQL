
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uname = request.getParameter("uname");
		String password = request.getParameter("pwd");
		String errorMsg = null;
		
		if (uname == null || uname.equals("")) {
			errorMsg = "User Name can't be null or empty";
		}
		
		if (password == null || password.equals("")) {
			errorMsg = "Password can't be null or empty";
		}
		
		if (errorMsg != null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.html");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>" + errorMsg + "</font>");
			rd.include(request, response);
		} else {
			UserBean userBn = new UserBean();
			userBn.setUsername(uname);
			userBn.setPassword(password);
			boolean userFound = UserDAO.getUser(userBn);
			if (userFound) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<html><body>" +"Welcome " +  uname + "</body></html>");
			} else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.html");
				PrintWriter out = response.getWriter();
				out.println("<font color=red>No user found with given user id, please register first.</font>");
				rd.include(request, response);
			}
		}

	}
}
