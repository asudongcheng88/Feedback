package questions;

import java.io.BufferedReader;
import java.io.IOException;



import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import program.Program;
import section.Section;
import section.SectionDAO;

/**
 * Servlet implementation class QuestServlet
 */
@WebServlet("/QuestServlet")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		SurveyDAO surveyDAO = new SurveyDAO();
		
		Gson gson = new Gson();
		
		if(action.equals("List")){		//for list button
			
			String id = request.getParameter("currCode");
			
			PrintWriter out = response.getWriter();
			
			List<Questionnaire> questList = surveyDAO.selectAllQuest(id);
			
			//request.setAttribute("questList", questList);		//send array to CreateSurvey.jsp
			
			out.print(gson.toJson(questList));
			
		    //request.getRequestDispatcher("CreateSurvey.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//create questionnaire and list all the questions
		
		Questionnaire quest = new Questionnaire();	
		
		Section sec = new Section();
		SectionDAO secDAO = new SectionDAO();
		SurveyDAO surveyDAO = new SurveyDAO();
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession(true);

		//create questions for admin
		
		if(action.equals("Add")){			//for add button
			
			String[] question = request.getParameterValues("question[]");
			String []section = request.getParameterValues("section[]");
			
			System.out.println(question);
			
			for(int i=0; i<question.length; i++){
				
				System.out.println(section[i]);
				System.out.println(question[i]);
				
				quest.setSecName(section[i]);
				quest.setQuestText(question[i]);	//get parameter from html form
				quest.setProgId(String.valueOf(session.getAttribute("createdCode"))); //set bean value from the passed progId
				
				surveyDAO.createQuest(quest);		//create question
				
				sec.setSecName(section[i]);
				sec.setProgId(String.valueOf(session.getAttribute("createdCode")));
				
				boolean exist = secDAO.checkSection(sec);		//check whether section name already exist in SECTION table
				
				//System.out.println(sec.isExist());
				
				if(!exist){			//if not exist
				
					secDAO.insertSection(sec);			//insert into section table
				}
			}
		
			
			
			//response.sendRedirect("CreateSurvey.jsp");
			
		}
		
		
		
		

		
	
	    
	}

}
