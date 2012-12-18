package org.hduo.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

//import oracle.jdbc.pool.OracleDataSource;

public class TestRMIDSBind {
	public static void main(String[] args) {

		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.rmi.registry.RegistryContextFactory");
		env.put(Context.PROVIDER_URL, "rmi://localhost:1099");

		try {

			// Create the initial context
			Context ctx = new InitialContext(env);
			System.out.println("here");
			// OracleDataSource ds = new OracleDataSource();
			//
			ctx.bind("joe3", new Fruit("myfruit"));
			System.out.println("sssssssssssssssss");
			System.out.println(ctx.lookup("joe3"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
