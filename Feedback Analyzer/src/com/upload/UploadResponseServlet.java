package com.upload;

import java.io.IOException;
import java.io.InputStream;
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

import response.Response;
import response.ResponseDAO;
import multipleData.MultipleData;

/**
 * Servlet implementation class UploadResponseServlet
 */
@WebServlet("/UploadResponseServlet")
@MultipartConfig
public class UploadResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static Logger LOGGER = 
            Logger.getLogger(UploadServlet.class.getCanonicalName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadResponseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		

		response.setContentType("text/html;charset=UTF-8");
		
		Excel excel = new Excel();
		final Part filePart = request.getPart("file");
		
		
		
		//String progCode = request.getParameter("upload-prog-code");
		String adminId = (String) session.getAttribute("x");
		
		final String fileName = excel.getFileName(filePart);
		
		String fileType = excel.getFileType(fileName);
		
		InputStream filecontent = filePart.getInputStream();
		
		List<MultipleData> responseArray = excel.getMultipleResponse(filecontent, fileType);
		
		ResponseDAO resDAO = new ResponseDAO();
		
		for(MultipleData mulData:responseArray){
			
			String rawData1 = mulData.getQuest1();
			String rawData2 = mulData.getQuest2();
			String rawData3 = mulData.getQuest3();
			String rawData4 = mulData.getQuest4();
			String rawData5 = mulData.getQuest5();
			
			String cleanData1 = resDAO.cleanData(rawData1);
			String cleanData2 = resDAO.cleanData(rawData2);
			String cleanData3 = resDAO.cleanData(rawData3);
			String cleanData4 = resDAO.cleanData(rawData4);
			String cleanData5 = resDAO.cleanData(rawData5);
			
			String resId = mulData.getResId();
			
			String progId = mulData.getProgCode();
			
			Response res1 = new Response();
			
			res1.setQuestId(65);
			res1.setResId(resId);
			res1.setResponse(cleanData1);
			res1.setCleanData(cleanData1);
			
			resDAO.insertResponse(res1);
			
			resDAO.insertLexiconData(progId,resId, 65);	
			
			
			Response res2 = new Response();
			
			
			res2.setQuestId(66);
			res2.setResId(resId);
			res2.setResponse(cleanData2);
			res2.setCleanData(cleanData2);
			
			resDAO.insertResponse(res2);
			
			resDAO.insertLexiconData(progId,resId, 66);	
			
			
			Response res3 = new Response();
			
			res3.setQuestId(67);
			res3.setResId(resId);
			res3.setResponse(cleanData3);
			res3.setCleanData(cleanData3);
			
			resDAO.insertResponse(res3);
			
			resDAO.insertLexiconData(progId,resId, 67);	
			
			
			Response res4 = new Response();
			
			res4.setQuestId(68);
			res4.setResId(resId);
			res4.setResponse(cleanData4);
			res4.setCleanData(cleanData4);
			
			resDAO.insertResponse(res4);
			
			resDAO.insertLexiconData(progId,resId, 68);	
			
			
			Response res5 = new Response();
			
			res5.setQuestId(69);
			res5.setResId(resId);
			res5.setResponse(cleanData5);
			res5.setCleanData(cleanData5);
			
			resDAO.insertResponse(res5);
			
			resDAO.insertLexiconData(progId,resId, 69);	
			
		}

	}

}
