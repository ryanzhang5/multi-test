package com.wm.gc.l1;

import org.apache.log4j.Logger;

public class SpilloverStrandCache extends StrandCache {
	static Logger logger = Logger.getLogger(SpilloverStrandCache.class);
	private StrandCache secondaryStrandCache = null;
	private boolean isEntryNOTAllowedWhileJanitorRunning = false;

	public SpilloverStrandCache(Namespace namespace, String cacheName,
			String baseDir, long maxSizeInBytes, Janitor janitor) {
		super(namespace, cacheName, new RamStore(), janitor);
		secondaryStrandCache = new FileStrandCache(namespace, cacheName,
				baseDir);
		getCacheStat().setMaxSize(maxSizeInBytes);
	}

	public SpilloverStrandCache(Namespace namespace, String cacheName,
			String baseDir, long maxSizeInBytes, Janitor janitor,
			boolean isEntryNOTAllowedWhileJanitorRunning) {
		this(namespace, cacheName, baseDir, maxSizeInBytes, janitor);
		this.isEntryNOTAllowedWhileJanitorRunning = isEntryNOTAllowedWhileJanitorRunning;
	}
	
	public final void putCacheEntry(CacheKey key, CacheEntry entry, String version) {
		if(getCacheStat().getSize() < getCacheStat().getMaxSize() && (!isEntryNOTAllowedWhileJanitorRunning)){
			super.putCacheEntry(key, entry, null);
			logger.info("------------------------------------------------------------------------------------put into primary");
		}
		logger.debug("--------------------after put into primary, i will put into secondary");
		secondaryStrandCache.putCacheEntry(key, entry, null);
	}
	public final CacheEntry getCacheEntry(CacheKey key, String version) {
		//get entry from primary store
		CacheEntry entry =  super.getCacheEntry(key, null);
		
		//get entry from secondary store if cannot found from primary cache. and then put into primary cache
		if(entry ==null){
			entry = secondaryStrandCache.getCacheEntry(key, null);
			super.putCacheEntry(key, entry, null);
		}
		return entry;
	}
	public CacheEntry removeCacheEntry(CacheKey key) {
		logger.debug("------try to remove " + key);
		super.removeCacheEntry(key);
		secondaryStrandCache.removeCacheEntry(key);
		return null;
	}

	public void removeAll() {
		logger.debug("--------------------------remove all cache");
		super.removeAll();
		secondaryStrandCache.removeAll();
	}
}
