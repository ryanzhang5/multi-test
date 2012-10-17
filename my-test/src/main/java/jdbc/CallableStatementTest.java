package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CallableStatementTest {

	public static Connection getConnection()throws SQLException{
		Connection connection = null;
		String url = "jdbc:mysql://localhost:3306/hduo";
		String user = "root";
		String password = "123456";
		connection = DriverManager.getConnection(url,user,password);
		return connection;
		
	}
	
	
	public static void testCallable()throws SQLException{
		String sql = "call GetUserByName3(?,?,?)";
		Connection connection = getConnection();
		CallableStatement cstmt = connection.prepareCall(sql);
		
		cstmt.setString(1, "paul");
		cstmt.registerOutParameter(2, java.sql.Types.VARBINARY);
		cstmt.registerOutParameter(3, java.sql.Types.INTEGER);
		
		ResultSet rs = cstmt.executeQuery();
		
		System.out.println(cstmt.getString(2) + "    " + cstmt.getInt(3));
		while (rs.next()) {
		System.out.println(rs.getInt(1) + "   " + rs.getString(2) + "   " + rs.getString(3));	
		}
		
		rs.close();
		cstmt.close();
		connection.close();
		
	}
	
	
	
	public static void main(String[] args) throws SQLException{
		testCallable();
	}

}
