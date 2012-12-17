package org.jboss.netty.dal;

import java.io.Serializable;

public class Response implements Serializable {
	private static int index = 0;
	public int id = index++;

	public Response() {

	}

	@Override
	public String toString() {
		return "this is Response " + id;
	}

}
