package com.wm.gc.l1;

import org.apache.log4j.Logger;

public class Cache {
	private IStore store;
	private String name;
	private Namespace namespace;
	private Janitor jaintor;
	private final CacheStat stat;
	static Logger logger = Logger.getLogger(Cache.class);

	public Cache(Namespace namespace, String name, IStore store, Janitor jaintor) {
		this.namespace = namespace;
		this.name = name;
		this.store = store;
		this.jaintor = jaintor;
		jaintor.setCache(this);
		stat = new CacheStat(this);
		// CacheManager.add(namespace, this);
	}

	public CacheEntry getCacheEntry(CacheKey key, String version) {
		CacheEntry entry = store.get(key, version);
		if (entry != null) {
			entry.eventGet();
		}
		getCacheStat().eventGet(key, entry);
		getJaintor().eventGet(key, entry);
		return entry;
	}

	public void putCacheEntry(CacheKey key, CacheEntry entry, String version) {
		logger.info("------------------------------------------------------input "
				+ key);
		boolean isNew = store.put(key, entry, version);
		entry.eventPut();
		getCacheStat().eventPut(key, entry, isNew);
		getJaintor().eventPut(key, entry);
	}

	public CacheEntry removeCacheEntry(CacheKey key) {
		logger.info("------------------------------------------------------remove "
				+ key);
		CacheEntry entry = store.remove(key, null);
		getCacheStat().eventRemoveCacheEntry(key, entry);
		getJaintor().eventRemove(key, entry);
		return entry;
	}
	
	public CacheEntry removeCacheEntryFromPrimaryStore(CacheKey key) {
		logger.info("------------------------------------------------------remove "
				+ key);
		CacheEntry entry = store.remove(key, null);
		getCacheStat().eventRemoveCacheEntry(key, entry);
		getJaintor().eventRemove(key, entry);
		return entry;
	}

	public void removeAll() {
		logger.info("------------------------------------------------------remove all cache");

		store.removeAll();
		getCacheStat().eventRemoveAll();
		getJaintor().eventRemoveAll();
	}

	public void setStore(IStore store) {
		this.store = store;
	}

	public String getName() {
		return name;
	}

	public Namespace getNamespace() {
		return namespace;
	}

	public CacheStat getCacheStat() {
		return stat;
	}

	public Janitor getJaintor() {
		return jaintor;
	}

	public IStore getStore() {
		return store;
	}

	public CacheKey[] getAllKeys() {
		return store.getAllKeys();
	}
}
