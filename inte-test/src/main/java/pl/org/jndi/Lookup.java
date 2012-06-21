package pl.org.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Lookup {
	public static void main(String[] args) {
	
		String name = "/tmp";

		// Identify service provider to use
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.rmi.registry.RegistryContextFactory");
		env.put(Context.PROVIDER_URL, "rmi://localhost:1099");

		try {

			// Create the initial context
			Context ctx = new InitialContext(env);

			// Look up an object
			Object obj = ctx.lookup(name);

			// Print it out
			System.out.println(name + " is bound to: " + obj);

			// Close the context when we're done
			ctx.close();
		} catch (NamingException e) {
			System.err.println("Problem looking up " + name + ": " + e);
		}
	}
}
