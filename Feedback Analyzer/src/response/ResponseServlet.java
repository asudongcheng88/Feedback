package response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import respondent.Respondent;

/**
 * Servlet implementation class ResponseServlet
 */
@WebServlet("/ResponseServlet")
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResponseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				
				ResponseDAO resDAO = new ResponseDAO();
				//System.out.println("Enter servlet");
				HttpSession resSession = request.getSession(true);
				
				String respondentId = (String) resSession.getAttribute("resId");
				String progId = (String) resSession.getAttribute("progId");
	
				String rawDataList[] = request.getParameterValues("res[]");
				String questIdList[] = request.getParameterValues("qId[]");
				
				for(int i=0; i<rawDataList.length; i++){
					
					String cleanData = resDAO.cleanData(rawDataList[i]);		//CLEAN the response
					
					int questId = Integer.parseInt(questIdList[i]);
					
					System.out.println("this is from servlet");
					System.out.println(rawDataList[i]);
					
					//System.out.println("Clean data = "+cleanData);
					
					Response bean = new Response();
							
					bean.setQuestId(questId);
					bean.setResId(respondentId);
					bean.setResponse(rawDataList[i]);
					bean.setCleanData(cleanData);	
			
					resDAO.insertResponse(bean);		//insert the response
					
					resDAO.insertLexiconData(progId,respondentId, questId);		//analyze the response
					
				}
				
				
				
		
	}

}
