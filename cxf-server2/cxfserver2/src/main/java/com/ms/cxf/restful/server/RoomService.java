package com.ms.cxf.restful.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/roomservice")
@Produces("application/xml")
public class RoomService {

	@GET
	@Path("/room/{id}")
	@Consumes("application/xml")
	public Room getRoom(@PathParam("id") String id) {
		System.out.println("get room by id= " + id);
		Room room = RoomDAO.getRoom(id);
		return room;
	}

	@GET
	@Path("/room")
	@Consumes("application/xml")
	public Room getAllRoom() {
		System.out.println("get all room");
		Room rooms = RoomDAO.getRooms();
		return rooms;
	}

	@POST
	@Path("/room")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.TEXT_XML,MediaType.APPLICATION_JSON })
	public void addRoom(Room room) {
		System.out.println("add room which id is:" + room.getId());
		System.out.println(room.getPersons());
		RoomDAO.addRoom(room);
	}

	@PUT
	@Path("/room/{id}")
	@Consumes("application/xml")
	public void updateRoom(@PathParam("id") String id, Room room) {
		System.out.println("update room which original id is:" + room.getId());
		RoomDAO.updateRoom(id, room);
	}

	@DELETE
	@Path("/room/{id}")
	@Consumes("application/xml")
	public void deleteRoom(@PathParam("id") String id) {
		System.out.println("remove room by id= " + id);
		RoomDAO.deleteRoom(id);
	}

	@POST
	@Path("/room/{id}")
	@Consumes("application/xml")
	public void addPerson(@PathParam("id") String id, Person person) {
		System.out.println("add person who's name is:" + person.getName());
		RoomDAO.addPerson(id, person);
	}

	@DELETE
	@Path("/room/{id}/{name}")
	@Consumes("application/xml")
	public void deletePerson(@PathParam("id") String id, @PathParam("name") String name) {
		System.out.println("remove person who's name is: " + name);
		RoomDAO.deletePerson(id, name);
	}
}
