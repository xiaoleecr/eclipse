package oo.day04;

public class FinalDemo {

	public static void main(String[] args) {
		
	}

}
class Roo{}
final class Poo extends Roo{}
//class Qoo extends Poo{}//编译错误final修饰的类不能被继承,但可以继承其他类










class Noo{
	void show(){}
	final void say(){}
}
class Ooo extends Noo{
	void show(){}
	//void say(){}//编译错误，final方法不可被重写
}











class Moo{
	final int num =5;
	final int num2;
	Moo(){
		num2=6;
	}
	void show(){
		final int a;
		//num=7;//编译错误，final修饰的变量不可改值
		}
}