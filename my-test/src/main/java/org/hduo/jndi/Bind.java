package org.hduo.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Bind {
	public static void main(String[] args) {

		// Set up the environment for creating the initial context
		Hashtable<String, Object> env = new Hashtable<String, Object>(11);
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:389/o=anotherbug,c=com");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "cn=manager,o=anotherbug,c=com");
		env.put(Context.SECURITY_CREDENTIALS, "secret");
		try {
			// Create the initial context
			Context ctx = new InitialContext(env);

			// Create the object to be bound
			Fruit fruit = new Fruit("orange");

			// Perform the bind
			ctx.bind("cn=Favorite Fruit555", fruit);

			// Check that it is bound
			Object obj = ctx.lookup("cn=Favorite Fruit222");
			System.out.println(obj);

			// Close the context when we're done
			ctx.close();
		} catch (NamingException e) {
			System.out.println("Operation failed: " + e);
		}
	}
}
