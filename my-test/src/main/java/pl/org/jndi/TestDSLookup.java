package pl.org.jndi;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;

public class TestDSLookup {
	public static void main(String[] args) throws SQLException, NamingException {
		Context ctx = null;
		try {
			Properties prop = new Properties();
			prop.setProperty(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.fscontext.RefFSContextFactory");
			prop.setProperty(Context.PROVIDER_URL,
					"file:/tmp/jndi/jdbc");
			ctx = new InitialContext(prop);
		} catch (NamingException ne) {
			System.err.println(ne.getMessage());
		}
		DataSource ds = (DataSource) ctx.lookup("joe");
		System.out.println(ds);
	}
}