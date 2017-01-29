package connectDB;

import java.sql.*;



public class DAO {
	
	public Connection getConnection(){
		
		String connectionURL = "jdbc:oracle:thin:@localhost:1521:xe";
		Connection con = null;
		
		try{
			
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			con = DriverManager.getConnection(connectionURL,"fyp_database","fyp_database");
				
		}catch (Exception e){
			e.printStackTrace();
		}	
	
		return con;
	}
	
	public void closeConnection(Connection con){
		try {
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
