package respondent;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import program.Program;
import program.ProgramDAO;

/**
 * Servlet implementation class ListForRespondent
 */
@WebServlet("/ListForRespondentServlet")
public class ListForRespondentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListForRespondentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String progId = request.getParameter("progId");

		Gson gson = new Gson();
		
		if(action.equalsIgnoreCase("program name list")){
			
			
			ProgramDAO programDAO = new ProgramDAO();
			
			String progName = programDAO.progNameList(progId);
			
			PrintWriter out = response.getWriter();
			
			out.print(gson.toJson(progName));
			out.flush();
			out.close();
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
