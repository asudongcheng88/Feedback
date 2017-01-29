package section;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.DAO;

public class SectionDAO {
	
	static DAO dao = new DAO();
	static Connection con = null;
	
	public void insertSection(Section bean){
		
		String sql = "insert into Section (sec_name,prog_id) values (?,?)";
		
		con = dao.getConnection();
		
		PreparedStatement statement = null;
		
		System.out.println(bean.getSecName());
		
		try {
			
			statement = con.prepareStatement(sql);
			statement.setString(1, bean.getSecName());
			statement.setString(2, bean.getProgId());
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
	
	public boolean checkSection(Section bean){
		
		con = dao.getConnection();
		//Section section = new Section();
		boolean found = false;
		
		String sql = "select sec_name from Section where sec_name=? and prog_id=?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, "sec_name");
			stmt.setString(2, "prog_id");
			rs = stmt.executeQuery();
			found = rs.next();
			
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
		
		return found;
		
	}
	
	public List<Section> sectionList(String progName){
		
		//System.out.println("Enter section list");
		
		List<Section> secArray = new ArrayList<Section>();
		
		String sql = "select sec_name from Section S join Program P on S.prog_id = P.prog_id where prog_name=?";
		con = dao.getConnection();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, progName);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				String section = rs.getString("sec_name");
				//System.out.println("Section will be entered "+section);
				Section sec = new Section(section);
				secArray.add(sec);
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
		
		return secArray;
		
	}

}
