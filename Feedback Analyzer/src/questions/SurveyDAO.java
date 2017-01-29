package questions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import program.Program;
import multipleData.ProgramAndQuestions;
import connectDB.DAO;

public class SurveyDAO {
	
	static DAO dao = new DAO();
	static Connection con = null;
	
	/*
	 * create questions
	 * use in QuestionServlet
	 */
	
	public void createQuest(Questionnaire bean){
		
		String sql = "insert into Questions (quest_text,prog_id,sec_name) values (?,?,?)";
		
		con = dao.getConnection();
		PreparedStatement statement = null;
		
		try {
			
			statement = con.prepareStatement(sql);
			statement.setString(1, bean.getQuestText());
			statement.setString(2, bean.getProgId());
			statement.setString(3, bean.getSecName());
			statement.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally{
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dao.closeConnection(con);
		}
		
	}
	
	/*
	 * create new program for admin
	 * use in ProgramServlet
	*/
	public void createProg(Program bean){
		
		String sql = "insert into Program (prog_id, prog_name, prog_date, prog_description,ad_id) values (?,?,TO_DATE(?, 'dd/MM/yy'),?,?)";
		con = dao.getConnection();
		PreparedStatement statement = null;
		
		try {
			
			statement = con.prepareStatement(sql);
			
			statement.setString(1, bean.getProgId());
			statement.setString(2, bean.getProgName());
			statement.setString(3, bean.getProgDate());
			statement.setString(4, (bean.getProgDescription()));
			statement.setString(5, bean.getAdId());
			
			statement.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dao.closeConnection(con);
		}
	
	}
	
	/*
	 * use in RespondentServlet for SurveyForm.jsp
	 * use in QuestionServlet for CreateSurvey.jsp
	 * 
	*/
	public List<Questionnaire> selectAllQuest(String id){
		
		List<Questionnaire> questList = new ArrayList<Questionnaire>();
	
		String sql = "select quest_text, quest_id, sec_name from Questions where prog_id=?";
		
		con = dao.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, id);
			rs = statement.executeQuery();
			
			while(rs.next()){
		
				String text = rs.getString("quest_text");
				int questId = Integer.parseInt(rs.getString("quest_id"));
				String section = rs.getString("sec_name");
	
				Questionnaire quest = new Questionnaire(section,questId,text);
				questList.add(quest);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				statement.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dao.closeConnection(con);
		}
		
		return questList;
		
	}
	
	//select all question for program menu and analyze menu
	
	public List<ProgramAndQuestions> selectAllQuestForProgram(Program bean){
			
			List<ProgramAndQuestions> questList = new ArrayList<ProgramAndQuestions>();
			
			String progName = bean.getProgName();
			
		
			String sql = "select * from Program P join Questions Q on P.prog_id = Q.prog_id where prog_name =?";
	
			con = dao.getConnection();
			PreparedStatement statement = null;
			ResultSet rs = null;
			
			try {
				statement = con.prepareStatement(sql);
				statement.setString(1,progName);
				rs = statement.executeQuery();
				
				while(rs.next()){
					String progId = rs.getString("prog_id");
					String name = rs.getString("prog_name");
					String progDate = rs.getString("prog_date");
					String adminId = rs.getString("ad_id");
					int questId = Integer.parseInt(rs.getString("quest_id"));
					String questText = rs.getString("quest_text");
					
					
					
			
					ProgramAndQuestions PnQ = new ProgramAndQuestions(progId,name,progDate,adminId,questId,questText);
					
					questList.add(PnQ);
			
					
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				try {
					statement.close();
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dao.closeConnection(con);
			}
			
			return questList;
		}
	
	//return question list in analyze result
	//use in ANALYZE SERVLET
	
	public List<Questionnaire> questionListBasedOnSection(String section, String progName){
		
		//System.out.println("Enter questionListBasedOnSection");
		
		List<Questionnaire> questList = new ArrayList<Questionnaire>();
		
		String sql = "select quest_text from Questions Q join Program P on Q.prog_id = P.prog_id where Q.sec_name = ? and P.prog_name = ?";
		
		con = dao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, section);
			stmt.setString(2, progName);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				
				String questText = rs.getString("quest_text");
				
				Questionnaire quest = new Questionnaire (questText);
				
				questList.add(quest);
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
		
		return questList;
		
		
	}

}
