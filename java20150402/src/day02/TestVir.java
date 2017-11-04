package day02;

//测试Java变量

public class TestVir {
	public static void main(String[] args) {
		// 定义一个表示年龄的整数
		byte age;// 声明一个表示年龄的整数
		age = 127;// 给变量赋值（初始化）注意在其范围内赋值
		System.out.println("年龄" + age);
		System.out.println(age + 1);
		// 直接写出的整数默认为int型，两数相加先看类型，以范围大的为输出类型
		short money = 32767;// 声明一个变量并赋值
		System.out.println(money + 1);
		int i = Integer.MAX_VALUE;// int类型最大值
		System.out.println(i);
		System.out.println(i + 1);// 输出-2147483648
		System.out.println(i + 2);// 输出-2147483647，超出范围则循环回最小值起计数
		// 算术运算尽量避免在边界值运算，如果有超过范围的情况则选择更大的类型
		long l=Long.MAX_VALUE;
		System.out.println(l);
		double d=Double.MAX_VALUE;
		System.out.println(d);
		d=3.1;//直接整数赋值默认为int型，注意不要超过int型范围
		System.out.println(d);
		System.out.println(d-1.9);
		//double,float是不精确的
		//算术运算有舎入误差		
		float f=1.0f;
		//float f=1.0是错误写法;所有直接小数赋值默认为double类型
		System.out.println(f);
		boolean isGirl=true;
		System.out.println(isGirl);
		boolean isTrue=1>=1;//用关系表达式判断
		System.out.println(isTrue); 
		char c=20013;
		System.out.println(c);//'中'
		c=50000;
		System.out.println(c);//?韩国字
		System.out.println('我'+'你');
		c='a';
		System.out.println((int)c+"start");//29233
		int a='v';
		System.out.println((int)a);
	
	}
}
