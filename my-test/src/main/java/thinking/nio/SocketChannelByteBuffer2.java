package thinking.nio;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ServerSide2 implements Runnable {

	public void run() {
		try {
			ByteBuffer buffer = ByteBuffer.allocate(20);
			ServerSocketChannel ssChannel = ServerSocketChannel.open();
			ssChannel.socket().setReuseAddress(true);
			ssChannel.socket().bind(new InetSocketAddress("localhost", 9909),
					10);
			Selector selector = Selector.open();

			SocketChannel socketChannel = ssChannel.accept();
			socketChannel.configureBlocking(false);
			System.out.println("1111111111111111111111" + socketChannel.socket().getLocalPort());
			SocketChannel socketChannel2 = ssChannel.accept();
			socketChannel2.configureBlocking(false);
			System.out.println("222222222222222222222222"+ socketChannel.socket().getLocalPort());
			socketChannel.register(selector, SelectionKey.OP_READ);
			socketChannel.register(selector, SelectionKey.OP_WRITE);
			socketChannel2.register(selector, SelectionKey.OP_READ);
			
			
			int index = 0;
			while (true) {
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (Exception e) {
					// TODO: handle exception
				}
				selector.select();
				Iterator<SelectionKey> iterator = selector.selectedKeys()
						.iterator();
				while (iterator.hasNext()) {
					SelectionKey selectionKey = (SelectionKey) iterator.next();
					SocketChannel sChannel = (SocketChannel) selectionKey
							.channel();
					
					System.out.println("server side -----------"+selectionKey.isAcceptable() + " "
							+ selectionKey.isConnectable() + " " + selectionKey.isReadable()
							+ " " + selectionKey.isWritable());
				/* 	
					buffer.clear();
					sChannel.read(buffer);
					buffer.flip();
					byte[] bytes = new byte[20];
					buffer.get(bytes, 0, buffer.limit());
					*/
					// System.out.println((index++) + "----------" + new
					// String(bytes));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

/*
 * class ClientSide2 implements Runnable { public void run() { try {
 * 
 * SocketChannel channel = null;
 * 
 * channel = SocketChannel.open(); channel.configureBlocking(false);
 * channel.connect(new InetSocketAddress("localhost", 9905));
 * 
 * //channel.socket().setSendBufferSize(1055);
 * channel.setOption(StandardSocketOptions.SO_SNDBUF, 1025); StringBuilder
 * sBuilder = new StringBuilder(); for(int i = 0;i<32769;i++){
 * sBuilder.append("A"); }
 * 
 * ByteBuffer bb = ByteBuffer.wrap(sBuilder.toString().getBytes()); bb.rewind();
 * //bb.rewind(); Selector selector = Selector.open();
 * channel.register(selector, SelectionKey.OP_CONNECT); int index = 0;
 * while(true){ selector.select(); Iterator iterator =
 * selector.selectedKeys().iterator(); while (iterator.hasNext()) { SelectionKey
 * key = (SelectionKey) iterator.next(); iterator.remove();
 * if(key.isConnectable()){ channel.finishConnect(); channel.register(selector,
 * SelectionKey.OP_WRITE); } if(key.isWritable()){
 * System.out.println(channel.socket().getSendBufferSize()); index++;
 * TimeUnit.SECONDS.sleep(1); System.out.println(index +
 * "time write before-----------------"+bb.position()); int num =
 * channel.write(bb); System.out.println(index +
 * "time write after-----------------"+bb.position() +
 * "-------------remaining is----" + bb.remaining()); } } } } catch (Exception
 * e) { } } }
 */

class ClientSide2 implements Runnable {
	public void run() {
		try {

			SocketChannel channel = null;

			channel = SocketChannel.open();
			channel.configureBlocking(false);
			channel.connect(new InetSocketAddress("localhost", 9909));
			
			// channel.socket().setSendBufferSize(1055);
			channel.setOption(StandardSocketOptions.SO_SNDBUF, 1025);
			
			
			
			
			SocketChannel channel2 = null;

			channel2 = SocketChannel.open();
			channel2.configureBlocking(false);
			channel2.connect(new InetSocketAddress("localhost", 9909));
			
			// channel.socket().setSendBufferSize(1055);
			channel2.setOption(StandardSocketOptions.SO_SNDBUF, 1025);
			
			
			System.out.println("====================================== " +channel2.validOps());
			
			
			SocketChannel channel3 = null;

			channel3 = SocketChannel.open();
			channel3.configureBlocking(false);
			channel3.connect(new InetSocketAddress("localhost", 9909));

			// channel.socket().setSendBufferSize(1055);
			channel3.setOption(StandardSocketOptions.SO_SNDBUF, 1025);
			
			
			
			
			
			StringBuilder sBuilder = new StringBuilder();
			for (int i = 0; i < 32769; i++) {
				sBuilder.append("A");
			}

			ByteBuffer bb = ByteBuffer.wrap(sBuilder.toString().getBytes());
			bb.rewind();
			// bb.rewind();
			Selector selector = Selector.open();
			// channel.register(selector, SelectionKey.OP_WRITE);
			
			channel.register(selector, SelectionKey.OP_CONNECT);
			channel2.register(selector, SelectionKey.OP_WRITE);
			channel3.register(selector, SelectionKey.OP_CONNECT);
			int index = 0;
			while (true) {
				System.out.println("beflore ");
				selector.select();
				System.out.println("after ");
				TimeUnit.SECONDS.sleep(5);
				Iterator iterator = selector.selectedKeys().iterator();
				System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT  " + selector.selectedKeys().size());
				while (iterator.hasNext()) {
					System.out
							.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
					SelectionKey key = (SelectionKey) iterator.next();
					System.out.println(key.isAcceptable() + " "
							+ key.isConnectable() + " " + key.isReadable()
							+ " " + key.isWritable());
					iterator.remove();
					if (key.isConnectable()) {
						System.out.println("since connectable ,do nothing");
						/*SocketChannel channel4 =(SocketChannel) key.channel();
						channel4.finishConnect();
						channel4.register(selector, SelectionKey.OP_WRITE);
						System.out.println("^^^^^^^^^^^^^^^^^^^^^ " + channel4.socket().getLocalPort());
						*/
						SocketChannel channel4 =(SocketChannel) key.channel();
						//channel4.finishConnect();
						channel4.register(selector, SelectionKey.OP_WRITE);
					}
					
					if (key.isWritable()) {
						/*System.out
								.println(channel.socket().getSendBufferSize());
						index++;
						TimeUnit.SECONDS.sleep(5);
						System.out.println(index
								+ "time write before-----------------"
								+ bb.position());
						int num = channel.write(bb);
						System.out.println(index
								+ "time write after-----------------"
								+ bb.position()
								+ "-------------remaining is----"
								+ bb.remaining());*/
						System.out.println("now writable");
					//	key.channel().close();
						key.cancel();
					}
				}
			}
		} catch (Exception e) {
		}
	}
}

public class SocketChannelByteBuffer2 {
	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(new ServerSide2());
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		es.execute(new ClientSide2());

	}
}
