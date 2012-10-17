package thinking.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelCopy {

	public static void main(String[] args) throws Exception {
		FileChannel in = new FileInputStream("pom.xml").getChannel();
		FileChannel out = new FileOutputStream("b.txt").getChannel();

		ByteBuffer buff = ByteBuffer.allocate(1024);

		while (in.read(buff) != -1) {
			buff.flip();
			out.write(buff);
			buff.clear();
		}
		
		in.close();
		out.close();
	}

}
