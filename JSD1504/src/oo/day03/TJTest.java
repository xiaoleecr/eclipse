package oo.day03;

//T类、J类测试类
public class TJTest {
	public static void main(String[] args) {
		Tetromino t = new T(2, 5);// 创建T型对象
		printWall(t);// T tt=t
		Tetromino j=new J(1,2);
		printWall(j);
	}

	public static void printWall(Tetromino tt) {
		Cell[] cells=tt.cells;//取出tt型的格子数组并赋值给cells，对cells的操作就是对tt型格子数组的操作
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
				boolean flag = true;
				for (int k = 0; k < cells.length; k++) {
					if (i == cells[k].row && j == cells[k].col) {
						flag = false;
						break;
					}
				}
				if (flag == true) {
					System.out.print("- ");
				} else {
					System.out.print("* ");
				}

			}
			System.out.println();
		}System.out.println();
	}
}
