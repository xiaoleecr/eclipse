package oo.day03;
//格子类
public class Cell {
	int row;  //行号
	int col;  //列号
	
	Cell(int row,int col){
		this.row = row;
		this.col = col;
	}
	String getCellInfo(){ //获取格子行号和列号
		return row+","+col;
	}
	
	/*Cell(){
		this(0,0); //调2个参数构造
	}
	
	void drop(){ //下落1格
		row++;
	}
	void moveLeft(int n){ //左移n格
		col -= n;
	}
	void moveRight(){ //右移1格
		col++;
	}*/
	

	/*void drop(int n){ //下落n格
		row += n;
	}
	void moveLeft(){ //左移1格
		col--;
	}*/
}





