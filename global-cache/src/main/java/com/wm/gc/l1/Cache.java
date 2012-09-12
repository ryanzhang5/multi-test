package com.wm.gc.l1;

public class Cache {
private IStore store;


public CacheEntry getCacheEntry(CacheKey key,String version){
	return store.get(key, version);
}
}
