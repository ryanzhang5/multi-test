package com.wm.gc.query.bc;

import com.wm.gc.l1.CacheEntry;
import com.wm.gc.l1.CacheKey;
import com.wm.gc.l1.Namespace;
import com.wm.gc.util.GCUtil;
import com.wm.gc.wireprotocol.WMQLCachePolicy;
import com.wm.gc.wireprotocol.WMQLConnectionDescriptor;

public class BinaryCache {
	
	

public static CacheEntry getFromL1Only(Cache l1,CacheKey key,String version){
}	
	
	
	
	
	
public static CacheEntry get(WMQLConnectionDescriptor wmqlcd,Namespace ns,String cacheName,CacheKey key,String version)throws Exception{
	WMQLBCQuery query = new WMQLBCQuery(version);
	query.setKey(key.getKey());
	query.setModeType(GCUtil.WMQL_REQUEST_TYPE_GET);
	
	//TODO     why cache policy is 0
	return query.clientExecute(wmqlcd,new WMQLCachePolicy(0),ns,cacheName);
}
}
