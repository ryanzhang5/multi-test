package com.wm.gc.l1;

public interface IStore {
	public boolean put(CacheKey cacheKey, CacheEntry cacheEntry, String version);

	public CacheEntry get(CacheKey cacheKey, String version);

	public void removeAll();

	public CacheEntry remove(CacheKey key, String version);
	
	public boolean expireEntries(long timestamp);
	
	public CacheKey[] getAllKeys();
}
