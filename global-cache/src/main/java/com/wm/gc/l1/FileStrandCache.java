package com.wm.gc.l1;

public class FileStrandCache extends StrandCache {

	public FileStrandCache(Namespace namespace, String cacheName, String baseDir) {
		super(namespace, cacheName, new FileStore(namespace, cacheName,baseDir), new Janitor());
	}

}
