package pl.org.app;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PrivateObjectTest {
	PrivateObject obj;

	@BeforeClass
	public void testSetUp() {
		obj = new PrivateObject();
		System.out.println("set up private object");
	}

	@Test
	public void testPrivateField() throws Exception {
		Field field = PrivateObject.class.getDeclaredField("name");
		field.setAccessible(true);
		System.out.println(field.get(obj));
	}

	@Test
	public void testPrivateMethod() throws Exception {
		Method method = PrivateObject.class.getDeclaredMethod("printName",
				new Class[] {});
		method.setAccessible(true);
		method.invoke(obj, new Object[] {});
	}

}
