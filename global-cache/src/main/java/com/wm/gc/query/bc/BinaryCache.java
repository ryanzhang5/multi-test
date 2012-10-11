package com.wm.gc.query.bc;

import java.io.File;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.wm.gc.l1.Cache;
import com.wm.gc.l1.CacheEntry;
import com.wm.gc.l1.CacheKey;
import com.wm.gc.l1.CacheManager;
import com.wm.gc.l1.FileStrandCache;
import com.wm.gc.l1.Namespace;
import com.wm.gc.wireprotocol.WMQLCachePolicy;
import com.wm.gc.wireprotocol.WMQLConnectionDescriptor;

public class BinaryCache {
	public static final Logger logger = Logger.getLogger(BinaryCache.class);
	public static final byte WMQL_QUERY_TYPE_DATABASE=1;
	public static final byte WMQL_QUERY_TYPE_BINARY_CACHE=2;

public static CacheEntry getFromL1Only(Cache l1,CacheKey key,String version){
	return l1.getCacheEntry(key, version);
}	
	
public static void putIntoL1(Cache l1,CacheKey key,Object data,long ttlSeconds,String version){
	CacheEntry entry = CacheEntry.getInstance(ttlSeconds*1000, data);
	logger.debug("----------------putIntoL1");
	l1.putCacheEntry(key, entry, version);
}	
	
public static CacheEntry getFromL1Only(Cache l1,CacheKey key){
	logger.debug("----------------getFromL1Only");
	CacheEntry entry = l1.getCacheEntry(key, null);
	return entry;
}	
		
public static CacheEntry getFromL1OrL2(Cache l1,CacheKey key){
	CacheEntry entry = getFromL1Only(l1, key);
	if(entry !=null){
		return entry;
	}
	
	try {
		entry =  get(null,l1.getNamespace() , l1.getName(), key, null);
		
		if(entry != null){
			 putIntoL1(l1,key,entry.getData(),1000,null);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	return entry;
}	

public synchronized static CacheEntry get(WMQLConnectionDescriptor wmqlcd,Namespace ns,String cacheName,CacheKey key,String version)throws Exception{
	logger.debug("----------------get");
	WMQLBCQuery query = new WMQLBCQuery(version);
	query.setKey(key.getKey());
	query.setModeType(com.wm.gc.util.GCUtil.WMQL_REQUEST_TYPE_GET);
	return query.clientExecute(wmqlcd,new WMQLCachePolicy(0),ns,cacheName);
}
public static void main(String[] args) {
	
	ExecutorService executor = Executors.newCachedThreadPool();
	for (int i = 0; i < 500; i++) {
		executor.execute(new Runnable() {
			
			public void run() {
				Namespace namespace2 = new Namespace("catalog", null, true, true, 0);
				
				Cache cache = null;
				cache = CacheManager.find(namespace2, "mycachename");
				if(cache ==null){
					//cache = new FileStrandCache(namespace2,"mycachename","/home/ryan/itemcache/");
					cache = new FileStrandCache(namespace2,"mycachename","c:"+File.separator+"itemcache"+File.separator);
				}
			 try {
				Random random = new Random();
				
				CacheEntry entry = BinaryCache.get(null, namespace2, "mycachename", CacheKey.getInstance(""+random.nextInt(40)), null);
				// CacheEntry entry = getFromL1OrL2(cache, CacheKey.getInstance("9"));
				 byte[] bytes = (byte[])(entry.getData());
			      
				System.out.println("--------------------------------" + Thread.currentThread().getName() + "end, output is "+new String(bytes));
			} catch (Exception e) {
				e.printStackTrace();
			}
				
			}
		});
	}
	
	
	



}

}
