package com.wm.gc.l2.drivers;

import com.wm.gc.l1.Cache;
import com.wm.gc.l1.CacheEntry;
import com.wm.gc.l1.CacheManager;
import com.wm.gc.l1.FileStrandCache;
import com.wm.gc.l1.Namespace;
import com.wm.gc.query.RequestHeader;
import com.wm.gc.query.WMQLQuery;

public abstract class WMQLDriver {
	RequestHeader header = null;
	WMQLQuery query = null;
	String rootDir = "/home/ryan/l2cache/";
	public static WMQLDriver getInstance(byte queryType) {
		if(queryType == WMQLQuery.WMQL_QUERY_TYPE_BINARY_CACHE){
			return new WMQLBCDriver();
		}else if (queryType == WMQLQuery.WMQL_QUERY_TYPE_DATABASE) {
			//TODO 
			return null;
		}
		return null;
	}



	protected Cache getCache(Namespace namespace, String cacheName) {
		Cache  stranCache = CacheManager.find(namespace,cacheName);
		if(stranCache == null){
			//TODO L2SOCacheConf
			stranCache = new FileStrandCache(namespace,cacheName,rootDir);
			return stranCache;
		}
		return null;
	}

	public CacheEntry execute(RequestHeader header, WMQLQuery query) {
		this.header = header;
		this.query = query;
		
		return implExecute();
		
	}

	public abstract CacheEntry implExecute();
	
	public RequestHeader getHeader() {
		return header;
	}

	public void setHeader(RequestHeader header) {
		this.header = header;
	}

	public WMQLQuery getQuery() {
		return query;
	}

	public void setQuery(WMQLQuery query) {
		this.query = query;
	}

}
