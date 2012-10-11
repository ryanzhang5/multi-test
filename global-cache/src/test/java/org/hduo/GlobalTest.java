package org.hduo;

import java.io.File;

import junit.framework.TestCase;

import com.wm.gc.l1.Cache;
import com.wm.gc.l1.CacheKey;
import com.wm.gc.l1.FileStrandCache;
import com.wm.gc.l1.MemoryBasedSpilloverEnforcer;
import com.wm.gc.l1.Namespace;
import com.wm.gc.l1.SpilloverStrandCache;
import com.wm.gc.query.bc.BinaryCache;

public class GlobalTest extends TestCase {
	// test insert into L1
	public void testFileStrandCache() {
		String data = "aaaaaaaaaa";
		for (int i = 0; i < 50000; i++) {

			Namespace namespace2 = new Namespace("catalog", null, true, true, 0);

			Cache cache = null;
			// cache = new
			// FileStrandCache(namespace2,"mycachename","/home/ryan/itemcache/");
			cache = new FileStrandCache(namespace2, "mycachename", "e:"
					+ File.separator + "itemcache" + File.separator);
			try {
				BinaryCache.putIntoL1(cache,
						CacheKey.getInstance(Integer.toString(i)),
						data.getBytes(), 100, null);

				System.out
						.println("putting-------------------------------" + i);
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}
	
	
	public void testSpilloverStrandCache() {
		Namespace namespace = new Namespace("catalog", "jdbcpool_catalog",true, true, 0);
		String baseDir = "e:"+ File.separator + "itemcache" + File.separator;
		SpilloverStrandCache rsc = new SpilloverStrandCache(namespace, "catalog",baseDir, 1024, new MemoryBasedSpilloverEnforcer(0.75, 10, 10*1000));
		String v ;
		for(int i =0;i<70000000;i=i+3){
			
			byte[] bytes = new byte[5];
			v = String.valueOf(i);
			rsc.putStrandTTL(CacheKey.getInstance(v), 333333, bytes);
			
			v = String.valueOf(i+1);
			rsc.putStrandTTL(CacheKey.getInstance(v), 333333, bytes);
			
			v = String.valueOf(i+2);
			rsc.putStrandTTL(CacheKey.getInstance(v), 333333, bytes);
			CacheKey key = CacheKey.getInstance(String.valueOf(i));
			
			//byte[] data = rsc.getStrand(key);
			//rsc.removeStrand(key);
		}
	}
}
