package thinking.nio;

import java.nio.ByteBuffer;

public class GetData {
	private static final int BSIZE = 1024;

	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(BSIZE);
		// Allocation automatically zeroes the ByteBuffer:
		int i = 0;
		while (i++ < bb.limit())
			if (bb.get() != 0)
				System.out.print("nonzero");
		System.out.print("i = " + i);
		bb.clear();
		// Store and read a char array:
		bb.asCharBuffer().put("Howdy!");
		char c;
		while ((c = bb.getChar()) != 0)
			System.out.print(c + " ");
		System.out.println();
		bb.clear();
		// Store and read a short:
		bb.asShortBuffer().put((short) 471142);
		System.out.println(bb.getShort());
		bb.clear();
		// Store and read an int:
		bb.asIntBuffer().put(99471142);
		System.out.println(bb.getInt());
		//bb.clear();
		//bb.flip();
		bb.clear();
		// Store and read a long:
		bb.asLongBuffer().put(99471142);
		System.out.println(bb.getLong());
		bb.clear();
		// Store and read a float:
		bb.asFloatBuffer().put(99471142);
		System.out.println(bb.getFloat());
		bb.clear();
		// Store and read a double:
		bb.asDoubleBuffer().put(99471142);
		System.out.print(bb.getDouble());
		bb.clear();
	}

}
