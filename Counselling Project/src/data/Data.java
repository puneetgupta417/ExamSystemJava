package data;

import java.sql.*;

public class Data {
	
	 public Connection con;
	 public Statement smt;
	
	public Data()
	{
		
		try
		{
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		con=DriverManager.getConnection("jdbc:ucanaccess://database.accdb");
		smt=con.createStatement();
		}
		catch(Exception e)
		{
		System.out.println("hi "+e);
		}
	}
	
	public ResultSet executeQuery(String sql) throws SQLException
	{
		ResultSet rs = smt.executeQuery(sql);
		return rs;
	}
	
	public int executeUpdate(String sql) throws SQLException
	{
		smt.executeUpdate(sql);
		return 0;
		
	}
	
}
