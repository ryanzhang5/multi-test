package org.hdo.mysql;

import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public class DeployDataSource {

	public static void main(String args[]) throws SQLException, NamingException {
		// For this to work you need to create the
		// directory /JNDI/JDBC on your filesystem first
		Context ctx = null;
		try {
			Properties prop = new Properties();
			prop.setProperty(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.fscontext.RefFSContextFactory");
			prop.setProperty(Context.PROVIDER_URL, "file:/tmp/jndi/jdbc-mysql");
			ctx = new InitialContext(prop);
			
		} catch (NamingException ne) {
			System.err.println(ne.getMessage());
		}

		MysqlConnectionPoolDataSource cpds = new MysqlConnectionPoolDataSource();
		cpds.setServerName("localhost");
		cpds.setUser("root");
		cpds.setPassword("123456");
		cpds.setUrl("jdbc:mysql://localhost:3306/bookstore");
		

		// Register the ConnectionPoolDS with JNDI, using the logical name
		// “jdbc/pool/bookserver_pool”
		ctx.bind("bookserver_pool", cpds);
		//ctx = new InitialContext();
		
		

	}

}
