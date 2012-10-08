package com.wm.gc.l1;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RamStore implements IStore {
	private final ConcurrentHashMap<CacheKey, CacheEntry> store = new ConcurrentHashMap<CacheKey, CacheEntry>();

	public boolean put(CacheKey cacheKey, CacheEntry cacheEntry, String version) {
		CacheEntry entry = store.put(cacheKey, cacheEntry);
		return entry==null;
	}

	public CacheEntry get(CacheKey cacheKey, String version) {
		return store.get(cacheKey);
	}

	public void removeAll() {
		store.clear();

	}

	public CacheEntry remove(CacheKey key, String version) {
		return store.remove(key);
	}

	public CacheKey[] getAllKeys() {
		Set<CacheKey> set = store.keySet();
		CacheKey keys[] = new CacheKey[set.size()];
		set.toArray(keys);
		return keys;
	}

	public boolean expireEntries(long timestamp) {
		for (CacheKey key : store.keySet()) {
			CacheEntry entry = store.get(key);
			if (entry.getExpireTime() < timestamp) {
				remove(key, null);
			}
		}
		return true;
	}

}
