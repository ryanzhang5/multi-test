package com.wm.gc.l1;

import java.io.Serializable;

public class CacheKey implements Serializable {
	private String key;

	private CacheKey(String key) {
		this.key = key;
	}

	public static CacheKey getInstance(String key) {
		return new CacheKey(key);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
