package thinking.nio;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ServerSide implements Runnable {

	public void run() {
		try {
			ByteBuffer buffer = ByteBuffer.allocate(10);
			ServerSocketChannel ssChannel = ServerSocketChannel.open();
			ssChannel.socket().bind(new InetSocketAddress("localhost", 9905),
					10);
			Selector selector = Selector.open();

			SocketChannel socketChannel = ssChannel.accept();
			socketChannel.configureBlocking(false);

			socketChannel.register(selector, SelectionKey.OP_READ);
			int index = 0;
			while (true) {

				selector.select();
				Iterator<SelectionKey> iterator = selector.selectedKeys()
						.iterator();
				while (iterator.hasNext()) {
					SelectionKey selectionKey = (SelectionKey) iterator.next();
					SocketChannel sChannel = (SocketChannel) selectionKey
							.channel();
					buffer.clear();
					sChannel.read(buffer);
					buffer.flip();
					byte[] bytes = new byte[10];
					buffer.get(bytes,0,buffer.limit());
					
					System.out.println((index++) + "----------"+ new String(bytes));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

class ClientSide implements Runnable {
	public void run() {
		try {
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress("localhost", 9905));
			OutputStream outputStream = socket.getOutputStream();
			outputStream.write(("ABCDEFGHIJKLMNOPQRSTUVWXYZ").getBytes());
			outputStream.flush();

		} catch (Exception e) {
		}
	}
}

public class SocketChannelByteBuffer {
	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(new ServerSide());
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		es.execute(new ClientSide());
	}
}
