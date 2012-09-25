package com.wm.gc.l1;

public class Cache {
	private IStore store;
	private String name;
	private Namespace namespace;
	private Janitor jaintor;

	public Cache(Namespace namespace, String name, IStore store, Janitor jaintor) {
		this.namespace = namespace;
		this.name = name;
		this.store = store;
		this.jaintor = jaintor;
		CacheManager.add(namespace, this);
	}

	public CacheEntry getCacheEntry(CacheKey key, String version) {
		return store.get(key, version);
	}

	public void putCacheEntry(CacheKey key, CacheEntry entry, String version) {
		store.put(key, entry, version);

		// TODO
		// getCacheStat().eventPut()
		// getJanitor().eventPut()
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
}
