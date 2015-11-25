package com.ms.cxf.restful.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/jsonservice")
@Produces("application/json")
public class JsonService {

	@GET
	@Path("/room/{id}")
	@Consumes("application/json")
	public Room jsonTest(@PathParam("id") String id) {
		System.out.println("get room by id= " + id);
		Room room = RoomDAO.getRoom(id);
		return room;
	}

}
