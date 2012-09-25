package com.wm.gc.l2.drivers;

import com.wm.gc.l1.Cache;
import com.wm.gc.l1.CacheEntry;
import com.wm.gc.l1.Namespace;
import com.wm.gc.query.RequestHeader;
import com.wm.gc.query.WMQLQuery;
import com.wm.gc.util.GCUtil;
import com.wm.gc.wireprotocol.WMQLCachePolicy;

public class WMQLBCDriver extends WMQLDriver {

	public CacheEntry implExecute() {
		CacheEntry entry = null;
		switch (getHeader().getMode()) {
		case GCUtil.WMQL_REQUEST_TYPE_GET:
			entry = doGet();
			break;

		default:
			break;
		}

		return entry;

	}

	public CacheEntry doGet() {
		Cache cache = getCache(getHeader().getNamespace(), getHeader()
				.getCacheName());
		CacheEntry entry = cache.getCacheEntry(getHeader().getCacheKey(), "");
		
		
		return entry;
	}

	public static void main(String[] args) {

		Namespace namespace2 = new Namespace("catalog", null, true, true, 0);
		RequestHeader header = new RequestHeader(GCUtil.WMQL_REQUEST_TYPE_GET,
				WMQLQuery.WMQL_QUERY_TYPE_BINARY_CACHE, new WMQLCachePolicy(0),
				namespace2, "mycachename", "5", null, null);
	
	
	WMQLDriver driver = new WMQLBCDriver();
	CacheEntry ce = driver.execute(header, null);
	
	}

}
