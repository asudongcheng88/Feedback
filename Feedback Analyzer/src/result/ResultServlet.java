package result;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

/**
 * Servlet implementation class ResultServlet
 */
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		
		String adminId = (String) session.getAttribute("x");
		
		String[] progName= request.getParameterValues("progName[]");
		
		String progName_1 = progName[0];
		String progName_2 = progName[1];
		
		ResultDAO resultDAO = new ResultDAO();
		
		List<Result> result_1 = new ArrayList<Result>();
		List<Result> result_2 = new ArrayList<Result>();
		
		

		
		int totalRespondent_1 = resultDAO.getTotalRespondent(progName_1, adminId);
		int totalRespondent_2 = resultDAO.getTotalRespondent(progName_2, adminId);
		
		result_1 = resultDAO.getAveragePoint(totalRespondent_1, progName_1);
		result_2 = resultDAO.getAveragePoint(totalRespondent_2, progName_2);
		
		String json1 = gson.toJson(result_1);
		String json2 = gson.toJson(result_2);
		
		String bigJson = json1 + "%" + json2;
		
		out.print(bigJson);
		out.flush();
		out.close();
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
