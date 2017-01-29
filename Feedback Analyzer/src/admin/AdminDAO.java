package admin;

import connectDB.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminDAO {
	
	static DAO dao = new DAO();
	static Connection con = null;
	
	public static Admin login(Admin bean){		//log in admin
		
		String id = bean.getAdId();
		String password = bean.getAdPass();
		
		String sql = "select* from admin where ad_id='"+id+"' and ad_pass='"+password+"'";
		
		con = dao.getConnection();
		
		
		ResultSet rs = null;	
		PreparedStatement statement = null;
			
		try {
			statement = con.prepareStatement(sql);
				
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
				statement.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dao.closeConnection(con);
		}

		return bean;
	}
	//this type has changed
	public void register(Admin bean){		//registering admin
		
		
		
		String sql = "INSERT INTO Admin (ad_id, ad_name, ad_pass) VALUES (?,?,?)";
		
		con = dao.getConnection();
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1,bean.getAdId());
			statement.setString(2,bean.getAdName());
			statement.setString(3,bean.getAdPass());
			statement.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				statement.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			dao.closeConnection(con);
		}
		
		
		
	}

}
