package program;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.DAO;

public class ProgramDAO {
	
	static DAO dao = new DAO();
	static Connection con = null;
	
	/*
	 * list all the program name for Menu
	 * list all the program id for Register Respondent
	 * admin id as input parameter
	 */

	public List<Program> selectAllProg(String id){									
																					
		List<Program> progList = new ArrayList<Program>();
		
		String sql = "select prog_name, prog_id from Program where ad_id='"+id+"'";
		
		con = dao.getConnection();
		
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			statement = con.prepareStatement(sql);
			rs = statement.executeQuery();
			Program prog;
			while(rs.next()){

				String progName = rs.getString("prog_name");
				String progId = rs.getString("prog_id");
	
				prog = new Program(progId, progName);
				progList.add(prog);		
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
		
		return progList;
	}	
	
	
	/*
	 * list program name for register respondent
	 * prog id as input parameter
	 */
	
	public String progNameList(String progId){
		
		String progName = "";
		
		String sql = "select prog_name from Program where prog_id = ?";
		
		con = dao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, progId);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				progName = rs.getString("prog_name");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dao.closeConnection(con);
		}

		return progName;
	}
	
	public boolean isCreated(String adminId){
		
		boolean exist = false;
		
		
		con = dao.getConnection();
		
		String sql = "select * from Program where ad_id = ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, adminId);
			
			rs = stmt.executeQuery();
			
			if(rs.next()){
				
				exist = true;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		return exist;
		
	}
	

}
