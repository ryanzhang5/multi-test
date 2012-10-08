package com.wm.gc.l1;

public class Janitor {
protected Cache cache = null;
protected long lastCleanDtm = 0;
protected long lastReclaimedCount = 0;
protected long lastExpiredCount = 0;
protected long lastIdleCount = 0;
protected long lastAccessCount = 0;
public Janitor(){}
public final void eventPut(CacheKey key,CacheEntry entry){
	impEventPut(key,entry);
}
public final void eventGet(CacheKey key,CacheEntry entry){
	impEventGet(key,entry);
}
public final void eventRemove(CacheKey key,CacheEntry entry){
	impEventRemove(key,entry);
}
public final void eventRemoveAll(){
	impEventRemoveAll();
}

protected void impEventPut(CacheKey key,CacheEntry entry){}
protected void impEventGet(CacheKey key,CacheEntry entry){}
protected void impEventRemove(CacheKey key,CacheEntry entry){}
protected void impEventRemoveAll(){}

public Cache getCache() {
	return cache;
}
public void setCache(Cache cache) {
	this.cache = cache;
}
public long getLastCleanDtm() {
	return lastCleanDtm;
}
public long getLastReclaimedCount() {
	return lastReclaimedCount;
}
public long getLastExpiredCount() {
	return lastExpiredCount;
}
public long getLastIdleCount() {
	return lastIdleCount;
}
public long getLastAccessCount() {
	return lastAccessCount;
}


}
