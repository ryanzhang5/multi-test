package thinking.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferToText {
	private static final int BSIZE = 1024;

	public static void main(String[] args) throws Exception {
	/*	FileChannel fc = new FileOutputStream("data2.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text".getBytes()));
		fc.close();
		fc = new FileInputStream("data2.txt").getChannel();
		ByteBuffer buff = ByteBuffer.allocate(BSIZE);
		fc.read(buff);
		buff.flip();
		// Doesn’t work:
		System.out.println(buff.asCharBuffer());
		// Decode using this system’s default Charset:
		buff.rewind();
		String encoding = System.getProperty("file.encoding");
		System.out.println("Decoded using " + encoding + ": "
				+ Charset.forName(encoding).decode(buff));
		
		
		
		// Or, we could encode with something that will print:
		fc = new FileOutputStream("data2.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
		fc.close();
		// Now try reading again:
		fc = new FileInputStream("data2.txt").getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());
		*/
		FileChannel fc =null;
		ByteBuffer buff = null;
		
		
		// Use a CharBuffer to write through:
		fc = new FileOutputStream("data3.txt").getChannel();
		buff = ByteBuffer.allocate(9); // More than needed
		//buff.asCharBuffer().put("Some text");
		buff.put("some text".getBytes());
		
		//buff = ByteBuffer.wrap("tttttttttt".getBytes());
		
		fc.write(buff);
		fc.close();
		// Read and display:
		fc = new FileInputStream("data3.txt").getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.print("777777");
		while(buff.hasRemaining()){
			System.out.print((char)buff.get());
		}
		System.out.print("888".getBytes().length);
		
	}
}
