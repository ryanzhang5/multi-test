package thinking.io;

import java.io.File;
import java.io.FileOutputStream;

public class FileTest {

	
	public static void main(String[] args)throws Exception {
		String s = "/home/ryan/35/36/37/2222";
		
		File file = new File(s);
		//System.out.println(file.getParentFile().mkdirs());
	 
		FileOutputStream fo = new FileOutputStream(s);
		fo.write(2);
		fo.write(50);
		fo.write(60);
		fo.write(65);
		fo.close();
		
	}

}
