package pl.org.app;

public class App {
	private double someNumber = 1.0;

	public double divide(double a) {
		double result = someNumber / a;
		someNumber = 0.0;
		return result;
	}

	
	
	public double getSomeNumber() {
		double result = someNumber;
		someNumber = 0.0;
		return result;
	}

	public void setSomeNumber(double someNumber) {
		this.someNumber = someNumber;
	}
}