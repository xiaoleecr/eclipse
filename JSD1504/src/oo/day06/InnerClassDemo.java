package oo.day06;

public class InnerClassDemo {
	public static void main(String[] args) {
		Mama m = new Mama();
		// Baby b=new Baby();内部类不可被外部访问
	}

}

class Mama {// 外部类
	private String name;
	Baby createBaby() {
		return new Baby();
	}
	class Baby {// 内部类
		void getMamaName() {
			System.out.println(name);//Mama.this为隐式的指向外部类的引用
			System.out.println(Mama.this.name);//完整写法
			//System.out.println(this.name);编译错误
			
		}

	}
}
