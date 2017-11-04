package day04;

import java.util.Scanner;

public class WhileDemo {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int a=scan.nextInt(),i;
		
		for(i=2;i<=a;i++){
			if(a%i==0){
				System.out.println("非质数");
				break;
				}
			else System.out.println("质数");
			}
		}
	}


