package com.wm.gc.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GCUtil {
	public static final int MODE = 0x01;
	public static final int QUERY_TYPE = 0x02;
	public static final int POLICY = 0x03;
	public static final int NAMESPACE_NAME = 0x04;
	public static final int CACHE_NAME = 0x05;
	public static final int CACHE_KEY = 0x06;
	public static final int VERSION = 0x07;
	public static final int TRANSACTION_ID = 0x08;

	public static final byte WMQL_REQUEST_TYPE_GET = 0;

	public static void writeBytes(byte[] data, DataOutputStream dataOutputStream)
			throws IOException {
		dataOutputStream.writeInt(data.length);
		dataOutputStream.write(data);
	}

	public static Object readObject(DataInputStream dataInputStream)
			throws IOException, ClassNotFoundException {
		int count = dataInputStream.readInt();
		byte bytes[] = new byte[count];
		dataInputStream.readFully(bytes);

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				bytes);
		ObjectInputStream objectInputStream = new ObjectInputStream(
				byteArrayInputStream);

		return objectInputStream.readObject();

	}

	public static byte[] readBytes(DataInputStream dis) throws IOException {
		byte bytes[] = new byte[dis.readInt()];
		dis.read(bytes, 0, bytes.length);
		return bytes;
	}

	public static void writeObject(Object object,
			DataOutputStream dataOutputStream) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(bo);
		objectOutputStream.writeObject(object);

		dataOutputStream.writeInt(bo.size());
		dataOutputStream.write(bo.toByteArray());

		objectOutputStream.close();

	}
}
