package com.wm.gc.query.bc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.wm.gc.l1.CacheEntry;
import com.wm.gc.l1.Namespace;
import com.wm.gc.query.RequestHeader;
import com.wm.gc.query.WMQLQuery;
import com.wm.gc.util.GCUtil;
import com.wm.gc.wireprotocol.Request;
import com.wm.gc.wireprotocol.Response;
import com.wm.gc.wireprotocol.WMQLCachePolicy;
import com.wm.gc.wireprotocol.WMQLConnectionDescriptor;

public class WMQLBCQuery extends WMQLQuery {
	private byte[] data = null;
	private String versoin;
	private byte mode;
	private String key;

	public WMQLBCQuery(String version) {
		this.versoin = version;
	}

	private void impClientExecute() {

	}

	public final CacheEntry impClientExecute(WMQLConnectionDescriptor wmqlcd,
			WMQLCachePolicy policy, Namespace ns, String cacheName) {

		InetAddress address;
		try {
			address = InetAddress.getByName("localhost");
			return impClientExecute(policy, ns, cacheName, address, 9905);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return null;

	}

	public CacheEntry impClientExecute(WMQLCachePolicy policy, Namespace ns,
			String cacheName, InetAddress address, int port) {
		Socket socket = new Socket();
		RequestHeader header = new RequestHeader(getMode(),
				GCUtil.WMQL_REQUEST_TYPE_GET, policy, ns, cacheName, getKey(),
				getVersoin(), null);

		try {
			socket.setReuseAddress(true);
			socket.setSoTimeout(3 * 1000);
			socket.connect(new InetSocketAddress(address,port), 5 * 1000);

			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();

			Request.writeRequest(out, header, this);

			out.flush();

			socket.shutdownOutput();

			CacheEntry rv = Response.readResponse(in);

			socket.shutdownInput();
			socket.close();

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public  void readRequest(DataInputStream dis)throws IOException{
		this.data = GCUtil.readBytes(dis);
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getVersoin() {
		return versoin;
	}

	public void setVersoin(String versoin) {
		this.versoin = versoin;
	}

	public byte getMode() {
		return mode;
	}

	public void setMode(byte mode) {
		this.mode = mode;
	}

	public String getKey() {
		return key;
	}

	public void writeRequest(DataOutputStream dataOutputStream)
			throws IOException {
		GCUtil.writeBytes(data, dataOutputStream);
	}

	public void setModeType(byte b) {
		this.mode = b;

	}

	public void setKey(String key) {
		this.key = key;

	}

}
