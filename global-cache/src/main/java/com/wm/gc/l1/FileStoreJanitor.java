package com.wm.gc.l1;

public class FileStoreJanitor extends Janitor {
	public void impEventPut(CacheKey key, CacheEntry entry) {
	}

	public void impEventGet(CacheKey key, CacheEntry entry) {
	}

	public void impEventRemove(CacheKey key, CacheEntry entry) {
		getCache().getStore().remove(key, null);
	}

	public void impEventRemoveAll() {
		getCache().getStore().removeAll();
	}
}
