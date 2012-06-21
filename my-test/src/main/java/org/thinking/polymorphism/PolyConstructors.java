package org.thinking.polymorphism;

class Glyph {
	void draw() {
		System.out.println("Glyph.draw()");
	}

	Glyph() {
		System.out.println("Glyph() before draw()");
		this.draw();
		System.out.println("Glyph() after draw()");
		System.out.println(this);
	}
	
	
	void print(){
		this.print2();
		System.out.println("Glyph().print");
	}
	void print2(){
		System.out.println("Glyph().print2");
	}
}

class RoundGlyph extends Glyph {
	private int radius = 1;

	RoundGlyph(int r) {
		radius = r;
		System.out.println("RoundGlyph.RoundGlyph(), radius = " + radius);
	}

	void draw() {
		System.out.println("RoundGlyph.draw(), radius = " + radius);
	}
	
	void print(){
		super.print();
		System.out.println("RoundGlyph().print");
	}
	void print2(){
		
		System.out.println("RoundGlyph().print2");
	}
	
}

public class PolyConstructors {
	public static void main(String[] args) {
		Glyph g = new RoundGlyph(5);
		System.out.println("***********************");
		g.print();
	}
}
