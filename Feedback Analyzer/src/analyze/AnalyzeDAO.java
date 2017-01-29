package analyze;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




import section.Section;
import connectDB.DAO;

public class AnalyzeDAO {
	
	static DAO dao = new DAO();
	static Connection con = null;
	
	/*
	 * This method use in RESPONSED DAO inside the method insertLexiconText
	 */
	
	
	public void insertResult(String progId, String text, int questId, int point ){
		
		/*
		 * System can be improved by adding question id into analyze
		 * question id is taken from selectResponseStmt
		 * so it can be evaluate by questions
		 */
		
		con = dao.getConnection();
		
		PreparedStatement insertAnalyzeStmt = null;

    	String sql = "insert into analyze (prog_id,lex_text,quest_id,total_point) values (?, ?, ?, ?)";  //insert to table Analyze
		
		try {
			insertAnalyzeStmt = con.prepareStatement(sql);
			
			insertAnalyzeStmt.setString(1, progId);
		    insertAnalyzeStmt.setString(2, text);
		    insertAnalyzeStmt.setInt(3, questId);
		    insertAnalyzeStmt.setInt(4, point);
		    insertAnalyzeStmt.execute();
		    
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				insertAnalyzeStmt.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			dao.closeConnection(con);
		}	
		
	}
	
	public int overallAnalysis(String section, String progName){
		int totalPoint = 0;
		
		String sql = "select SUM(total_point) AS point from Analyze A join Questions Q on A.quest_id = Q.quest_id join Program P on A.prog_id = P.prog_id where P.prog_name = ? and Q.sec_name = ?";
		
		con = dao.getConnection();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, progName);
			stmt.setString(2, section);
			rs = stmt.executeQuery();
			
			boolean x = rs.isBeforeFirst();
			System.out.println(x);
			
			while(rs.next()){
				
				if(rs.getString("point") != null){
					
					totalPoint = Integer.parseInt(rs.getString("point"));
				
				}
				else{
					totalPoint = 999999;
				}
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}catch (NullPointerException  nullException) {
			
			nullException.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			dao.closeConnection(con);
		}
		
		return totalPoint;
	}
	
	public List<Analyze> questPositiveAnalysisResult(String section, String progName, String question, String sign){
		
		String sql = "";
		List<Analyze> analysisArray = new ArrayList<Analyze>();
			
		sql = "select A.lex_text,A.total_point From Analyze A join Questions Q on A.quest_id=Q.quest_id join Program P on A.prog_id=P.prog_id where prog_name=? and Q.sec_name=? and Q.quest_text=? and rownum<= 5 and A.total_point > 0 order by A.total_point desc";

		con = dao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, progName);
			stmt.setString(2, section);
			stmt.setString(3, question);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				
				String lexText = rs.getString("lex_text");
				int point = rs.getInt("total_point");
				
				if(point > 0){
					
					Analyze analyze = new Analyze(lexText, point);
					analysisArray.add(analyze);
				}
				
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
		
		return analysisArray;
		
	}
	
public List<Analyze> questNegativeAnalysisResult(String section, String progName, String question, String sign){
		
		String sql = "";
		List<Analyze> analysisArray = new ArrayList<Analyze>();
			
		sql = "select A.lex_text,A.total_point From Analyze A join Questions Q on A.quest_id=Q.quest_id join Program P on A.prog_id=P.prog_id where prog_name=? and Q.sec_name=? and Q.quest_text=? and rownum<= 5 and A.total_point<0 order by A.total_point asc";
		
		con = dao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, progName);
			stmt.setString(2, section);
			stmt.setString(3, question);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				
				String lexText = rs.getString("lex_text");
				int point = rs.getInt("total_point");
				
				if(point < 0){
					
					Analyze analyze = new Analyze(lexText, point);
					analysisArray.add(analyze);
				}
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dao.closeConnection(con);
		}
		
		return analysisArray;
		
	}

	public boolean isValidResponse(String progName){
		
		String sql = "select R.response from Response R join Questions Q on R.quest_id=Q.quest_id join Program P on Q.prog_id = P.prog_id where P.prog_name=?";
		
		con = dao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean exist = false;
		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, progName);
			rs = stmt.executeQuery();
			
			exist = rs.next();
			
			if(exist == true){
				exist = true;
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
		
		return exist;
	}

}
