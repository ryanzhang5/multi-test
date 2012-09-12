package com.wm.gc.wireprotocol;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Wire {

	public static byte[] pack(byte[] data ) throws IOException{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		
		dos.writeInt(data.length);
		dos.write(data,0,data.length);
		return bos.toByteArray();
	}

	public static byte[] unpack(InputStream is) throws IOException {
		DataInputStream dis = new DataInputStream(is);
		byte bytes [] = new byte[dis.readInt()];
		dis.read(bytes);
		return bytes;
	}
	
}
