

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String uname = request.getParameter("username");
		String password = request.getParameter("password");
		String name=request.getParameter("name");
		String country = request.getParameter("country");
		
		String errorMsg = null;
		
		if (uname == null || uname.isEmpty()) {
			errorMsg = "User Name can't be null or empty";
		}
		
		if (password == null || password.isEmpty()) {
			errorMsg = "Password can't be null or empty";
		}
		
		if(name == null || name.isEmpty()){
			errorMsg = "Name field can't be empty or null";
		}
		
		if(country == null || country.isEmpty() ){
			errorMsg="Country field can't be empty or null";
		}
		
		if (errorMsg != null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Register.html");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>" + errorMsg + "</font>");
			rd.include(request, response);
		} else {
			UserBean userBn = new UserBean();
			userBn.setUsername(uname);
			userBn.setPassword(password);
			userBn.setName(name);
			userBn.setCountry(country);
			boolean userFound = UserDAO.insertUser(userBn);
			if (userFound) {

				
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.html");
				PrintWriter out = response.getWriter();
				out.println("<font color=green>Registration Successful.</font>");
				rd.include(request, response);
				
				
			} else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.html");
				PrintWriter out = response.getWriter();
				out.println("<font color=red>Cannot be registered. Please try again.</font>");
				rd.include(request, response);
			}
		}
		
	}

}
