package org.hduo.clone;

import bsh.This;

class Stu implements Cloneable{
	private String stuName;

	public Stu(String stuName) {
		super();
		this.stuName = stuName;
		
	}
	
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
class Sheep implements Cloneable{
	private String name;
	private Stu stu ;
	public Sheep(String name) {
		super();
		this.name = name;
		stu = new Stu(name);
	}
	public Object clone(){
		try {
			Sheep sheep =(Sheep) super.clone();
			sheep.setStu((Stu)stu.clone());
			return sheep;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getName() {
		return name;
	}
	public Stu getStu() {
		return stu;
	}
	public void setStu(Stu stu) {
		this.stu = stu;
	}
	
	
}

public class CloneDemo1 {

	public static void main(String[] args) {
		Sheep sheep = new Sheep("one");
		Sheep sheep2 =(Sheep) sheep.clone();
		
		System.out.println(sheep == sheep2);
	    System.out.println(sheep2.getName() == sheep.getName());
		System.out.println(sheep.getStu() == sheep2.getStu());
	}

}
