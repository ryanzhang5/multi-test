package org.jboss.netty.example.time2;

import java.util.Date;

public class UnixTime {
	private final int value;

	public UnixTime(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return (new Date(value * 1000)).toString();
	}
}
