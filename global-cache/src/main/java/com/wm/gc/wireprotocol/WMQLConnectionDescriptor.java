package com.wm.gc.wireprotocol;

import com.sun.org.apache.xml.internal.utils.NameSpace;

public class WMQLConnectionDescriptor {
	public String server;
	public int port;

	public WMQLConnectionDescriptor(String server, int port) {
		super();
		this.server = server;
		this.port = port;
	}

	public static WMQLConnectionDescriptor getDefaultInstance(NameSpace st) {
		return new WMQLConnectionDescriptor("localhost", 9905);
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
