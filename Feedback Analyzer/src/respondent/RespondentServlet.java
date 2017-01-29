package respondent;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import com.upload.UploadServlet;

import program.Program;
import program.ProgramDAO;
import questions.Questionnaire;
import questions.SurveyDAO;

/**
 * Servlet implementation class RespondentLogInServlet
 */
@WebServlet("/RespondentServlet")

public class RespondentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RespondentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Respondent res = new Respondent();
		HttpSession resSession = request.getSession(true);
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		res.setResId(id);
		res.setResPass(password);
		
		res = RespondentDAO.login(res);
		
		if(res.isValid()){
			
			
			resSession.setAttribute("resId", id);
			resSession.setAttribute("progId", password);
			//request.setAttribute("progId", res.getResPass());
			
			String progId = password;
			
			SurveyDAO surveyDAO = new SurveyDAO();
			List<Questionnaire> questList = surveyDAO.selectAllQuest(progId);

			request.setAttribute("questList",questList);		//send array to SurveyForm.jsp
			
			

			request.getRequestDispatcher("SurveyForm.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		String ajaxUpdateResult = "";

		HttpSession session = request.getSession(true);
		
		String adminId = (String) session.getAttribute("x");
		
		RespondentDAO respondentDAO = new RespondentDAO();
		
		String progId = null;
		
			try {

	            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);            

	            for (FileItem item : items) {
	            	
	            	
	                if (item.isFormField()) {
	                	progId = item.getString();
	                	
	                    

	                } else {

	                    

	                    InputStream content = item.getInputStream();

	                    response.setContentType("application/x-www-form-urlencoded");

	                    response.setCharacterEncoding("UTF-8");

	                    respondentDAO.registerRespondent(content, progId, adminId);			
	                    
	                    ajaxUpdateResult += "Your respondents have successfully registered\n\r";
	                   
	                }

	            }

	        } catch (FileUploadException e) {

	            throw new ServletException("Parsing file upload failed.", e);

	        }
		  response.getWriter().print(ajaxUpdateResult);
  

		
		
		*/
		
	}

}
