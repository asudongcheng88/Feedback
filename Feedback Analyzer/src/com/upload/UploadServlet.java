package com.upload;

import java.io.IOException;
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

import program.Program;
import program.ProgramDAO;

import com.upload.Excel;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static Logger LOGGER = 
            Logger.getLogger(UploadServlet.class.getCanonicalName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		String adminId = (String) session.getAttribute("x");
		
		List<Program> progArray = new ArrayList<Program>();
		ProgramDAO programDAO = new ProgramDAO();
		  
		progArray = programDAO.selectAllProg(adminId);
		  
		request.setAttribute("progIdList", progArray);
		
		request.getRequestDispatcher("RegisterRespondent.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		

		response.setContentType("text/html;charset=UTF-8");
		
		Excel excel = new Excel();
		final Part filePart = request.getPart("file");
		
		
		
		String progCode = request.getParameter("upload-prog-code");
		String adminId = (String) session.getAttribute("x");
		//System.out.println(subCode);
		final String fileName = excel.getFileName(filePart);
		
		String fileType = excel.getFileType(fileName);
		
		System.out.println(fileType);
		
		excel.uploadFile(filePart, fileType, progCode, adminId);
	}

}
