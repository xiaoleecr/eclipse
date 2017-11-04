package oo.day02;

public class Cell {
	int col;// 行号
	int row;// 列号

	void moveUp() {
		col--;
	}

	void drop() {
		col++;
	}

	void moveLeft() {
		row--;
	}

	void moveRight() {
		row++;
	}
	void drop(int n){
		col+=n;
	}
	Cell(int row,int col){
		this.row=row;
		this.col=col;
	}
	Cell(int n){
		this(n,n);//调两个参数构造
	}
	Cell(){
		this(0,0);//调两个参数构造
	}
}
