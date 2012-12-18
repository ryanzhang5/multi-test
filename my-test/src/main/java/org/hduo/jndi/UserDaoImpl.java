package org.hduo.jndi;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;

public class UserDaoImpl {
	private LdapTemplate ldapTemplate;

	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}

	public List getAllContactNames() {
		return ldapTemplate.search("", "objectclass=person",
				new AttributesMapper() {

					public Object mapFromAttributes(Attributes attributes)
							throws NamingException {
						return attributes.get("labeledURI").get();
					}
				});
	
	}
	
	

}
