package oo.day01;
//格子类
public class Cell {
	int row;//行号
	int col;//列号
	
	public Cell(){
		
	}
	
	public Cell(Cell obj1,Cell obj2){
		this.row=obj1.row;
		this.col=obj2.col;
	}
	void drop(){
		row++;//下落一格
		
	}
	void moveLeft(int n){
		col-=n;//左移n格
	}
	void moveRight(int n){
		col+=n;
	}
	void moveUp(){
		row--;
	}
	String getCellInfo(){
		return row+","+col;
	}
}
