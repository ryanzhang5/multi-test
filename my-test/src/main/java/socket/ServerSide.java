package socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ServerSide {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8082, 3);
			Socket socket = ss.accept();
			socket.setSoTimeout(2 * 1000);
			InputStream in = new BufferedInputStream(socket.getInputStream());
			OutputStream out = new BufferedOutputStream(
					socket.getOutputStream());
			System.out.println("in server " + socket);
			int ch = 0;
			while (true) {
				while (true) {
					ch = in.read();
					System.out.println("-+++++++++++++ch is " + ch);
					if (ch == -1) {
						break;
					}
					System.out.println((char) ch);
				}
				System.out.println("before shutdonw input");
				socket.shutdownInput();
				System.out.println("after shutdonw input");
				System.out.println("&&&&&&&&&&&" + in.read());
				System.out.println("&&&&&&&&&&&" +  in.read());
				System.out.println("&&&&&&&&&&&" + in.read());
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
