package oo.day03;
//������
public class Cell {
	int row;  //�к�
	int col;  //�к�
	
	Cell(int row,int col){
		this.row = row;
		this.col = col;
	}
	String getCellInfo(){ //��ȡ�����кź��к�
		return row+","+col;
	}
	
	/*Cell(){
		this(0,0); //��2����������
	}
	
	void drop(){ //����1��
		row++;
	}
	void moveLeft(int n){ //����n��
		col -= n;
	}
	void moveRight(){ //����1��
		col++;
	}*/
	

	/*void drop(int n){ //����n��
		row += n;
	}
	void moveLeft(){ //����1��
		col--;
	}*/
}





