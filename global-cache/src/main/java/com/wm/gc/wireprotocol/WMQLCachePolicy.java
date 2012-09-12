package com.wm.gc.wireprotocol;

public class WMQLCachePolicy {
	private long policy;

	public WMQLCachePolicy() {
	}

	public WMQLCachePolicy(long policy) {
		this.policy = policy;
	}

	public long getPolicy() {
		return policy;
	}

	public long getTTLMillis() {
		return policy * 1000;

	}
}
