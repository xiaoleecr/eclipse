package oo.day02;

import java.util.Scanner;

public class HomeWorkCellTest {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int x;
		Cell cell=new Cell();//创建一个对象
		cell.row=4;//对象的行号是4
		cell.col=4;//对象的列号是4
		System.out.println("请输入移动");
		printWall(cell);//画墙
		do{x=scan.nextInt();
			if(x==4){//左移一格
				cell.moveLeft();
			}
			else if(x==6){//右移一格
				cell.moveRight();
			}
			else if(x==8){//上移一格
				cell.moveUp();
			}
			else if(x==5){//下移一格
				cell.drop();
			}
			else if(x==0){
				System.out.println("退出游戏！");
				break;
			}
			printWall(cell);
			}while(true);
	}
	public static void printWall(Cell cell){
		for(int i=1;i<=20;i++){
			for(int j=1;j<=10;j++){
				if(j==cell.row&&i==cell.col){
					System.out.print("* ");
				}else{System.out.print("- ");}
			}System.out.println();
		}
	}

}
