package thinking.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class BlockedSocketChannel {

	public static void main(String[] args) throws Exception {
		SocketChannel channel = null;
		try {
			channel = SocketChannel.open();
			channel.connect(new InetSocketAddress("localhost", 8087));

			ByteBuffer buffer = ByteBuffer.allocate(128);

			channel.read(buffer);
			buffer.flip();
			System.out.print("client read from " + channel);
			while (buffer.hasRemaining()) {
				System.out.print((char) buffer.get());
			}
			System.out.println();

		} catch (Exception e) {
			System.out.println("we get interrupted error here");
			// e.printStackTrace();
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
