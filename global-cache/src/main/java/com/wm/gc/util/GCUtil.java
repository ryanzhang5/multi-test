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

	private static final long POLYNOMIAL = 0x04C11DB7L;

	private static long[] _crcTable = new long[256];
	private static boolean _tableIsGenerated = false;

	public static void writeBytes(byte[] data, DataOutputStream dataOutputStream)
			throws IOException {
		if(data == null){
			dataOutputStream.writeInt(-1);
			return ;
		}
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
		int length = dis.readInt();
		if(length == -1){return null;}
		byte bytes[] = new byte[length];
		dis.read(bytes);
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

	private static void genCRCTable() {
		int i, j;
		long crc_accum;

		for (i = 0; i < 256; i++) {
			crc_accum = ((long) i << 24);
			for (j = 0; j < 8; j++) {
				if ((crc_accum & 0x80000000L) > 0) {
					crc_accum = (crc_accum << 1) ^ POLYNOMIAL;
				} else {
					crc_accum = crc_accum << 1;
				}
			} // for
			_crcTable[i] = crc_accum;
		}
		_tableIsGenerated = true;

		return;
	}

	// Calculates the checksum using a CRC algorithm.
	// Returns the checksum, or -1 if an error occurs.
	public static long calcChecksum(byte[] blob) {
		int i, j;
		long crc_accum = 0;

		if (!_tableIsGenerated)
			genCRCTable();

		if (blob == null) {
			return -1;
		} // if ( blob == null )
		long len = blob.length;
		for (j = 0; j < len; j++) {
			i = ((int) (crc_accum >> 24) ^ ((int) blob[j])) & 0xff;
			crc_accum = (crc_accum << 8) ^ _crcTable[i];

		} // for

		return crc_accum;
	} // calcChocksum()

}
