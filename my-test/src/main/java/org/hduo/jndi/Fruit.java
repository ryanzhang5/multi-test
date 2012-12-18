package org.hduo.jndi;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;

public class Fruit implements Referenceable {
	String fruit;

	public Fruit(String f) {
		fruit = f;
	}

	public Reference getReference() throws NamingException {

		return new Reference(Fruit.class.getName(), new StringRefAddr("fruit",
				fruit), FruitFactory.class.getName(), null); // factory location
	}

	public String toString() {
		return fruit;
	}
}