package thinking.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {
	private static final int BSIZE = 1024;

	public static void main(String[] args) throws Exception {

		FileChannel fc = new FileOutputStream("data.txt").getChannel();
		
		ByteBuffer buffer = ByteBuffer.wrap("some text111".getBytes());
		
		System.out.println(buffer.limit());
		
		fc.write(buffer);
		System.out.println(buffer.limit());
		fc.write(ByteBuffer.wrap("some text222".getBytes()));

		fc.close();

		fc = new RandomAccessFile("data.txt", "rw").getChannel();

		fc.position(fc.size());

		fc.write(ByteBuffer.wrap("some text333".getBytes()));
		fc.write(ByteBuffer.wrap("some text555".getBytes()));

		fc.close();

		fc = new FileInputStream("data.txt").getChannel();
		
		ByteBuffer buff = ByteBuffer.allocate(BSIZE);
		fc.read(buff);
		buff.flip();
		while(buff.hasRemaining()){
			System.out.print((char)buff.get());
		}
		fc.close();
		System.out.println("this is go");
	}

}
