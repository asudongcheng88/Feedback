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

import section.Section;
import section.SectionDAO;

import com.google.gson.Gson;

/**
 * Servlet implementation class CompareServlet
 */
@WebServlet("/CompareServlet")
public class CompareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompareServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		
		
		
		String action = request.getParameter("action");
		
		//System.out.println(progName[0] + progName[1]);
		
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		if(action.equalsIgnoreCase("Overall result")){
			
			//System.out.println("Inside overall result block");
			String[] progName = request.getParameterValues("compareProgramName[]");
			
			String setAttributeProgName1 = progName[0];
			String setAttributeProgName2 = progName[1];
			
			session.setAttribute("progName1",setAttributeProgName1);
			session.setAttribute("progName2",setAttributeProgName2);
			
			String progName1 = (String) session.getAttribute("progName1");
			String progName2 = (String) session.getAttribute("progName2");
			
			//System.out.println("Program name = "+progName);
			
			AnalyzeDAO analyzeDAO = new AnalyzeDAO();
			SectionDAO sectionDAO = new SectionDAO();
			
			List<Section> secArray1 = new ArrayList<Section>();
			List<Analyze> overallAnalysisArray1 = new ArrayList<Analyze>();
			
			List<Section> secArray2 = new ArrayList<Section>();
			List<Analyze> overallAnalysisArray2 = new ArrayList<Analyze>();

			secArray1 = sectionDAO.sectionList(progName1);
			secArray2 = sectionDAO.sectionList(progName2);
			
			//for program 1
			
			for(Section sec:secArray1){
				
				String section1 = sec.getSecName();
				
				//System.out.println("Insert section name = "+section);
				
				Integer point1 = analyzeDAO.overallAnalysis(section1, progName1);
				
				//System.out.println("Point = "+point);
				if(point1 != 999999){
					Analyze analyze = new Analyze(point1, section1);
					
					overallAnalysisArray1.add(analyze);
				}
	
			}
			
			boolean exist1 = analyzeDAO.isValidResponse(progName1);
			List<Section> sectionList1 = new ArrayList<Section>();
			
			
			
			//for program 2
			
			for(Section sec:secArray2){
				
				String section2 = sec.getSecName();
				
				//System.out.println("Insert section name = "+section);
				
				Integer point2 = analyzeDAO.overallAnalysis(section2, progName2);
				
				//System.out.println("Point = "+point);
				if(point2 != 999999){
					Analyze analyze = new Analyze(point2, section2);
					
					overallAnalysisArray2.add(analyze);
				}
	
			}
			
			
			boolean exist2 = analyzeDAO.isValidResponse(progName2);
			List<Section> sectionList2 = new ArrayList<Section>();
			
			//System.out.println("Exist is "+exist);
			
				if(exist1 == true && exist2 == true){
					System.out.println("Double JSON");
					sectionList1 = secArray1;
					sectionList2 = secArray2;
					
					String json1 = gson.toJson(overallAnalysisArray1);
					String json2 = gson.toJson(sectionList1);
					
					String json3 = gson.toJson(overallAnalysisArray2);
					String json4 = gson.toJson(sectionList2);
					
					String bigJson = json1 + "%"+ json2 + "%" + json3 + "%" + json4;
					System.out.println(bigJson);
					out.print(bigJson);
					
					
				}else if(exist1 == true && exist2 == false){
					//System.out.println("Single JSON");
					out.print(gson.toJson(overallAnalysisArray2));
					
					
				}else if(exist2 == true && exist1 == false){
					//System.out.println("Single JSON");
					out.print(gson.toJson(overallAnalysisArray1));
					
					
				}
			out.flush();
			out.close();
		
		}else if(action.equalsIgnoreCase("questionResult")){
			
			String progName1 = (String) session.getAttribute("progName1");
			String progName2 = (String) session.getAttribute("progName2");
			
			String section = request.getParameter("section");
			String sign = request.getParameter("sign");
			String question = request.getParameter("question");
			
			//System.out.println("Sign = "+sign);

			
			AnalyzeDAO analyzeDAO = new AnalyzeDAO();
			List<Analyze> questAnalysisArray1 = new ArrayList<Analyze>();
			List<Analyze> questAnalysisArray2 = new ArrayList<Analyze>();
			
				if(sign.equalsIgnoreCase("Positive")){
					
					//System.out.println("Inside positive block");

					questAnalysisArray1 = analyzeDAO.questPositiveAnalysisResult(section, progName1, question, sign);
					questAnalysisArray2 = analyzeDAO.questPositiveAnalysisResult(section, progName2, question, sign);

					
				}
				else if(sign.equalsIgnoreCase("Negative")){
					
					//System.out.println("Inside negative block");

					questAnalysisArray1 = analyzeDAO.questNegativeAnalysisResult(section, progName1, question, sign);
					questAnalysisArray2 = analyzeDAO.questNegativeAnalysisResult(section, progName2, question, sign);

				}
			
			//Object[] data={};
			//data[0] = gson.toJson(questAnalysisArray);
			//data[1] = gson.toJson(sectionList);
				
			String json1 = gson.toJson(questAnalysisArray1);
			String json2 = gson.toJson(questAnalysisArray2);
			
			String bigJson = json1 + "%" + json2;
			
			out.print(bigJson);
			out.flush();
			out.close();
			
			
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
