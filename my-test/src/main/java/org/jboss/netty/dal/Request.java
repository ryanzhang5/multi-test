package org.jboss.netty.dal;

import java.io.Serializable;

public class Request implements Serializable {
	private static int index = 0;
	public int id = index++;

	public Request() {

	}

	@Override
	public String toString() {
		return "this is request " + id;
	}
}
