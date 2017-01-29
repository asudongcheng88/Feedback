package program;

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

import questions.Questionnaire;
import questions.SurveyDAO;
import multipleData.ProgramAndQuestions;

import com.google.gson.Gson;

/**
 * Servlet implementation class ProgramServlet
 */
@WebServlet("/ProgramServlet")
public class ProgramServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProgramServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		/***************list all the question based on clicked program******************/
		
		PrintWriter out = response.getWriter();
		Program bean = new Program();
		SurveyDAO surveyDAO = new SurveyDAO();
		Gson gson = new Gson();
		
		
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("list program")){
			
			//********************************************************session.removeAttribute("ProgramProgName");
			
			String progName = request.getParameter("progName");
			//System.out.println(progName);
			//********************************************************session.setAttribute("progName", progName);
			
			/***************** return question list for PROGRAM and ANALYZE Ajax **********************/
			
			bean.setProgName(progName);
				
			//method in SurveyDAO
				
			List<ProgramAndQuestions> questList = surveyDAO.selectAllQuestForProgram(bean);
		
			out.print(gson.toJson(questList)); 
			
			
		}else if(action.equalsIgnoreCase("check program")){
			
			ProgramDAO programDAO = new ProgramDAO();
			
			HttpSession session = request.getSession(true);
			
			String adminId =  (String) session.getAttribute("x");
			
			boolean exist = programDAO.isCreated(adminId);
			
			out.print(gson.toJson(exist)); 
			
		}
			
		
		
			out.flush();
			out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/**************create new program for admin********************/
		
		Program prog = new Program();
		
		SurveyDAO surveyDAO = new SurveyDAO();
		
		HttpSession session = request.getSession(true);
		
		//System.out.println(String.valueOf(session.getAttribute("x")));
		
		//String progDate = 
		
		prog.setAdId(String.valueOf(session.getAttribute("x")));		//set admin id value to bean
		prog.setProgId(request.getParameter("code"));
		prog.setProgName(request.getParameter("name"));
		prog.setProgDate(request.getParameter("date"));
		prog.setProgDescription(request.getParameter("description"));
		
		surveyDAO.createProg(prog);						/*
															create program
															createProg in SurveyDAO
														*/
		
		
		session.setAttribute("createdCode", request.getParameter("code"));	//passing prog id value to CreateSurvey.jsp
		session.setAttribute("createdName", request.getParameter("name"));
		
		response.sendRedirect("CreateSurvey.jsp");				//go to CreateSurvey.jsp
	}

}
