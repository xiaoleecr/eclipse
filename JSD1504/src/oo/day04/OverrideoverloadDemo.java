package oo.day04;

public class OverrideoverloadDemo {
	public static void main(String[] args) {
	//重写方法被调用时看对象
	//重载方法被调用时看参数类型绑定方法
		
		Eoo eoo=new Eoo();
		Coo o=new Doo();
		eoo.test(o);
	}

}
class Coo{
	void show(){
		System.out.println("父类show");
	}
}
class Doo extends Coo{
	void show(){System.out.println("子类show");}
}
class Eoo{
void test(Coo o){System.out.println("父类型参数");
o.show();}
void test(Doo o){System.out.println("子类型参数");
o.show();}}
