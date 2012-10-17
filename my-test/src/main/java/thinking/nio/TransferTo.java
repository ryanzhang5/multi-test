package thinking.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class TransferTo {

	public static void main(String[] args)throws Exception {
		FileChannel in = new FileInputStream("pom.xml").getChannel();
		FileChannel out = new FileOutputStream("c.txt").getChannel();
		FileChannel out2 = new FileOutputStream("c2.txt").getChannel();
		
		
		in.transferTo(0, in.size(), out);
		
		out2.transferFrom(in, 0, in.size());
		
	}

}
