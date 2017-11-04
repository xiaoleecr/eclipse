package day04;

import java.util.Random;
import java.util.Scanner;

public class Additon {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		Random r=new Random();
		int score=0,x=0,y=0,z=0;
		for(int i=1;i<=10;i++){
			
			x=r.nextInt(100);
			y=r.nextInt(100);
			System.out.println("请计算:"+x+"+"+y+"=?");
			z=scan.nextInt();
			if(z==-1){
				System.out.println("exited!");
				break;
			}
			if(z==x+y){
				System.out.println("你答对了");
				score=score+1;
			}else System.out.println("很遗憾");
		}
		System.out.println("你的得分是："+score);
	}

}
