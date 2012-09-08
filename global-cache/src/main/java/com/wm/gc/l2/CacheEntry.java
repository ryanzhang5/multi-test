package com.wm.gc.l2;

import java.io.Serializable;

public class CacheEntry implements Serializable {
	public String id;
	public String name;

	CacheEntry(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "id = " + this.id + " name = " + this.name;
	}
}
