package thinking.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import javax.servlet.jsp.tagext.TryCatchFinally;

public class ServerSocketChannelTest {

	public static void main(String[] args) {
		try {

			ServerSocketChannel server = ServerSocketChannel.open();
			server.configureBlocking(false);

			server.socket().bind(new InetSocketAddress(8087));
			Selector selector = Selector.open();
			server.register(selector, SelectionKey.OP_ACCEPT);
			int index = 0;
			while (true) {
				selector.select();
				Iterator iterator = selector.selectedKeys().iterator();
				while (iterator.hasNext()) {
					SelectionKey key = (SelectionKey) iterator.next();
					iterator.remove();
					if (key.isAcceptable()) {
						SocketChannel client = server.accept();
						System.out
								.println("Accepted connection from " + client);
						client.configureBlocking(false);

						SelectionKey key2 = client.register(selector,
								client.validOps());
						// key2.attach(source);
					}
					if (key.isWritable()) {
						SocketChannel client = (SocketChannel) key.channel();
						String outputString = (index++) + " ABCDEFGHIJKL";
						ByteBuffer output = ByteBuffer.wrap(outputString
								.getBytes());
						System.out.println("server write " + outputString
								+ " to client " + client);
						// ByteBuffer output = (ByteBuffer) key.attachment();
						output.rewind();
						try {
							client.write(output);		
						} catch (IOException e) {
							System.out.println(client + "get exception will be closed while write");
							client.close();
						}
						
					}
					if (key.isReadable()) {
						SocketChannel sChannel = (SocketChannel) key.channel();

						ByteBuffer buffer = ByteBuffer.allocate(128);
						try {
							sChannel.read(buffer);		
						} catch (IOException e) {
							System.out.println(sChannel + "get exception will be closed while read");
							sChannel.close();
						}
						
						buffer.flip();
						System.out.print("server read from" + sChannel);
						while (buffer.hasRemaining()) {
							System.out.print((char) buffer.get());
						}
						System.out.println();
					}
				}
			}
		} catch (Exception e) {
		} finally {
		}
	}
}
