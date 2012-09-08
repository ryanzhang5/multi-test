package thinking.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class MyServer implements Runnable {

	public void run() {
		try {
			ServerSocket ss = new ServerSocket(8082, 3);
			Socket socket = ss.accept();
			InputStream in = new BufferedInputStream(socket.getInputStream());
			OutputStream out = new BufferedOutputStream(
					socket.getOutputStream());
			System.out.println("in server " + socket);
			int ch = 0;
			while (true) {
				while ((ch = in.read()) != -1) {
					System.out.print((char) ch);
				}
				
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}

class MyClient implements Runnable {
	private String name;
	private Socket socket;
	private String clientSaid;

	public MyClient(String name, Socket socket, String clientSaid) {
		this.name = name;
		this.socket = socket;
		this.clientSaid = clientSaid;

	}

	public void run() {
		try {

			InputStream in = new BufferedInputStream(socket.getInputStream());
			OutputStream out = new BufferedOutputStream(
					socket.getOutputStream());

			byte[] bytes = clientSaid.getBytes();

			out.write(bytes);
			out.flush();
			System.out.println("now " + this.name + " will close socket " );
			socket.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}

public class SocketCloseVSShutdown {

	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();

		es.execute(new MyServer());
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("-------------------");

		Socket socket = null;
		try {
			socket = new Socket(InetAddress.getByName("localhost"), 8082);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		MyClient myClient1 =  new MyClient("client-1", socket, "hello, this is client1");
		MyClient myClient2 = new MyClient("client-1", socket, "good, i am client2");
		es.execute(myClient1);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		es.execute(myClient2);

	}

}
