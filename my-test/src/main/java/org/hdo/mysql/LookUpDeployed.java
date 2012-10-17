package org.hdo.mysql;

import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LookUpDeployed {
	public static void main(String[] args) throws SQLException, NamingException {
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
		//DataSource ds = (DataSource) ctx.lookup("myds");
		DataSource ds = (DataSource) ctx.lookup("bookserver_pool");
		
		System.out.println(ds);
	}
}
