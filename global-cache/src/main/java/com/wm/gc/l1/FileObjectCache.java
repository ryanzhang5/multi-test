package com.wm.gc.l1;

public class FileObjectCache extends ObjectCache {
	public FileObjectCache(Namespace namespace, String cacheName, String baseDir) {
		super(namespace, cacheName,
				new FileStore(namespace, cacheName, baseDir), new Janitor());
	}

}
