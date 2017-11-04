package oo.day06;

public class MuiltType {
	public static void main(String[] args) {
		Aoo o1 = new Boo();// 向上造型
		Boo o2 = (Boo) o1;// 大类型转为小类型必须强制转
		Inter1 o3 = (Inter1) o1;// 强制类型转换;
		if (o1 instanceof Coo) {

			Coo o4 = (Coo) o1;// 语法虽然无错，Coo和Boo无关联
		}
	}

}

interface Inter1 {
}

class Aoo {
}

class Boo extends Aoo implements Inter1 {
}

class Coo extends Aoo {
}