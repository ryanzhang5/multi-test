package com.wm.gc.l1;

import java.util.concurrent.ConcurrentHashMap;

public class CacheManager {
public static final  ConcurrentHashMap<Namespace, ConcurrentHashMap<String, Cache>>  allCaches=new ConcurrentHashMap<Namespace, ConcurrentHashMap<String,Cache>>();
	public synchronized static Cache find(Namespace namespace, String cacheName) {
		ConcurrentHashMap<String, Cache> cacheMap = getCacheMap(namespace);
		if (cacheMap == null) {
			cacheMap = new ConcurrentHashMap<String, Cache>();
			allCaches.put(namespace, cacheMap);
			return null;
		}
		return cacheMap.get(cacheName);
	}
	
	
	public synchronized static void add(Namespace namespace,Cache cache) {
		ConcurrentHashMap<String, Cache> cacheMap = getCacheMap(namespace);
		if(!cacheMap.containsKey(cache.getName())){
			cacheMap.put(cache.getName(), cache);
		}
	}
	
	
	private static ConcurrentHashMap<String, Cache> getCacheMap(Namespace namespace){
		return allCaches.get(namespace);
	}

}
