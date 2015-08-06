package core.chapter02;

class Person {
	int age;
	public void method1() {
		Student student = new Student();
		student.method2(this);
	}
	
	public void setAge(int age){
		this.age = age;
	}
	
	public int getAge(){
		return age;
	}

}

class Student {
	public void method2(Person person) {
		System.out.println("this is person " + person.getAge());
	}
}

public class This2 {
	public static void main(String[] args) {
		Person person = new Person();
		person.setAge(10);
		person.method1();
	}
}