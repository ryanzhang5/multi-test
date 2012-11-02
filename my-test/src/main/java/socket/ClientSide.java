package socket;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ClientSide {

	public static void main(String[] args) {
		Socket socket = null;
		try {
			// socket = new Socket(InetAddress.getByName("localhost"), 8082);
			socket = new Socket();
			socket.setReuseAddress(true);
			socket.bind(new InetSocketAddress("localhost", 12453));
			socket.connect(new InetSocketAddress("localhost", 8082), 15000);
			System.out.println("in client " + socket);
			OutputStream os = socket.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(os);
			PrintWriter pw = new PrintWriter(bos);
			pw.write("aaa");
			pw.flush();
			socket.shutdownOutput();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
