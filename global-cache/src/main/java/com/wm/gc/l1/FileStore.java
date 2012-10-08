package com.wm.gc.l1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class FileStore implements IStore {
	static Logger logger = Logger.getLogger(FileStore.class);
	private long expireTimestamp = 0;
	private String baseDir = "/home/ryan/itemcache/l1";
	private Namespace namespace;
	private String expiredFileName = null;

	public FileStore(Namespace namespace, String cacheName) {
		baseDir = baseDir + namespace.getName() + File.separator + cacheName
				+ File.separator;
		expiredFileName = baseDir + "expire_timestamp.exp";

		File expireFile = new File(expiredFileName);
		if (expireFile.exists()) {
			DataInputStream dis;
			try {
				dis = new DataInputStream(new FileInputStream(expireFile));
				expireTimestamp = dis.readLong();
				dis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public FileStore(Namespace namespace, String cacheName, String _baseDir) {
		baseDir = _baseDir + namespace.getName() + File.separator + cacheName
				+ File.separator;
		expiredFileName = baseDir + "expire_timestamp.exp";

		File expireFile = new File(expiredFileName);
		if (expireFile.exists()) {
			DataInputStream dis;
			try {
				dis = new DataInputStream(new FileInputStream(expireFile));
				expireTimestamp = dis.readLong();
				dis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public boolean put(CacheKey cacheKey, CacheEntry cacheEntry, String version) {

		String fileName = FileKey.getFileName(baseDir, cacheKey, null);
		logger.debug("fileKey " + cacheKey.getKey() + " generated filename "
				+ fileName);
		File file = new File(fileName);
		File dir = file.getParentFile();

		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			DataOutputStream dos = new DataOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)));
			CacheEntry.writeCacheEntry(cacheEntry, dos);

			dos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public CacheEntry get(CacheKey cacheKey, String version) {
		CacheEntry entry = null;
		String fileName = FileKey.getFileName(baseDir, cacheKey, null);
		File file = new File(fileName);
		DataInputStream dis = null;

		try {

			dis = new DataInputStream(new BufferedInputStream(
					new FileInputStream(file)));
			entry = CacheEntry.readCacheEntry(dis);

			long createTime = entry.getCreateTime();

			if (createTime < expireTimestamp) {
				file.delete();
			}

			// return entry;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dis.close();
			} catch (Exception e) {
				logger.error("-------------------we got error herer");
				e.printStackTrace();
			}

		}
		return entry;
	}

	public void removeAll() {
		expireTimestamp = System.currentTimeMillis();
		expireEntries(expireTimestamp);
	}

	public boolean expireEntries(long timestamp) {
		File expFile = new File(expiredFileName);

		File dir = expFile.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}

		DataOutputStream dis;
		try {
			dis = new DataOutputStream(new FileOutputStream(expFile));
			dis.writeLong(timestamp);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public CacheEntry remove(CacheKey key, String version) {
		CacheEntry entry = get(key, version);

		String fileName = FileKey.getFileName(baseDir, key, null);

		File file = new File(fileName);

		if (file.exists()) {
			file.delete();
		}

		return entry;
	}

	public static void main(String[] args) {
		Namespace namespace = new Namespace("catalog", "jdbcpool_catalog",
				true, true, 0);
		FileStore store = new FileStore(namespace, "mycachename",
				"/home/ryan/l2cache/");
		for (int i = 0; i < 50; i++) {
			CacheKey key = CacheKey.getInstance("" + i);
			logger.debug(i + " try to write file " + key.getKey());
			String data = ("mydata" + i);
			store.put(key, CacheEntry.getInstance(i, data.getBytes()), null);

		}
		CacheKey key = CacheKey.getInstance("5");

		CacheEntry entry = store.get(key, "");
		byte[] bytes = (byte[]) entry.getData();

		/*
		 * ObjectInputStream objis; try { objis = new ObjectInputStream(new
		 * ByteArrayInputStream(bytes)); byte[] bytes2 =
		 * (byte[])objis.readObject(); String s = new String(bytes2);
		 * logger.debug("data from cache=============="+s); } catch (Exception
		 * e1) { e1.printStackTrace(); }
		 */
		logger.debug("=====================" + new String(bytes));

	}

	public CacheKey[] getAllKeys() {
		return null;
	}

	
}
