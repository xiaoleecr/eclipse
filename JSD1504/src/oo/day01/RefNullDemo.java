package oo.day01;

import java.util.Scanner;

public class RefNullDemo {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int x = 0, y = 0;
		Cell c = new Cell();
		System.out.println("请输入：\n4左移1格\n6右移一格\n8上移一格\n5下移一格");
		c.col = 2;
		c.row = 5;
		do {
			x = scan.nextInt();
			if (x == 4) {
				c.moveLeft(1);
			} else if (x == 6) {
				c.moveRight(1);
			} else if (x == 8) {
				c.moveUp();
			} else if (x == 5) {
				c.drop();
			} else if (x == 0) {
				System.out.println("EXTI");
				break;
			}

			paintWall(c);
		} while (true);
	}

	public static void paintWall(Cell c) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				if (j == c.col && i == c.row) {
					System.out.print("* ");
				} else {
					System.out.print("- ");
				}
			}
			System.out.println();
		}

	}
}
