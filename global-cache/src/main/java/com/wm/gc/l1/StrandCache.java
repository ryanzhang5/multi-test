package com.wm.gc.l1;

public class StrandCache extends Cache {

	public StrandCache(Namespace namespace, String name, IStore store,
			Janitor jaintor) {
		super(namespace, name, store, jaintor);
	}

	public byte[] getStrand(CacheKey key) {
		CacheEntry entry = getCacheEntry(key, null);
		return (entry == null) ? null : (byte[]) entry.getData();
	}

	public void putStrandTTL(CacheKey key, long ttl, byte[] data) {
		putCacheEntry(key, CacheEntry.getInstance(ttl, data), null);
	}

	public byte[] removeStrand(CacheKey key) {
		CacheEntry entry = removeCacheEntry(key);
		return (entry == null) ? null : (byte[]) entry.getData();
	}
}
