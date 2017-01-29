package result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import questions.Questionnaire;
import connectDB.DAO;

public class ResultDAO {
	
	static DAO dao = new DAO();
	static Connection con = null;
	
	public int getTotalRespondent(String progName, String adminId){
		
		con = dao.getConnection();
		
		int total = 0;
		
		System.out.println(progName);
		System.out.println(adminId);
		
		String sql = "select count(R.res_id) as total from Respondent R join Program P on R.prog_id = P.prog_id where P.prog_name = ? and P.ad_id = ? ";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, progName);
			stmt.setString(2, adminId);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				
				total = rs.getInt("total");
				
			}
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			dao.closeConnection(con);
		}	
		
		return total;
	}
	
	public List<Questionnaire> getQuestionId(String progName){
		
		con = dao.getConnection();
		
		List<Questionnaire> questId = new ArrayList<Questionnaire>();
		
		String sql = "select Q.quest_id from Questions Q join Program P on Q.prog_id = P.prog_id where P.prog_name = ? order by Q.quest_id asc";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, progName);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				
				Questionnaire q = new Questionnaire();
				q.setQuest_id(rs.getInt("quest_id"));
				
				questId.add(q);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			dao.closeConnection(con);
		}	
		
		return questId;
	}
	
	public List<Result> getAveragePoint(int totalRespondent, String progName){
		
		Connection con1 = dao.getConnection();
		
		int count = 0;
		double average = 0.0;
		double total = 0.0;
		
		List<Result> resultArray = new ArrayList<Result>();
		
		String sql = "select count(A.total_point) as total from analyze A join program P on A.prog_id = P.prog_id join Questions Q on Q.quest_id = A.quest_id where P.prog_name = ? and A.quest_id = ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Questionnaire> questId = new ArrayList<Questionnaire>(); 
		questId = getQuestionId(progName);
		
		
			
		try {
				
			for(Questionnaire qId:questId){
					
				count++;
				
				stmt = con1.prepareStatement(sql);
				stmt.setString(1, progName);
				stmt.setInt(2, qId.getQuestId());
				
				rs = stmt.executeQuery();
				
				while(rs.next()){
					
					total = rs.getDouble("total");
					
				}
				
				average =  total / totalRespondent;
				
				DecimalFormat df = new DecimalFormat("####0.0000");
				
				String straverage = df.format(average);
				
				average = Double.parseDouble(straverage);
				
				System.out.println("double value "+average);
				
				Result result = new Result();
				
				String questText = getQuestion(qId.getQuestId(), progName);
				
				System.out.println(questText);
				
				result.setQuestion(questText);
				result.setAverage(average);
				result.setQuestionNo("Question "+count);
				result.setProgName(progName);
				
				resultArray.add(result);
				
			} 
				
			
		}catch (SQLException e) {
				
			e.printStackTrace();
			
		}finally{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
					
			e.printStackTrace();
			}
			dao.closeConnection(con);
		}
			
		
		
		return resultArray;
		
	}
	
	public String getQuestion(int quest_id, String progId){
		
		con = dao.getConnection();
		
		String text = "";
		

		
		String sql = "select quest_text from questions where quest_id = ? and prog_id = ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, quest_id);
			stmt.setString(2, progId);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				
				text = rs.getString("quest_text");
				
			}
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			dao.closeConnection(con);
		}	
		
		return text;
	}
	
	

}
