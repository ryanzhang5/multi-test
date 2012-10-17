package thinking.io;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class FormattedMemoryInput {
	public static void main(String[] args) throws IOException {
		try {
			DataInputStream in = new DataInputStream(new FileInputStream("pom.xml"));
			while (true)
				System.out.print((char) in.readByte());
		} catch (EOFException e) {
			System.err.println("End of stream");
		}
	}
}

