package com.wm.gc.query;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wm.gc.l1.CacheEntry;
import com.wm.gc.l1.Namespace;
import com.wm.gc.query.bc.WMQLBCQuery;
import com.wm.gc.wireprotocol.WMQLCachePolicy;
import com.wm.gc.wireprotocol.WMQLConnectionDescriptor;

public abstract class WMQLQuery {

	public static final byte WMQL_QUERY_TYPE_BINARY_CACHE =2;
	
	public static WMQLQuery getInstance(byte queryType) {
		switch (queryType) {
		case WMQL_QUERY_TYPE_BINARY_CACHE:	
				
			return new WMQLBCQuery(null);

		default:
			break;
		};
		return null;
	}
	
	public final CacheEntry clientExecute(WMQLConnectionDescriptor wmqlcd,
			WMQLCachePolicy wmqlCachePolicy, Namespace ns, String cacheName) {
		return impClientExecute(wmqlcd, wmqlCachePolicy, ns, cacheName);
	}
	
	
	public abstract  CacheEntry impClientExecute(WMQLConnectionDescriptor wmqlcd,
			WMQLCachePolicy wmqlCachePolicy, Namespace ns, String cacheName);
	
	public abstract void writeRequest(DataOutputStream dataOutputStream)throws IOException;

	public abstract void readRequest(DataInputStream dis) throws IOException;



}
