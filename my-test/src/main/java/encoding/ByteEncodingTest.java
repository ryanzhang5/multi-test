package encoding;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

public class ByteEncodingTest {
	public static void main(String[] args) {
	 try {
		//InputStream is = new FileInputStream(new File("src\\main\\resources\\ansi.txt"));
		InputStream is = new FileInputStream(new File("src\\main\\resources\\utf.txt"));
		byte[] bytes = new byte[20];
		int len = is.read(bytes);
		System.out.println(len);
		for (int i = 0; i < bytes.length; i++) {
			System.out.println("i="+i +"  "+Long.toHexString(bytes[i]));
		}
		//String s =  new String(bytes, "gb2312");
		String s =  new String(bytes, "utf-8");
		System.out.println(Charset.defaultCharset());
		System.out.println(s);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
}
