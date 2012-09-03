package thinking.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class SocketChannelTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SocketChannel channel = null;
		try {
			channel = SocketChannel.open();
			channel.connect(new InetSocketAddress("localhost", 8087));
			ByteBuffer buffer = ByteBuffer.wrap("some text111".getBytes());

			buffer = ByteBuffer.allocate(48);
			buffer.flip();

			while (buffer.hasRemaining()) {
				channel.write(buffer);
			}
			
			TimeUnit.SECONDS.sleep(1);
			ByteBuffer buffer2 = ByteBuffer.allocate(1024);
			System.out.println("May stop here");
			long start = System.currentTimeMillis();
			channel.read(buffer2);
			System.out.println(System.currentTimeMillis()-start);
			buffer2.flip();
			while(buffer2.hasRemaining()){
				System.out.println((char)buffer2.get());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
