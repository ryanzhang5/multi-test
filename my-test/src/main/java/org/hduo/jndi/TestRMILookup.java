package org.hduo.jndi;

import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TestRMILookup {
	public static void main(String[] args) throws SQLException, NamingException {
		Context ctx = null;
		try {
			Hashtable env = new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.rmi.registry.RegistryContextFactory");
			env.put(Context.PROVIDER_URL, "rmi://localhost:1099");
			ctx = new InitialContext(env);
		} catch (Exception ne) {
			System.err.println(ne.getMessage());
		}
		DataSource ds = (DataSource) ctx.lookup("joe3");
		System.out.println(ds);
	}

}
