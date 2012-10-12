package com.wm.gc.l1;

import org.apache.log4j.Logger;

public class SpilloverObjectCache extends ObjectCache {
	static Logger logger = Logger.getLogger(SpilloverObjectCache.class);
	private ObjectCache secondaryObjectCache = null;
	private boolean isEntryNOTAllowedWhileJanitorRunning = false;

	public SpilloverObjectCache(Namespace namespace, String cacheName,
			String baseDir, long maxSizeInBytes, Janitor janitor) {
		super(namespace, cacheName, new RamStore(), janitor);
		secondaryObjectCache = new FileObjectCache(namespace, cacheName,
				baseDir);
		getCacheStat().setMaxSize(maxSizeInBytes);
	}

	public SpilloverObjectCache(Namespace namespace, String cacheName,
			String baseDir, long maxSizeInBytes, Janitor janitor,
			boolean isEntryNOTAllowedWhileJanitorRunning) {
		this(namespace, cacheName, baseDir, maxSizeInBytes, janitor);
		this.isEntryNOTAllowedWhileJanitorRunning = isEntryNOTAllowedWhileJanitorRunning;
	}

	public CacheEntry getCacheEntry(CacheKey key, String version) {
		// get entry from primary store
		CacheEntry entry = super.getCacheEntry(key, null);

		// get entry from secondary store if cannot found from primary cache.
		// and then put into primary cache
		if (entry == null) {
			entry = secondaryObjectCache.getCacheEntry(key, null);
			super.putCacheEntry(key, entry, null);
		}
		return entry;
	}

	public ICacheObject getCacheObject(CacheKey key) {
		CacheEntry entry = getCacheEntry(key, null);
		return (entry == null) ? null : (ICacheObject) (entry.getData());
	}

	public final void putCacheEntry(CacheKey key, CacheEntry entry,
			String version) {
		if (getCacheStat().getSize() < getCacheStat().getMaxSize()
				&& (!isEntryNOTAllowedWhileJanitorRunning)) {
			super.putCacheEntry(key, entry, null);
			logger.info("------------------------------------------------------------------------------------put into primary");
		}
		logger.debug("--------------------after put into primary, i will put into secondary");
		secondaryObjectCache.putCacheEntry(key, entry, null);
	}

	public CacheEntry removeCacheEntry(CacheKey key) {
		logger.debug("------try to remove " + key);
		super.removeCacheEntry(key);
		secondaryObjectCache.removeCacheEntry(key);
		return null;
	}

	public void removeAll() {
		logger.debug("--------------------------remove all cache");
		super.removeAll();
		secondaryObjectCache.removeAll();
	}
}
