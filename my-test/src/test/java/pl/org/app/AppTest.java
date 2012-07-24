package pl.org.app;

import java.sql.Connection;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import javax.sql.PooledConnection;
import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.apache.commons.dbcp.BasicDataSource;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

public class AppTest {

	DataSource ds;

	@BeforeClass
	public void testSetUp() {
		System.out
				.println("testSetUp------------------------------------------------");
		/*
		 * DriverAdapterCPDS cpds = new DriverAdapterCPDS(); try {
		 * cpds.setDriver("com.mysql.jdbc.Driver"); } catch
		 * (ClassNotFoundException e) { e.printStackTrace(); }
		 * cpds.setUrl("jdbc:mysql://localhost:3306/bookstore");
		 * cpds.setUser("root"); cpds.setPassword("123456");
		 * 
		 * SharedPoolDataSource tds = new SharedPoolDataSource();
		 * tds.setConnectionPoolDataSource(cpds); tds.setMaxActive(10);
		 * tds.setMaxWait(50);
		 * 
		 * ds = tds;
		 */
	}

	@Test
	public void testSomeNumber4() {
		System.out
				.println("testSomeNumber4------------------------------------------------");
		try {
			Connection conn = ds.getConnection();
			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBasicDataSource(){
		BasicDataSource bda = new BasicDataSource();
		System.out.println("testBasicDataSource------------------------------------------------");
		bda.setUsername("root");
		bda.setPassword("123456");
		bda.setUrl("jdbc:mysql://localhost:3306/bookstore");
		bda.setInitialSize(13);
		try{
			Connection conn = bda.getConnection();
			System.out.println("===++++++++++++++++++++++++="+conn.getTransactionIsolation());
			System.out.println(conn);
			bda.close();
		}catch(Exception e){
			
		}
	}

	@Test
	public void testXA() {

		try {
			System.out
					.println("txstXA------------------------------------------------");
			MysqlXADataSource xda = new MysqlXADataSource();
			xda.setUser("root");
			xda.setPassword("123456");
			xda.setUrl("jdbc:mysql://localhost:3306/bookstore");
			XAConnection xaconn;
			DataSource ds = null;
			System.out.println(xda.getConnection());
			System.out.println(xda.getXAConnection());
			System.out.println(xda.getXAConnection().getConnection());
			PooledConnection pc = null;
			ConnectionPoolDataSource cds = null;
			XAResource xar = null;
			//UserTransaction ut = null;
			xar.commit(null, false);
			Xid xid = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}