package commandline.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CMDDatabase {
	//
	public boolean status;
	private String logInfo, user, pwd; 
	private Connection conn = null;
	private Statement smt = null;
	private ResultSet rs;
	
	public CMDDatabase () {
//		this.logInfo = "jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/m_17_2278853c";
//		this.user = "2278853c";
//		this.pwd = "2278853c";
		this.logInfo = "jdbc:mysql://45.78.59.136:3306/2278853";
		this.user = "2278853c";
		this.pwd = "10214358og";
		
		this.connect();
	}

	private void connect() {
		//
		try {
			conn = DriverManager.getConnection(logInfo, user, pwd);
			smt = conn.createStatement();
			if (conn != null) {
				System.out.println("Connection successful");
				status = true;
			} else {
				System.err.println("Failed to make connection!");
				status = false;
			}
		} catch (SQLException e) {
			System.err.println("Connection Failed!");
			e.printStackTrace();
			status = false;
		}
		
	}
	
	public ResultSet pgQuery() {
		try {
			this.rs = smt.executeQuery("SELECT * FROM recording");
			return rs;
		} catch (SQLException e) {
			//
		}
		return rs;
	}
	
	public void pgClose() {
		try {
			conn.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection could not be closed ¨C SQLexception");
		}
		
	}
	
}
