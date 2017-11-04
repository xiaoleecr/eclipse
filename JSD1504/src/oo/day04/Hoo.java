package oo.day04;

class Foo {
	public int a;
	protected int b;
	int c;
	private int d;
	void show() {
		a = 1;
		b = 2;
		c = 3;
		d = 4;
	}
}
class Goo{
	Foo o = new Foo();
	void aaa() {
		o.show();
		System.out.println(o.a + "" + o.b + "" + o.c);
	}
	// void shows(){
	// Foo o= new Foo();
	// o.a=1;
	// o.b=1;
	// o.c=1;
	// o.d=1;
	// }
}
public class Hoo {
	public static void main(String[] args) {
		Goo g = new Goo();
		g.aaa();
	}
}