package analyze;

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
import javax.swing.JOptionPane;

import com.google.gson.Gson;

import questions.Questionnaire;
import questions.SurveyDAO;
import response.ResponseDAO;
import result.ResultDAO;
import section.Section;
import section.SectionDAO;

/**
 * Servlet implementation class AnalyzeServlet
 */
@WebServlet("/AnalyzeServlet")
public class AnalyzeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnalyzeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		
		String action = request.getParameter("action");
		
		System.out.println("Action is "+action);
		
		String adminId = (String) session.getAttribute("x");
		
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		if(action.equalsIgnoreCase("Overall result")){
			
			//System.out.println("Inside overall result block");
			String setAttributeProgName = request.getParameter("analyzeProgName");
			System.out.println(setAttributeProgName);
			session.setAttribute("progName",setAttributeProgName);
			
			String progName = (String) session.getAttribute("progName");
			
			//System.out.println("Program name = "+progName);
			
			AnalyzeDAO analyzeDAO = new AnalyzeDAO();
			SectionDAO sectionDAO = new SectionDAO();
			
			List<Section> secArray = new ArrayList<Section>();
			List<Analyze> overallAnalysisArray = new ArrayList<Analyze>();

			secArray = sectionDAO.sectionList(progName);
			
			for(Section sec:secArray){
				
				String section = sec.getSecName();
				
				//System.out.println("Insert section name = "+section);
				
				Integer point = analyzeDAO.overallAnalysis(section, progName);
				
				//System.out.println("Point = "+point);
				if(point != 999999){
					Analyze analyze = new Analyze(point, section);
					
					overallAnalysisArray.add(analyze);
				}
	
			}
			
			boolean exist = analyzeDAO.isValidResponse(progName);
			List<Section> sectionList = new ArrayList<Section>();
			
			//System.out.println("Exist is "+exist);
			ResultDAO resultDAO = new ResultDAO();
			int totalRes = resultDAO.getTotalRespondent(progName, adminId);
			
				if(exist == true){
					System.out.println("Double JSON");
					sectionList = secArray;
					
					String json1 = gson.toJson(overallAnalysisArray);
					String json2 = gson.toJson(sectionList);
					String json3 = gson.toJson(Integer.toString(totalRes));
					String bigJson = json1 + "%"+ json2 +"%"+json3;
					
					out.print(bigJson);
					out.flush();
					out.close();
					
				}else{
					//System.out.println("Single JSON");
					out.print(gson.toJson(overallAnalysisArray));
					out.flush();
					out.close();
					
				}
	
		
		}else if(action.equalsIgnoreCase("overall result menu")){
			
			
			String progName = (String) session.getAttribute("progName");
			
			//System.out.println("Program name = "+progName);
			
			AnalyzeDAO analyzeDAO = new AnalyzeDAO();
			SectionDAO sectionDAO = new SectionDAO();
			
			List<Section> secArray = new ArrayList<Section>();
			List<Analyze> overallAnalysisArray = new ArrayList<Analyze>();

			secArray = sectionDAO.sectionList(progName);
			
			for(Section sec:secArray){
				
				String section = sec.getSecName();
				
				//System.out.println("Insert section name = "+section);
				
				Integer point = analyzeDAO.overallAnalysis(section, progName);
				
				//System.out.println("Point = "+point);
				if(point != 999999){
					Analyze analyze = new Analyze(point, section);
					
					overallAnalysisArray.add(analyze);
				}
	
			}
			
			boolean exist = analyzeDAO.isValidResponse(progName);
			List<Section> sectionList = new ArrayList<Section>();
			
			//System.out.println("Exist is "+exist);
			
				if(exist == true){
					System.out.println("Double JSON");
					sectionList = secArray;
					
					String json1 = gson.toJson(overallAnalysisArray);
					String json2 = gson.toJson(sectionList);
					String bigJson = json1 + "%"+ json2;
					
					out.print(bigJson);
					out.flush();
					out.close();
					
				}else{
					//System.out.println("Single JSON");
					out.print(gson.toJson(overallAnalysisArray));
					out.flush();
					out.close();
					
				}
		}
		
		else if(action.equalsIgnoreCase("section")) {
			
			//System.out.println("Inside GO block");
			
			String progName = (String) session.getAttribute("progName");
			String section = request.getParameter("section");
			
			//System.out.println("Program name = "+progName);
			//System.out.println("Section = "+section);
			
			SurveyDAO surveyDAO = new SurveyDAO();
			
			List<Questionnaire> questArray = new ArrayList<Questionnaire>();
			
			questArray = surveyDAO.questionListBasedOnSection(section, progName);
			
			out.print(gson.toJson(questArray));
			out.flush();
			out.close();
		}
		
		else if(action.equalsIgnoreCase("questionResult")){
			
			String progName = (String) session.getAttribute("progName");
			String section = request.getParameter("section");
			String sign = request.getParameter("sign");
			String question = request.getParameter("question");
			
			//System.out.println("Sign = "+sign);

			
			AnalyzeDAO analyzeDAO = new AnalyzeDAO();
			List<Analyze> questAnalysisArray = new ArrayList<Analyze>();
			
				if(sign.equalsIgnoreCase("Positive")){
					
					//System.out.println("Inside positive block");

					questAnalysisArray = analyzeDAO.questPositiveAnalysisResult(section, progName, question, sign);
					
				}
				else if(sign.equalsIgnoreCase("Negative")){
					
					//System.out.println("Inside negative block");

					questAnalysisArray = analyzeDAO.questNegativeAnalysisResult(section, progName, question, sign);
					
				}
			
			//Object[] data={};
			//data[0] = gson.toJson(questAnalysisArray);
			//data[1] = gson.toJson(sectionList);
			
			out.print(gson.toJson(questAnalysisArray));
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
