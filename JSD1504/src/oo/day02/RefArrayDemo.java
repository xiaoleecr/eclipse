package oo.day02;

public class RefArrayDemo {
	public static void main(String[] args) {
		/*Cell[] cells=new Cell[4];
		cells[0]=new Cell(0,0);
		cells[1]=new Cell(1,1);
		cells[2]=new Cell(2,2);*/
		
		Cell[] cells=new Cell[]{
				new Cell(0,0),
				new Cell(1,1),
				new Cell(2,2),
				new Cell(3,3)
		};
		int[][] arrs=new int[3][];
		arrs[0]=new int[2];
		arrs[1]=new int[3];
		arrs[2]=new int[2];
	
	for(int i=0;i<arrs.length;i++){
		for(int j=0;j<arrs[i].length;j++){
			arrs[i][j]=100;
			System.out.println(arrs[i][j]);
		}
	}

	}
	class T{
		Cell[] cells;
		T(int row,int col){
			cells=new Cell[4];
			cells[0]=new Cell(row,col);
			cells[1]=new Cell(row,col+1);
			cells[2]=new Cell(row,col+2);
			cells[3]=new Cell(row+1,col+1);
		}
		void drop(){
			cells[0].row++;
		}
		void moveLeft(){
			
		}
		void moveRight(){
			
		}
	}
}