package com.wm.gc.l1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStore implements IStore {

	public boolean put(CacheKey cacheKey, CacheEntry cacheEntry, String version) {
		//TODO get a file name
		String fileName = "/home/ryan/30/31/32/33/12122";

		File file = new File(fileName);
		File dir = file.getParentFile();

		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			CacheEntry.writeCacheEntry(cacheEntry, dos);
		
			dos.close();
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public CacheEntry get(CacheKey cacheKey, String version) {
		CacheEntry entry = null;
		// TODO get a filename
		String fileName = "";
		File file = new File(fileName);
		DataInputStream dis = null;

		try {
			dis = new DataInputStream(new BufferedInputStream(
					new FileInputStream(file)));
			entry = CacheEntry.readCacheEntry(dis);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return entry;
	}

}
