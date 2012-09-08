package com.wm.gc.l2;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

public class WMQLBCQuery implements Runnable {
	static Logger logger = Logger.getLogger(WMQLBCQuery.class);

	public void run() {
		try {
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress("localhost", 9765));
			OutputStream outputStream = socket.getOutputStream();
			InputStream inputStream = socket.getInputStream();
			// socket.setSoTimeout(2 * 1000);

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(
					byteArrayOutputStream);
			List<CacheEntry> list = new ArrayList<CacheEntry>();
			list.add(new CacheEntry("1", "myname1"));
			list.add(new CacheEntry("2", "myname2"));
			oos.writeObject(list);

			DataOutputStream dataOutputStream = new DataOutputStream(
					outputStream);

			dataOutputStream.writeInt(byteArrayOutputStream.size());
			dataOutputStream.write(byteArrayOutputStream.toByteArray(), 0,
					byteArrayOutputStream.size());

			outputStream.flush();
			logger.debug("client sent out things, now socket will shutdown");
			socket.shutdownOutput();

			byte[] bytes = new byte[4096];
			inputStream.read(bytes);

			logger.info("client get info " + new String(bytes));

			socket.shutdownInput();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 15; i++) {
			service.execute(new WMQLBCQuery());
		}

	}
}
