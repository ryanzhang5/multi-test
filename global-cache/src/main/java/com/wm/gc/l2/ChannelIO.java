package com.wm.gc.l2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ChannelIO {
	private SocketChannel socketChannel;

	public SocketChannel getSocketChannel() {
		return socketChannel;
	}

	private ByteBuffer requestByteBuffer;

	private ChannelIO(SocketChannel socketChannel, boolean blocking) {
		this.socketChannel = socketChannel;
		try {
			socketChannel.configureBlocking(blocking);
			socketChannel.socket().setTcpNoDelay(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ChannelIO getInstance(SocketChannel socketChannel,
			boolean blocking) {
		ChannelIO channelIO = new ChannelIO(socketChannel, blocking);
		channelIO.requestByteBuffer = ByteBuffer.allocate(1024);
		return channelIO;
	}

	public int read() throws IOException {
		resizeRequestByteBuffer(requestByteBuffer.capacity() / 20);
		int count = socketChannel.read(requestByteBuffer);
		return count;
	}

	private void resizeRequestByteBuffer(int remaining) {
		if (requestByteBuffer.remaining() < remaining) {
			ByteBuffer temp = ByteBuffer
					.allocate(requestByteBuffer.capacity() * 2);
			requestByteBuffer.flip();
			temp.put(requestByteBuffer);
			requestByteBuffer = temp;
		}
	}

	public boolean checkComplete() {
		if (requestByteBuffer.position() < 3)
			return false;
		int position = requestByteBuffer.position() - 3;
		return requestByteBuffer.get(position) == 'T'
				&& requestByteBuffer.get(position + 1) == 'T'
				&& requestByteBuffer.get(position + 2) == 'T';
	}

	public ByteBuffer getRequestByteBuffer() {
		return requestByteBuffer;
	}

	public void write(ByteBuffer responseByteBuffer) {
		try {
			while (responseByteBuffer.hasRemaining()) {
				socketChannel.write(responseByteBuffer);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			socketChannel.shutdownInput();
			socketChannel.shutdownOutput();
			socketChannel.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
