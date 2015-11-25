package com.ms.cxf.restful.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.jaxrs.client.WebClient;

import com.ms.cxf.restful.server.Person;
import com.ms.cxf.restful.server.Room;
public class Client {
	static WebClient client;

	public static void main(String[] args) {
		client = WebClient.create("http://localhost:9999/");
		//put();
		post();
	}

	static void get() {
		Room room = client.path("roomservice/room/001").accept("application/xml").get(Room.class);
		System.out.println("get the room which id is:" + room.getId());
	}

	static void post() {
		Room room = new Room();
		room.setId("007");
		Map<String, Person> persons = new HashMap<String, Person>();
		Person person = new Person();
		person.setName("personName");
		person.setSex("personSex");
		persons.put("myPerson", person);
		room.setPersons(persons);
		client.path("roomservice/room").accept("application/xml").post(room, Room.class);
	}

	static void delete() {
		client.path("roomservice/room/002").accept("application/xml").delete();
	}

	static void put() {
		Room room = new Room();
		room.setId("006");
		client.path("roomservice/room/003").accept("application/xml").put(room);
	}
}
