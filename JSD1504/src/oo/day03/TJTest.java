package oo.day03;

//T�ࡢJ�������
public class TJTest {
	public static void main(String[] args) {
		Tetromino t = new T(2, 5);// ����T�Ͷ���
		printWall(t);// T tt=t
		Tetromino j=new J(1,2);
		printWall(j);
	}

	public static void printWall(Tetromino tt) {
		Cell[] cells=tt.cells;//ȡ��tt�͵ĸ������鲢��ֵ��cells����cells�Ĳ������Ƕ�tt�͸�������Ĳ���
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
