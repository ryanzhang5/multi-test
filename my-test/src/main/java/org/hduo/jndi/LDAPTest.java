package org.hduo.jndi;

import java.util.Hashtable;

import javax.jws.soap.SOAPBinding.Use;
import javax.naming.Context;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;

import bsh.NameSpace;

public class LDAPTest {

	public static void get(Hashtable<String, String> env) {
		DirContext ctx = null;
		NamingEnumeration results = null;
		try {
			ctx = new InitialDirContext(env);
			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			results = ctx.search("", "(objectclass=person)", controls);
			while (results.hasMore()) {
				SearchResult searchResult = (SearchResult) results.next();
				Attributes attributes = searchResult.getAttributes();
				NamingEnumeration<? extends Attribute> attrs = attributes
						.getAll();
				while (attrs.hasMoreElements()) {
					Attribute attribute = (Attribute) attrs.nextElement();
					System.out.println("-------------------------"
							+ attribute.getID() + "::::" + attribute.get());
				}
				Attribute attr = attributes.get("cn");
				String cn = (String) attr.get();
				System.out.println(" Person Attr = " + cn + " "
						+ attributes.get("sn").get() + " "
						+ attributes.get("uid").get());
			}
		} catch (NamingException e) {
			throw new RuntimeException(e);
		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (Exception e) {
				}
			}
			if (ctx != null) {
				try {
					ctx.close();
				} catch (Exception e) {
				}
			}
		}
	}

	public static void get2(Hashtable<String, String> env) {
		DirContext ctx = null;
		try {
			ctx = new InitialDirContext(env);
			// LdapContext lctx =(LdapContext)ctx.lookup("uid=Unmi");
			System.out.println("begin");
			NamingEnumeration<NameClassPair> list = ctx.list("o=anotherbug");
			while (list.hasMore()) {
				Object object = (Object) list.next();
				System.out.println(object);

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {

			if (ctx != null) {
				try {
					ctx.close();
				} catch (Exception e) {
				}
			}
		}
	}

	public static void bind(Hashtable<String, String> env) {
		User2 user = new User2("ryan2");
		DirContext ctx = null;
		try {
			ctx = new InitialDirContext(env);
			ctx.bind("cn=another user2", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void lookup(Hashtable<String, String> env) {
		DirContext ctx = null;
		try {
			ctx = new InitialDirContext(env);
			Object obj =ctx.lookup("cn=another user");
			System.out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void rename(Hashtable<String, String> env) {
		DirContext ctx = null;
		try {
			ctx = new InitialDirContext(env);
			ctx.rename("cn=another user2", "cn=another user2ssssssss");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		LDAPTest LDAPTest1 = new LDAPTest();
//		String root = "o=anotherbug,c=com"; // root
		String root = "o=anotherbug,c=com"; // root
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost/" + root);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "cn=manager,o=anotherbug,c=com");
		env.put(Context.SECURITY_CREDENTIALS, "secret");
		lookup(env);
		//get(env);
	}
}