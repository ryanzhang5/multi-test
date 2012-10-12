package com.wm.gc.l1;

public class ObjectCache extends Cache {
	public ObjectCache(Namespace namespace, String name, IStore store,
			Janitor jaintor) {
		super(namespace, name, store, jaintor);
	}

	public ICacheObject getCachedObject(CacheKey key) {
		CacheEntry entry = getCacheEntry(key, null);
		return (entry == null) ? null : (ICacheObject) entry.getData();
	}

	public void putCachedObjectTTL(CacheKey key, long ttl, ICacheObject object) {
		putCacheEntry(key, CacheEntry.getInstance(ttl, object), null);
	}

	public ICacheObject removeCachedObject(CacheKey key) {
		CacheEntry entry = removeCacheEntry(key);
		return (entry == null) ? null : (ICacheObject) entry.getData();
	}
}