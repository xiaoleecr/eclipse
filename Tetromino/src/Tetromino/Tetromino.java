package Tetromino;

public class Tetromino {
	Cell[] cells;
	Tetromino(){
		cells=new Cell[4];
	}
	public void drop(){
		for(int i=0;i<cells.length;i++){
			cells[i].row++;
		}
	}
	public void moveLeft(){
		for(int i=0;i<cells.length;i++){
			cells[i].col--;
		}
	}
	public void moveRight(){
		for(int i=0;i<cells.length;i++){
			cells[i].col++;
		}
	}
}
