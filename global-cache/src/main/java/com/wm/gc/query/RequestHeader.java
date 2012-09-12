package com.wm.gc.query;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.wm.gc.l1.CacheKey;
import com.wm.gc.l1.Namespace;
import com.wm.gc.util.GCUtil;
import com.wm.gc.wireprotocol.WMQLCachePolicy;

public class RequestHeader {
	private int mode = 0;
	private byte queryType = -1;
	private WMQLCachePolicy policy = null;
	private String namespaceName = null;
	private Namespace namespace = null;
	private String cacheName = "L2ServerCatalogCache";
	private CacheKey cacheKey = null;
	private String version = null;
	private String sGUID = null;

	public RequestHeader() {
	}

	public RequestHeader(int mode, byte queryType, WMQLCachePolicy policy,
			Namespace namespace, String cacheName, String cacheKey,
			String version, String guid) {
		this.mode = mode;
		this.queryType = queryType;
		this.policy = policy;
		this.namespace = namespace;
		// TODO namespaceName
		this.cacheName = cacheName;
		this.cacheKey = CacheKey.getInstance(cacheKey);
		this.version = version;

	}

	public void write(DataOutputStream dataOutputStream) throws IOException {
		HashMap<Integer, Object> headerMap = new HashMap<Integer, Object>();
		headerMap.put(GCUtil.MODE, Integer.valueOf(mode));
		headerMap.put(GCUtil.QUERY_TYPE, new Byte(queryType));
		headerMap.put(GCUtil.NAMESPACE_NAME, namespaceName);
		headerMap.put(GCUtil.POLICY, Long.valueOf(policy.getPolicy()));
		headerMap.put(GCUtil.CACHE_NAME, cacheName);
		headerMap.put(GCUtil.CACHE_KEY, cacheKey.getKey());
		headerMap.put(GCUtil.VERSION, version);
		headerMap.put(GCUtil.TRANSACTION_ID, sGUID);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				byteArrayOutputStream);
		objectOutputStream.writeObject(headerMap);

		dataOutputStream.writeInt(byteArrayOutputStream.size());
		dataOutputStream.write(byteArrayOutputStream.toByteArray());

		objectOutputStream.close();
	}

	public void read(DataInputStream dis) throws IOException {
		HashMap headerMap = new HashMap();
		int length = dis.readInt();
		byte bytes[] = new byte[length];
		dis.read(bytes);
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(
				bytes));
		Object obj;
		try {
			obj = ois.readObject();
			if (obj != null && obj instanceof HashMap) {
				headerMap = (HashMap) obj;

				mode = (Integer) headerMap.get(GCUtil.MODE);
				queryType = (Byte) headerMap.get(GCUtil.QUERY_TYPE);
				policy = new WMQLCachePolicy(
						(Long) headerMap.get(GCUtil.POLICY));
				namespaceName = (String) headerMap.get(GCUtil.NAMESPACE_NAME);
				// TODO namespace = Namespace.findNamespace(namespacename);
				namespace = null;
				cacheName = (String) headerMap.get(GCUtil.CACHE_NAME);
				cacheKey = CacheKey.getInstance((String) headerMap
						.get(GCUtil.CACHE_KEY));
				version = (String) headerMap.get(GCUtil.VERSION);

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public byte getQueryType() {
		return queryType;
	}

}
