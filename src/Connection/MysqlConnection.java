package Connection;
import java.sql.*;
public class MysqlConnection  {
	public static Connection getConnection() 
	{
		Connection con=null;
		try 
		{                                           
			con=DriverManager.getConnection("jdbc:mysql://localhost/rec","root","");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void main(String args[]) {
		Connection con=getConnection();
		System.out.println(con);
		
		if(con==null) {
			System.out.println("Not Connected!");
		}
		else
		{
			System.out.println("**Connected!**");	
		}

CalculateProbability obj=new CalculateProbability();
		obj.initializeFactors();
		obj.calculateProbability();
	}
	public static void close(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
}