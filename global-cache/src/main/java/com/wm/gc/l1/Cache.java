package com.wm.gc.l1;

public class Cache {
	private IStore store;
	private String name;
	private Namespace namespace;
	private Janitor jaintor;
	private final CacheStat stat;

	public Cache(Namespace namespace, String name, IStore store, Janitor jaintor) {
		this.namespace = namespace;
		this.name = name;
		this.store = store;
		this.jaintor = jaintor;
		stat = new CacheStat(this);
		CacheManager.add(namespace, this);
	}

	public CacheEntry getCacheEntry(CacheKey key, String version) {
		return store.get(key, version);
	}

	public void putCacheEntry(CacheKey key, CacheEntry entry, String version) {
		boolean isNew = store.put(key, entry, version);

		getCacheStat().eventPut(key,entry,isNew);
		getJaintor().eventPut(key,entry);
	}

	public CacheEntry removeCacheEntry(CacheKey key){
		return store.remove(key, null);
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
