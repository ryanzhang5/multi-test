package thinking.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class SocketBackLogAndTimeOut {
	
	public void buildServerSocket(){
		try {
			ServerSocket ss = new ServerSocket(8088, 3,
					InetAddress.getByName("localhost"));

			while (true) {
				Socket client = ss.accept();

				TimeUnit.SECONDS.sleep(1);
				System.out.println(client.getLocalPort());
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void buildClient(){
		long start = 0L;
		try {
			for (int i = 0; i < 10; i++) {
				 start = System.currentTimeMillis();
				Socket socket = new Socket();
				SocketAddress address = new InetSocketAddress("localhost", 8088);
				System.out.println("this time i is "+ i);
				socket.connect(address, 3000);
			}
		} catch (IOException e) {
			System.out.println(System.currentTimeMillis() - start);
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
	}
}
