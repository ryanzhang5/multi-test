package encoding;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CharEncodingTest {
	public static void main(String[] args) {
		try {
			BufferedReader br = null;
			br = new BufferedReader(new InputStreamReader(new FileInputStream("src\\main\\resources\\utf.txt"), "utf-8"));

			//br = new BufferedReader(new InputStreamReader(new FileInputStream("src\\main\\resources\\ansi.txt"), "gb2312"));

			System.out.println(br.readLine());

			char[] chars = new char[16];
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
