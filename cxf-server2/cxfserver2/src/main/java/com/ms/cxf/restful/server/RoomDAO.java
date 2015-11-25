package com.ms.cxf.restful.server;

import java.util.HashMap;
import java.util.Map;

public class RoomDAO {
	private static Map<String, Room> rooms;

	static {
		rooms = new HashMap<String, Room>();

		Person p1 = new Person();
		p1.setName("Boris");
		p1.setSex("male");

		Room r = new Room();
		r.setId("001");
		r.getPersons().put(p1.getName(), p1);
		rooms.put("001", r);
	}

	public static void addRoom(Room room) {
		rooms.put(room.getId(), room);
	}

	public static void deleteRoom(String id) {
		if (rooms.containsKey(id)) {
			rooms.remove(id);
		}

	}

	public static void updateRoom(String id, Room room) {
		rooms.remove(id);
		rooms.put(room.getId(), room);
	}

	public static Room getRoom(String id) {
		if (rooms.containsKey(id)) {
			return rooms.get(id);
		} else {
			return null;
		}
	}

	/* operations to persons */
	public static void addPerson(String id_room, Person person) {
		if (rooms.containsKey(id_room)) {
			Room room = rooms.get(id_room);
			room.getPersons().put(person.getName(), person);
		}
	}

	public static Room getRooms() {
		return new Room();
	}

	public static void deletePerson(String id_room, String name) {
		if (rooms.containsKey(id_room)) {
			Room room = rooms.get(id_room);
			room.getPersons().remove(name);
		}
	}

	public static Map<String, Room> getMapOfRooms() {
		return rooms;
	}
}
