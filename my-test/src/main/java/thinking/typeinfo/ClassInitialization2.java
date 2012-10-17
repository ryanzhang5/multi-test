package thinking.typeinfo;

import java.util.Random;


class Initial{
	
	public static final int t = 10;
	public static final int t2 = ( new Random(47)).nextInt(100);
 	static {
		System.out.println("this is go");
	}
}


class A{}

class B extends A{}



class C extends B{}







public class ClassInitialization2 {

	public static void main(String[] args) throws Exception{
		System.out.println("--------------------"+A.class.isInstance(new B()));
		
		 C c = new C();
		
		System.out.println(c instanceof A);
		System.out.println(c instanceof B);
		
		
		
		Class<Initial> it = Initial.class;
		System.out.println(Initial.t);
		System.out.println(Initial.t2);
		System.out.println("----------------------------");
		System.out.println(Initial.t);
		Class.forName("thinking.typeinfo.Initial");
		System.out.println(Initial.t);
	}

}
