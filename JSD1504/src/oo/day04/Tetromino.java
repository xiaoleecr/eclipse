package oo.day04;

public class Tetromino {
	Cell[] cells;
	Tetromino(){
		cells = new Cell[4];
		}
	//Cell's action
	void drop(){
		for(int i=0;i<cells.length;i++){
			cells[i].row++;
		}
	}
	void moveLeft(){
		for(int i=0;i<cells.length;i++){
			cells[i].col--;
		}
	}
	void moveRight(){
		for(int i=0;i<cells.length;i++){
			cells[i].col++;
		}
	}
	void print(){
		for(int i=0;i<this.cells.length;i++){
			String str = this.cells[i].getCellInfo();
			System.out.println(str);
		}
}
}