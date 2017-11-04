package oo.day05;

public class InterfaceDemo {
	public static void main(String[] args) {
		Inter4 o1=new Doo();
		Inter2 o2=new Doo();
		Inter1 o3=new Doo();
	}

}
interface Inter1{
public static final int NUM=5;
void a();//若在类中，必须加abstract修饰，接口中则默认有public abstract
}
//接口的实现
interface Inter2{
	void a();
	void b();
}
class Aoo implements Inter2{
	public void a(){};
	public void b(){}
}
interface Inter3{
	void c();
}
abstract class Boo{
	abstract void d();
}
abstract class Coo extends Boo implements Inter2,Inter3{
	
}
interface Inter4 extends Inter2{
void e();
	
}
class Doo implements Inter4{
	public void a(){};
	public void b(){};
	public void e(){};
}