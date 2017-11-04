package oo.day04;

//T类、J类测试类
public class TJTest {
	public static void main(String[] args) {
	 Tetromino t=new T();
	 t.print();
	 Tetromino j=new J();
	 j.print();
		TJTest.printWall(j);
	}

	public static void printWall(Tetromino oo) {
		Cell[] cells=oo.cells;//取出tt型的格子数组并赋值给cells，对cells的操作就是对tt型格子数组的操作
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
		}
	}
}
