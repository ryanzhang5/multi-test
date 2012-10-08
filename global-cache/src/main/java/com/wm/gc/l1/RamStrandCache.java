package com.wm.gc.l1;


public class RamStrandCache extends StrandCache {

	public RamStrandCache(Namespace namespace, String cacheName, long maxSizeInBytes,Janitor janitor) {
		super(namespace, cacheName, new RamStore(), janitor);
		getCacheStat().setMaxSize(maxSizeInBytes);
	}

	
	
	
	public static void main(String[] args) {
		RamStrandCache rsc = null;
		
		String v ;
		for(int i =0;i<70000000;i=i+3){
			
			byte[] bytes = new byte[555555];
			v = String.valueOf(i);
			rsc.putStrandTTL(CacheKey.getInstance(v), 333333, bytes);
			
			v = String.valueOf(i+1);
			rsc.putStrandTTL(CacheKey.getInstance(v), 333333, bytes);
			
			v = String.valueOf(i+2);
			rsc.putStrandTTL(CacheKey.getInstance(v), 333333, bytes);
			CacheKey key = CacheKey.getInstance(String.valueOf(i));
			
			byte[] data = rsc.getStrand(key);
			rsc.removeStrand(key);
		}
	}
}
