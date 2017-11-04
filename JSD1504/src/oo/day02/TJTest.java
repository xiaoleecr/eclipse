package oo.day02;

public class TJTest {
	public static void main(String[] args) {
		J t=new J(2,5);
		TJTest tjt=new TJTest();
		System.out.println("原始坐标:");
	//	t.print();
		tjt.printWall(t);
	}
public void printWall(J obj){
	Cell[] cell=obj.cells;
	for(int i=0;i<20;i++){
		for(int j=0;j<10;j++){
			boolean flag=true;
			for(int k=0;k<cell.length;k++)
			if(cell[k].row==i&&cell[k].col==j){
				flag = false;
				break;}
			if(flag==false){
				System.out.print("* ");
			}else{
			System.out.print("- ");
			}
		}
		System.out.println();
	}
}
}
