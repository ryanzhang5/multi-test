package pl.org.app;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;;


public class AppTest {

	App app;

	@BeforeClass
	public void testSetUp() {
		app = new App();
		System.out.println("testSetUp");
	}

	@Parameters({ "value" })
	@BeforeMethod
	public void setUp(double value) {
		app.setSomeNumber(value);
		System.out.println("setUp");
	}

	@Parameters({ "value" })
	@Test
	public void testSomeNumber1(double value) {
		System.out.println("testSomeNumber1");
		assertTrue(app.getSomeNumber() == value);
	}

	@Test(groups = { "brokenTests" })
	public void testSomeNumber2() {
		System.out.println("testSomeNumber2");
		assertFalse(app.getSomeNumber() == 1.0);
	}

	@Test
	public void testSomeNumber3() {
		System.out.println("testSomeNumber3");
		assertTrue(app.getSomeNumber() == 1.0);
	}

	@Test
	public void testSomeNumber4() {
		System.out.println("testSomeNumber4");
		assertTrue(app.divide(0.0)==1.0);
	}

}