package day04;

import java.util.Random;
import java.util.Scanner;

public class GuessGame {
	public static void main(String[] args) {
		Random r = new Random();
		int num = r.nextInt(10000);
		Scanner scan = new Scanner(System.in);
		System.out.println("猜吧");
		int guess = scan.nextInt();
		do {
			if (guess == 0) {
				break;
			}

			if (guess > num) {

				System.out.println("Too large");
			} else if (guess < num) {
				System.out.println("Too small");
			}
			System.out.println("再猜");
			guess = scan.nextInt();
		} while (guess != num);
		if (guess != 0)
			System.out.println("bingo!");
	}

}
