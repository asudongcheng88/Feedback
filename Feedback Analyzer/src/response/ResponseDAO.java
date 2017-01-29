package response;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import analyze.Analyze;
import analyze.AnalyzeDAO;
import program.Program;
import questions.Questionnaire;
import connectDB.DAO;

public class ResponseDAO {
	
	static DAO dao = new DAO();
	static Connection con = null;

	//insert into Response table
	
	public void insertResponse(Response bean) {
		
		String sql = "insert into response (res_id, quest_id, response, clean_data) values (?, ?, ?, ?)";
		
		con = dao.getConnection();
		PreparedStatement statement = null;
		
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1,bean.getResId());
			statement.setInt(2,bean.getQuestId());
			statement.setString(3,bean.getResponse());
			statement.setString(4,bean.getCleanData());
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
	
	
	//****This method not perfect
	
	
	public String cleanData(String string){
		
		string = string.toLowerCase();
		
		//remove number & punctuation
		
		
		Pattern pattern = Pattern.compile("[^a-zA-Z ]");
		Matcher regexMatcher = pattern.matcher(string.trim());
		string = regexMatcher.replaceAll("");
		
		
		try {
			
			Scanner scanner = new Scanner (new File("C:\\Users\\asuDongcheng\\Dropbox\\Program Development\\stopword\\stopwords.txt"));
			
			while(scanner.hasNextLine()){
				
				String stop = scanner.nextLine();
				
				String stopwords = ("\\s"+stop+"\\s");							
				pattern = Pattern.compile(stopwords);
				regexMatcher = pattern.matcher(string.trim());
				string = regexMatcher.replaceAll(" ");
				
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pattern = Pattern.compile("//s");
		regexMatcher = pattern.matcher(string.trim());
		string = regexMatcher.replaceAll("");
	
		return string;
		
	}
	
	
		
	//** error for the next unknown lexicon inserted
	//** violated primary key
	
	/*
	
	public void insertUnknownLex(String text){

		
		String unknownLexSql = "Select * from Unknown_Lexicon where un_lex_text = ?";
		String updateSql = "update Unknown_Lexicon set times_appeared = ? where un_lex_text = ?";
		String insertSql = "insert into unknown_lexicon (un_lex_text, times_appeared) values (?, ?)";
				
		
		Connection unLexCon = dao.getConnection();
		Connection updateCon = dao.getConnection();
		Connection insertCon = dao.getConnection();
		
		PreparedStatement unknownStmt = null;
		PreparedStatement updateStmt = null;
		PreparedStatement insertStmt = null;
		ResultSet rs = null;
		
		try{
			
			unknownStmt = unLexCon.prepareStatement(unknownLexSql);
			unknownStmt.setString(1, text);
			rs = unknownStmt.executeQuery();
			
			boolean exist = rs.next();
			
			if(exist){
				
				updateStmt = updateCon.prepareStatement(updateSql);
				updateStmt.setInt(1, (rs.getInt("times_appeared") + 1));
				updateStmt.setString(2, text);
				updateStmt.execute();
				
				
			}
			else
			{
				
				//System.out.println("Data will be insert");
				//System.out.println(text);
				
				insertStmt = insertCon.prepareStatement(insertSql);
				insertStmt.setString(1, text);
				insertStmt.setInt(2, 1);
				insertStmt.execute();
				
				
			}
			
		}catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				unknownStmt.close();
				updateStmt.close();
				insertStmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			dao.closeConnection(unLexCon);
			dao.closeConnection(updateCon);
			dao.closeConnection(insertCon);
		}
		
	}
	
	*/

	public Lexicons lexiconPoint(String text){
	
		Lexicons lex = new Lexicons();
		
		try {
			
			Scanner scanner = new Scanner (new File("C:\\Users\\asuDongcheng\\Dropbox\\Program Development\\Program\\FinalYearProject\\AFINN\\AFINN-111.txt"));
			
			while(scanner.hasNextLine()){
				
				String data = scanner.nextLine();
				
				StringTokenizer stTokenizer = new StringTokenizer(data, "\t");
				
				String lexicon = "";
				int point = 0;
				
				while(stTokenizer.hasMoreTokens()){
					
					lexicon = stTokenizer.nextToken();
					point = Integer.parseInt(stTokenizer.nextToken());
				}
				
				if(text.equalsIgnoreCase(lexicon)){
					
					lex.setLexPoint(point);
					lex.setLexText(lexicon);
					lex.setExist(true);
					
					break;
					
				}else{
					lex.setExist(false);
				}
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lex;
	}
	
	
	public void addTotalPoint(String progId, int questId, String text, int point){
		
		//System.out.println("Adding total point");
		//System.out.println("Point will be added = "+point);
		
		Connection updateAnalyzeCon = dao.getConnection();
		
		String sql = "update analyze set total_point = (total_point + ?) where prog_id = ? and quest_id=? and lex_text = ?";
		
		PreparedStatement stmt = null;
		
		try{
			stmt = updateAnalyzeCon.prepareStatement(sql);	
			stmt.setInt(1, point);
			stmt.setString(2, progId);
			stmt.setInt(3, questId);
			stmt.setString(4, text);
			stmt.execute();
		}catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dao.closeConnection(updateAnalyzeCon);
		}
		
	}
	
	//check whether lexicon text is exist or not in Analyze table
	
	public Analyze checkLexText(String resId, int questId, String cleanText){
		//System.out.println("Enter checkLExText method");
		Analyze analyze = new Analyze();
		Connection selectAnalyzeCon = dao.getConnection();
		
		String sql = "select*from analyze where prog_id=? and quest_id=? and lex_text=?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try{
			
			stmt = selectAnalyzeCon.prepareStatement(sql);
			stmt.setString(1, resId);
			stmt.setInt(2, questId);
			stmt.setString(3, cleanText);
			rs = stmt.executeQuery();
			
			boolean found = rs.next();
						
			if(found){
				
				//System.out.println("set lexicon text exist to true");
				
				analyze.setResId(rs.getString("prog_id"));
				analyze.setQuestId(Integer.parseInt(rs.getString("quest_id")));
				analyze.setLexText(rs.getString("lex_text"));
				analyze.setTotalPoint((rs.getInt("total_point")));
				analyze.setExist(true);
			}
			else{
				//System.out.println("set lexicon text exist to false");
				analyze.setExist(false);
				
			}
			
			
		}catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dao.closeConnection(selectAnalyzeCon);
		}
		
		//System.out.println("Out from checkLexText method");
		return analyze;
	}
	
	
	public void insertLexiconData(String progId, String respondentId, int questId){

		String cleanText = "";
		StringTokenizer st;
		AnalyzeDAO analyzeDAO = new AnalyzeDAO();
		//System.out.println("Enter insertLexiconData Method");
		
		//String selectResponSql = "select R.res_id, R.clean_data, R.quest_id, Q.prog_id From Response R join Questions Q On R.quest_id = Q.quest_id where Q.prog_id = ? and R.res_id=?";
		String selectResponSql = "select R.clean_data From Response R join Questions Q On R.quest_id = Q.quest_id where Q.prog_id = ? and R.res_id=? and R.quest_id=?";
		
		Connection selectResponseCon = dao.getConnection();
		
		PreparedStatement selectResponseStmt = null;
		ResultSet rsSelectResponse = null;

		try{
			selectResponseStmt = selectResponseCon.prepareStatement(selectResponSql);		//select from table Response
			selectResponseStmt.setString(1, progId);
			selectResponseStmt.setString(2, respondentId);
			selectResponseStmt.setInt(3, questId);
			
			rsSelectResponse = selectResponseStmt.executeQuery();						//execute select from Response statement
			
			
			/*
			 * Dont know why it iterate again even have NO data in database
			 * Last word FROM FIRST response will be process two times
			 * Tested using one word per questions
			 */
			
			System.out.println("This is from insertLexiconData Method");
			
			while(rsSelectResponse.next()){												//while got data in select statement
				
				st = new StringTokenizer(rsSelectResponse.getString("clean_data")," ");  //tokenize clean data
				
	
			    while (st.hasMoreTokens()) { 											//while got line in clean data
			    	
			    	
			    	cleanText = st.nextToken();
			    	
			    	System.out.println(cleanText);
			    	System.out.println();
			    	
			    	Analyze analyze = checkLexText(progId, questId, cleanText);
			    	
			    	Lexicons lex = lexiconPoint(cleanText);							//return lexicon bean
			    	
			    	
			    	if(lex.isExist()){
		    		
			    		
				    	if(analyze.isExist()){
				    		
				    	
				    		
				    		addTotalPoint(analyze.getResId(), analyze.getQuestId(), analyze.getLexText(), lex.getLexPoint());		
				    	}
				    	//if lexicons does not exist in table Analyze
				    	else{
				    		
				    		//insert text into analyze table

				    		analyzeDAO.insertResult(progId, cleanText, questId, lex.getLexPoint());
					    	
			  		
				    	}
			    	}
			    	else{

			    		//insertUnknownLex(cleanText);
			    	}
			    	
			    }//close tokenizer while			    		
			}//close result set while
			
		}catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				selectResponseStmt.close();
				rsSelectResponse.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dao.closeConnection(selectResponseCon);
			
		}
	}
	/*
	 * kiv
	 */
	/*
	public List<Analyze> selectResult(){

		List<Analyze> result = new ArrayList<Analyze>();
		
		String sql = "select * from Analyze A join Response R1 On A.response_id = R1.response_id Where R1.response_id = (select R2.response_id From Response R2 join Questions Q On R2.quest_id = Q.quest_id Where prog_id = '123')";
		
		con = dao.getConnection();
		PreparedStatement stmt = null;
		Analyze analyze;
		try {
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				String text = rs.getString("lex_text");
				int point = rs.getInt("total_point");
				analyze = new Analyze(text,point);
				result.add(analyze);
			}
			stmt.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			dao.closeConnection(con);
			
		}
		return result;
		
	}
	*/
}
