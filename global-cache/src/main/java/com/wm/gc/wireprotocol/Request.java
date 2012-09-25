package com.wm.gc.wireprotocol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.wm.gc.query.RequestHeader;
import com.wm.gc.query.WMQLQuery;

public class Request {

	public static void writeRequest(OutputStream outputStream,
			RequestHeader header, WMQLQuery query) throws IOException {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataOutputStream = new DataOutputStream(byteStream);

		header.write(dataOutputStream);
		query.writeRequest(dataOutputStream);

		dataOutputStream.close();

		byte[] bytes = byteStream.toByteArray();
		
		byte msgBytes [] = Wire.pack(bytes);
		outputStream.write(msgBytes);
		outputStream.flush();

	}

	public static WMQLQuery readRquest(InputStream is,RequestHeader requestHeader) throws Exception{
		
		byte msgBytes [] = Wire.unpack(is);
		
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(msgBytes));
		
		requestHeader.read(dis);
		
		WMQLQuery query = WMQLQuery.getInstance(requestHeader.getQueryType());
		query.readRequest(dis);
		return query;
	}
}
