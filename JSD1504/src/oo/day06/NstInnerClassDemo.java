package oo.day06;
//匿名内部类
public class NstInnerClassDemo {
	public static void main(String[] args) {
		//Inter2 o=new Inter2();接口不能被实例化
		
		//1.创建了Inter2的一个子类，没有名字
		//2.为该子类创建了一个名为o1的对象
		//3.在大括号中即是那个子类的类体
		Inter2 o1=new Inter2(){
		};
		Inter2 o2=new Inter2(){
			
		};
		Inter3 o3=new Inter3(){
			public void show(){
				System.out.println("show");
			}
		};
		o3.show();
		}
	}
class Moo implements Inter2{}

interface Inter3{
	void show();
}

interface Inter2{}
