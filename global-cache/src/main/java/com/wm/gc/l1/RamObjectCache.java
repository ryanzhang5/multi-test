package com.wm.gc.l1;

public class RamObjectCache extends ObjectCache {
	public RamObjectCache(Namespace namespace, String cacheName,
			long maxSizeInBytes) {
		this(namespace, cacheName, maxSizeInBytes, new Janitor());
	}

	public RamObjectCache(Namespace namespace, String cacheName,
			long maxSizeInBytes, Janitor janitor) {
		super(namespace, cacheName, new RamStore(), janitor);
		getCacheStat().setMaxSize(maxSizeInBytes);
	}
}
