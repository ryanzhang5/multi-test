package core.chapter03;


class Shape {
	public void draw() {
	}

	public void erase() {
	}
}

class Circle extends Shape {
	public void draw() {
		System.out.println("Circle.draw()");
	}

	public void erase() {
		System.out.println("Circle.erase()");
	}
}

class Square extends Shape {
	public void draw() {
		System.out.println("Square.draw()");
	}

	public void erase() {
		System.out.println("Square.erase()");
	}
}

class Triangle extends Shape {
	public void draw() {
		System.out.println("Triangle.draw()");
	}

	public void erase() {
		System.out.println("Triangle.erase()");
	}
}

public class Shapes {
	public static void main(String[] args) {
		Shape shape1 = new Circle();
		shape1.draw();
		Shape shape2 = new Square();
		shape2.draw();
		Shape shape3 = new Triangle();
		shape3.draw();
	}
}