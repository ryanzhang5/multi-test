package org.hdo.mysql;

import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.dbcp.datasources.SharedPoolDataSource;

public class DeployDataSource2 {

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

		
		SharedPoolDataSource spds = new SharedPoolDataSource();
		
		spds.setDataSourceName("bookserver_pool");
		
		ctx.bind("myds", spds);

		
		
		
		

	}

}
