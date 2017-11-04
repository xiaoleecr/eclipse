package day02;

public class NumberChange {
	public static void main(String[] args) {
		// 有两个整数
		int a = 10;
		int b = 15;
		a = a + b;
		b = a - b;
		a = a - b;
		System.out.println("a的值为" + a);
		System.out.println("b的值为" + b);
	}
}
