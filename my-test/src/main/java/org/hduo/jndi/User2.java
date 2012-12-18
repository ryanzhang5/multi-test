package org.hduo.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;
import javax.naming.spi.ObjectFactory;

public class User2 implements Referenceable {
	public String name;

	public User2(String name) {
		this.name = name;
	}

	public Reference getReference() throws NamingException {
		return new Reference(User2.class.getName(), new StringRefAddr("user",
				name), UserFactory.class.getName(), null);
	}

}

class UserFactory implements ObjectFactory {
	public UserFactory() {
	}

	public Object getObjectInstance(Object obj, Name name, Context nameCtx,
			Hashtable<?, ?> environment) throws Exception {
		if (obj instanceof Reference) {
			Reference ref = (Reference) obj;
			if (ref.getClassName().equals(User2.class.getName())) {
				RefAddr addr = ref.get("user");
				if (addr != null) {
					return new User2((String) addr.getContent());
				}
			}
		}

		return null;
	}

}
