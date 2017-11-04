package oo.day04;

public class StaticDemo {
	public static void main(String[] args) {
		/*Joo o1=new Joo();
		o1.show();
		Joo o2=new Joo();
		o2.show();
	}*/
//	Koo.say();
		Koo.say();
	Koo.testStatic();
	}
}
////
class Koo{
	int a;
	static int b;
	void show(){System.out.println("show me the money");}
	static void say(){System.out.println("hello");}
	void test(){}
		static void testStatic(){
			Koo k=new Koo();
			b=2;
			say();
			System.out.println("static");
		    k.show();
	}
//		a=1;b=2;
//		show();
//		say();
}





class Joo{
	//两个成员变量
	int a ;//实例变量
	static int b;//静态变量
	Joo(){
		a++;
		b++;
	}
	void show(){
		System.out.println("a="+a);
		System.out.println("b="+b);
	}
}