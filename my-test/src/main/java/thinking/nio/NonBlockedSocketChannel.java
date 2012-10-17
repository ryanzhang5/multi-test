package thinking.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import bsh.This;

public class NonBlockedSocketChannel implements Runnable {
	public static int count =1;
	public final int id = count++;
	public void run() {
		
		SocketChannel channel = null;
		try {
			channel = SocketChannel.open();
			channel.configureBlocking(false);
			channel.connect(new InetSocketAddress("localhost", 8087));
			Selector selector = Selector.open();
			channel.register(selector, channel.validOps());
			int index = 1;
			while (!Thread.interrupted()) {

				selector.select();
				Iterator iterator = selector.selectedKeys().iterator();
				while (iterator.hasNext()) {
					SelectionKey key = (SelectionKey) iterator.next();
					iterator.remove();

					if (key.isConnectable()) {
						System.out.println("now client connected");
						SocketChannel sChannel = (SocketChannel) key.channel();
						System.out.println("socket channel isconnectable "
								+ sChannel.finishConnect());
						/*
						 * ByteBuffer buffer = ByteBuffer.wrap("some text111"
						 * .getBytes()); key.attach(buffer);
						 */
					}
					if (key.isReadable()) {
						SocketChannel sChannel = (SocketChannel) key.channel();

						ByteBuffer buffer = ByteBuffer.allocate(128);

						sChannel.read(buffer);
						buffer.flip();
						System.out.print("client read from " + sChannel );
						while (buffer.hasRemaining()) {
							System.out.print((char) buffer.get());
						}
						System.out.println();
					}
					if (key.isWritable()) {
						SocketChannel client = (SocketChannel) key.channel();
						String outputString = (index++) + " XYZ";
						ByteBuffer output = ByteBuffer.wrap(outputString
								.getBytes());
						System.out.println( "client write " + outputString + " to " + client);
						// ByteBuffer output = (ByteBuffer) key.attachment();
						output.rewind();
						client.write(output);
					}
				}

			}
		} catch (Exception e) {
			System.out.println("we get interrupted error here");
			//e.printStackTrace();
		} finally {
			try {
				System.out.println(id + "-------------------thread terminate ");
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception{
		ExecutorService es = Executors.newCachedThreadPool();
		for (int i = 0; i <500; i++) {
			es.execute(new NonBlockedSocketChannel());
		}
		TimeUnit.SECONDS.sleep(1000);
		es.shutdownNow();
	}

}
