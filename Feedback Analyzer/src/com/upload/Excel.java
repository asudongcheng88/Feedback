package com.upload;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.Part;

import multipleData.MultipleData;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import respondent.Respondent;
import connectDB.DAO;



public class Excel {
	
	static DAO dao = new DAO();
	static Connection con = null;
	
	public String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
	private final static Logger LOGGER = 
            Logger.getLogger(UploadServlet.class.getCanonicalName());
	
	public String getFileType(String fileName){
		
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
		}
		
		return extension;
	}
	
	public void uploadFile(Part filePart, String fileType, String progId, String adminId) throws FileNotFoundException{
		
		// Create path components to save the file
		// final String path = request.getParameter("destination");

		List<Respondent> resArray = new ArrayList<Respondent>();		
		
		InputStream filecontent = null;
				
			try {
				
				filecontent = filePart.getInputStream();
				resArray = readStudentFileUpload(filecontent, fileType);
				
				if(!resArray.isEmpty()){
					
					insertStudentFileData(resArray, progId, adminId);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				
				if (filecontent != null) {
					try {
						filecontent.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			} 
	}
	
	@SuppressWarnings("deprecation")
	public List<Respondent> readStudentFileUpload(InputStream file, String fileType){
		
		List<Respondent> resArray = new ArrayList<Respondent>();
		
		try {
			
			if(fileType.equalsIgnoreCase("xls")){
				
				//Create Workbook instance holding reference to .xlsx file
				HSSFWorkbook xlsbook = new HSSFWorkbook(file);

		        //Get first/desired sheet from the workbook
		        HSSFSheet sheet = xlsbook.getSheetAt(0);
		        
		        //Iterate through each rows one by one
		        Iterator<Row> rowIterator = sheet.iterator();
		        
		     // Decide which rows to process
		        int rowStart = Math.min(10, sheet.getFirstRowNum()+1);
		        int rowEnd = Math.max(1400, sheet.getLastRowNum());
		        
		        System.out.println(rowStart);
		        System.out.println(rowEnd);

		        for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
		           Row r = sheet.getRow(rowNum);
		           if (r == null) {
		              // This whole row is empty
		              // Handle it as needed
		              continue;
		           }

		           int lastColumn = Math.max(r.getLastCellNum(), 100);
		           
		           Cell idCell = r.getCell(0);
		           //Cell nameCell = r.getCell(2);
		           Respondent respondent = new Respondent();
		           String resId = "";
		          
		           
		           /*
		           for (int cn = 0; cn < lastColumn; cn++) {
		              Cell c = r.getCell(cn, Row.RETURN_BLANK_AS_NULL);
		              if (c == null) {
		                 // The spreadsheet is empty in this cell
		              } else {
		            */	  	
		            	  switch (idCell.getCellType()) {
		            	  
			                    case Cell.CELL_TYPE_NUMERIC:
			                    	
			                    	
			                    	resId = Double.toString(idCell.getNumericCellValue());
			                    	BigDecimal bd = new BigDecimal(resId);
			                    	long longVal = bd.longValue();
			                    	resId = Long.toString(longVal);
			                    	respondent.setResId(resId);
			                        break;
			                        
			                    case Cell.CELL_TYPE_STRING:
			                        
			                    	respondent.setResId(idCell.getStringCellValue());
			                        break;
			                        
			                }
		            	  
		            	  /*
		            	  
		            	  switch (nameCell.getCellType()) {	
		            	  
			                    case Cell.CELL_TYPE_STRING:
			                    	
			                    	respondent.setStudName(nameCell.getStringCellValue());			                      
			                        break;
			                }
			                
			                */
		            	  
		            	  resArray.add(respondent);
		            	 
		              }
		              
		           
		      	}else if(fileType.equalsIgnoreCase("xlsx")){
				
					//Create Workbook instance holding reference to .xlsx file
					XSSFWorkbook xlsxbook = new XSSFWorkbook(file);
	
			        //Get first/desired sheet from the workbook
			        XSSFSheet sheet = xlsxbook.getSheetAt(0);
			        
			        //Iterate through each rows one by one
			        Iterator<Row> rowIterator = sheet.iterator();
			        
			     // Decide which rows to process
			        int rowStart = Math.min(10, sheet.getFirstRowNum()+1);
			        int rowEnd = Math.max(1400, sheet.getLastRowNum());
			        
			        System.out.println(rowStart);
			        System.out.println(rowEnd);
	
			        for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
			           Row r = sheet.getRow(rowNum);
			           if (r == null) {
			              // This whole row is empty
			              // Handle it as needed
			              continue;
			           }
	
			           int lastColumn = Math.max(r.getLastCellNum(), 100);
	
			           Cell idCell = r.getCell(0);
			           //Cell nameCell = r.getCell(2);
			           Respondent respondent = new Respondent();
			           String resId = "";
			          
			           
			           /*
			           for (int cn = 0; cn < lastColumn; cn++) {
			              Cell c = r.getCell(cn, Row.RETURN_BLANK_AS_NULL);
			              if (c == null) {
			                 // The spreadsheet is empty in this cell
			              } else {
			            */	  	
			            	  switch (idCell.getCellType()) {
			            	  
				                    case Cell.CELL_TYPE_NUMERIC:
				                       
				                    	resId = Double.toString(idCell.getNumericCellValue());
				                    	BigDecimal bd = new BigDecimal(resId);
				                    	long longVal = bd.longValue();
				                    	resId = Long.toString(longVal);
				                    	respondent.setResId(resId);
				                        break;
				                        
				                    case Cell.CELL_TYPE_STRING:
				                        
				                    	respondent.setResId(idCell.getStringCellValue());
				                        break;
				                        
				                }
			            	  /*
			            	  switch (nameCell.getCellType()) {	
			            	  
				                    case Cell.CELL_TYPE_STRING:
				                    	
				                    	student.setStudName(nameCell.getStringCellValue());			                      
				                        break;
				                }
			            	  */
			            	  resArray.add(respondent);
			        }
				
				
			}
	            

	        file.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		return resArray;
		
	}
	
	public void insertStudentFileData(List<Respondent> resArray, String progId, String adminId){
		
		
		con = dao.getConnection();
		
		String sql = "insert into Respondent (res_id, prog_id, ad_id) values (?, ?, ?)";
		
		PreparedStatement stmt = null;
		
		try {
			con.setAutoCommit(false);
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		try {
			stmt = con.prepareStatement(sql);
			
			for(Respondent st:resArray){
				
				stmt.setString(1, st.getResId());
				stmt.setString(2, progId);
				stmt.setString(3, adminId);
				stmt.addBatch();
			}
			
			stmt.executeBatch();
			con.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				stmt.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			dao.closeConnection(con);
			
		}
		
		
		
		
		
	}
	
	public List<MultipleData> getMultipleResponse(InputStream file, String fileType){
		
		List<MultipleData> multipleArray = new ArrayList<MultipleData>();
		
		
		try {
			
			if(fileType.equalsIgnoreCase("xls")){
				
				//Create Workbook instance holding reference to .xlsx file
				HSSFWorkbook xlsbook = new HSSFWorkbook(file);

		        //Get first/desired sheet from the workbook
		        HSSFSheet sheet = xlsbook.getSheetAt(0);
		        
		        //Iterate through each rows one by one
		        Iterator<Row> rowIterator = sheet.iterator();
		        
		     // Decide which rows to process
		        int rowStart = Math.min(10, sheet.getFirstRowNum()+1);
		        int rowEnd = Math.max(1400, sheet.getLastRowNum());
		        
		        System.out.println(rowStart);
		        System.out.println(rowEnd);

		        for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
		           Row r = sheet.getRow(rowNum);
		           if (r == null) {
		              // This whole row is empty
		              // Handle it as needed
		              continue;
		           }

		           int lastColumn = Math.max(r.getLastCellNum(), 100);
		           
		           Cell idCell = r.getCell(0);
		           Cell progCodeCell = r.getCell(1);
		           Cell response1Cell = r.getCell(2);
		           Cell response2Cell = r.getCell(3);
		           Cell response3Cell = r.getCell(4);
		           Cell response4Cell = r.getCell(5);
		           Cell response5Cell = r.getCell(6);
		           
		           MultipleData mulData = new MultipleData();
		           String resId = "";
		          
		           
		           /*
		           for (int cn = 0; cn < lastColumn; cn++) {
		              Cell c = r.getCell(cn, Row.RETURN_BLANK_AS_NULL);
		              if (c == null) {
		                 // The spreadsheet is empty in this cell
		              } else {
		            */	  	
		           switch (idCell.getCellType()) {
		            	  
		           	case Cell.CELL_TYPE_NUMERIC:
			                    	
			                    	
		           		resId = Double.toString(idCell.getNumericCellValue());
		           		BigDecimal bd = new BigDecimal(resId);
		           		long longVal = bd.longValue();
		           		resId = Long.toString(longVal);
		           		mulData.setResId(resId);
		           		break;
			                        
		           	case Cell.CELL_TYPE_STRING:
			                        
		           		mulData.setResId(idCell.getStringCellValue());
		           		break;
		           }
		            	  
		           mulData.setProgCode(progCodeCell.getStringCellValue());	
		           mulData.setQuest1(response1Cell.getStringCellValue());
		           mulData.setQuest2(response2Cell.getStringCellValue());
		           mulData.setQuest3(response3Cell.getStringCellValue());
		           mulData.setQuest4(response4Cell.getStringCellValue());
		           mulData.setQuest5(response5Cell.getStringCellValue());

			                
			                
		            	  
		           multipleArray.add(mulData);
		            	 
		              }
		              
		           
		      	}else if(fileType.equalsIgnoreCase("xlsx")){
				
					//Create Workbook instance holding reference to .xlsx file
					XSSFWorkbook xlsxbook = new XSSFWorkbook(file);
	
			        //Get first/desired sheet from the workbook
			        XSSFSheet sheet = xlsxbook.getSheetAt(0);
			        
			        //Iterate through each rows one by one
			        Iterator<Row> rowIterator = sheet.iterator();
			        
			     // Decide which rows to process
			        int rowStart = Math.min(10, sheet.getFirstRowNum()+1);
			        int rowEnd = Math.max(50, sheet.getLastRowNum());
			        
			        System.out.println(rowStart);
			        System.out.println(rowEnd);
	
			        for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
			           Row r = sheet.getRow(rowNum);
			           if (r == null) {
			              // This whole row is empty
			              // Handle it as needed
			              continue;
			           }
	
			           int lastColumn = Math.max(r.getLastCellNum(), 100);
	
			           Cell idCell = r.getCell(0);
			           Cell progCodeCell = r.getCell(1);
			           Cell response1Cell = r.getCell(2);
			           Cell response2Cell = r.getCell(3);
			           Cell response3Cell = r.getCell(4);
			           Cell response4Cell = r.getCell(5);
			           Cell response5Cell = r.getCell(6);
			           
			           MultipleData mulData = new MultipleData();
			           String resId = "";
			           String progId = "";
			          
			           
			           /*
			           for (int cn = 0; cn < lastColumn; cn++) {
			              Cell c = r.getCell(cn, Row.RETURN_BLANK_AS_NULL);
			              if (c == null) {
			                 // The spreadsheet is empty in this cell
			              } else {
			            */	  	
			           switch (idCell.getCellType()) {
			            	  
			           	case Cell.CELL_TYPE_NUMERIC:
				                    	
				                    	
			           		resId = Double.toString(idCell.getNumericCellValue());
			           		BigDecimal bd = new BigDecimal(resId);
			           		long longVal = bd.longValue();
			           		resId = Long.toString(longVal);
			           		mulData.setResId(resId);
			           		break;
				                        
			           	case Cell.CELL_TYPE_STRING:
				                        
			           		mulData.setResId(idCell.getStringCellValue());
			           		break;
			           }
			           
			           switch (progCodeCell.getCellType()) {
		            	  
			           	case Cell.CELL_TYPE_NUMERIC:
				                    	
				                    	
			           		progId = Double.toString(progCodeCell.getNumericCellValue());
			           		BigDecimal bd = new BigDecimal(progId);
			           		long longVal = bd.longValue();
			           		progId = Long.toString(longVal);
			           		mulData.setProgCode(progId);
			           		break;
				                        
			           	case Cell.CELL_TYPE_STRING:
			           		
			           		progId = progCodeCell.getStringCellValue();
			           		mulData.setProgCode(progId);
			           		break;
			           }
			            	  
			          // mulData.setProgCode(progCodeCell.getStringCellValue());	
			           mulData.setQuest1(response1Cell.getStringCellValue());
			           mulData.setQuest2(response2Cell.getStringCellValue());
			           mulData.setQuest3(response3Cell.getStringCellValue());
			           mulData.setQuest4(response4Cell.getStringCellValue());
			           mulData.setQuest5(response5Cell.getStringCellValue());

				                
				                
			            	  
			           multipleArray.add(mulData);
			        }
				
				
			}
	            

	        file.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		
		return multipleArray;
		
	}

}

