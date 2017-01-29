package admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import questions.Questionnaire;
import questions.SurveyDAO;
import respondent.Respondent;
import respondent.RespondentDAO;

/**
 * Servlet implementation class AdLoginServlet
 */
@WebServlet("/AdLoginServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Admin admin = new Admin();
		
		admin.setAdId(request.getParameter("id"));
		admin.setAdPass(request.getParameter("password"));
		
		admin = AdminDAO.login(admin);						//go to login method in AdminDAO
		
		HttpSession session = request.getSession(true);
		
		
		
		//admin login
		
		if(admin.isValid()){			//valid admin
			
			session.setAttribute("adminId", request.getParameter("id"));
			session.setAttribute("currentSessionUser",admin);
			
			session.setAttribute("x",request.getParameter("id"));	//passing id to Menu.html
			
			response.sendRedirect("Menu.html");						//go to Menu.html
		}

		
		//invalid password
		else{
			
			response.sendRedirect("LoginError.jsp");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//register admin
		
		Admin admin = new Admin();
		AdminDAO adminDAO = new AdminDAO();
				
		admin.setAdName(request.getParameter("name"));
		admin.setAdId(request.getParameter("id"));
		admin.setAdPass(request.getParameter("password"));
				
		adminDAO.register(admin);
				
		response.sendRedirect("LogIn.jsp");
	}
	

}
