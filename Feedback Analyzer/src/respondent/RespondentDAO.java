package respondent;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import oracle.jdbc.OraclePreparedStatement;
import response.Response;
import multipleData.MultipleData;
import connectDB.DAO;

public class RespondentDAO {
	
	static DAO dao = new DAO();
	static Connection con = null;
	
	public static Respondent login(Respondent bean){
		
		String id = bean.getResId();
		String pass = bean.getResPass();
		
		//** dont know it cannot replace with place holder (?)
		
		String sql = "select * from Respondent where res_id = '"+id+"' and prog_id = '"+pass+"'";
		
		con = dao.getConnection();
		
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			statement = con.prepareStatement(sql);
			//statement.setString(1,  id);
			//statement.setString(2, pass);
				
			rs = statement.executeQuery(sql);
				
			boolean valid = rs.next();
			
			
			if(!valid){
					
				bean.setValid(false);
			}
				
			else if(valid){
					
				bean.setValid(true);
			}
		} catch (SQLException e) {
				
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dao.closeConnection(con);
		}

		return bean;
	}
	
	
	public void registerRespondent(InputStream content, String progId, String adminId){
		
		String respondentName = "";
		String respondentId="";

		con = dao.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "insert into Respondent (res_name, res_id, prog_id, ad_id) VALUES (?,?,?,?)";
		
		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sql);
			@SuppressWarnings("resource")
			HSSFWorkbook wb = null;
			
			try {
				wb = new HSSFWorkbook(content);
			} catch (IOException e) {
				
				e.printStackTrace();
			}

    		//Get first sheet from the workbook
    		HSSFSheet sheet = wb.getSheetAt(0);

    		FormulaEvaluator fe = wb.getCreationHelper().createFormulaEvaluator();
    		
    		HSSFDataFormatter hdf = new HSSFDataFormatter();
    		
    		Cell nameCell = null;
    		Cell idCell = null;
    		
    		for(Row row:sheet){
    			
    			switch(fe.evaluate(row.getCell(0)).getCellType()){
					case Cell.CELL_TYPE_NUMERIC:
						nameCell = row.getCell(0);
						break;
					case Cell.CELL_TYPE_STRING:
						nameCell = row.getCell(0);
						break;
						
    			}
						
    			switch(fe.evaluate(row.getCell(1)).getCellType()){
					case Cell.CELL_TYPE_NUMERIC:
						idCell = row.getCell(1);
						break;
						
					case Cell.CELL_TYPE_STRING:
						idCell = row.getCell(1);
						break;
						
    			}
    			
    			
    			respondentName = hdf.formatCellValue(nameCell);
        		respondentId = hdf.formatCellValue(idCell);
        		stmt.setString(1,respondentName);
    			stmt.setString(2,respondentId);
    			stmt.setString(3, progId);
    			stmt.setString(4, adminId);
    			stmt.executeUpdate();

    		}

	
			((OraclePreparedStatement)stmt).sendBatch();
			con.commit();

			
		} catch (SQLException e) {

			e.printStackTrace();
		}finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dao.closeConnection(con);
			
		}
			
	}
	
}
