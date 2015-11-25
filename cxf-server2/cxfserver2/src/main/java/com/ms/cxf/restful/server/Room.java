package com.ms.cxf.restful.server;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Room")
public class Room {
	public Room() {
		persons = new HashMap<String, Person>();
	}

	String id;
	Map<String, Person> persons;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Person> getPersons() {
		return persons;
	}

	public void setPersons(Map<String, Person> persons) {
		this.persons = persons;
	}
}
