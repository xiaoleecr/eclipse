package Tetromino;

public class S extends Tetromino{
	public S(){
		this(0,0);
	}
	public S(int row,int col){
		cells[0]=new Cell(row,col);
		cells[1]=new Cell(row+1,col);
		cells[2]=new Cell(row+1,col+1);
		cells[3]=new Cell(row+2,col+1);
	}
}
