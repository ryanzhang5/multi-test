package logging.java.util.logging;

import java.util.Properties;

public class PropertyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {

		//InputStream is = PropertyTest.class
		//		.getResourceAsStream("logging.property");
		//System.getProperties().load(is);

		//System.setProperty("java.util.logging.config.file", "logging.property");
		
		Properties p = new Properties();
		
		
		
	  p.load(PropertyTest.class.getResourceAsStream("logging.property"));
		System.out.println(p);
		System.out.println("-------------------------------");
		System.out.println(System.getProperties());
	}
}
