package oo.day02;

public class CellTest {
	public static void main(String[] args) {
		/*
		 * // 类 引用 对象 Cell c = new Cell();//创建格子对象 c.row=2;//访问成员变量 c.col=5;
		 * c.drop(); c.moveLeft(3);{ String str=c.getCellInfo();
		 * System.out.println(str+","+c.hehe(false));//3,2 }
		 */
		Cell c = new Cell();
		c.row = 2;
		c.col = 5;
		System.out.println("下落一格");
		c.drop();
		printWall(c);
		System.out.println("左移3格");
		//c.moveLeft(3);
		printWall(c);
		
	}
public static void printWall(Cell cc){//括号里数据类型为Cell
	for (int i = 0; i < 20; i++) {
		for (int j = 0; j < 10; j++) {
			if (i == cc.row && j == cc.col) {
				System.out.print("* ");
			} else {
				System.out.print("- ");
			}
		}
		System.out.println();
	}
}
}
