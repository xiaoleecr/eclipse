package ShootingGame;

import java.util.Random;

/**
 * 该类暂定为1号杂兵使用类
 * 
 * @author tarena
 * 
 */
public class Solider1 extends FlyingObject implements Enemy {
	private final int MOVE_LEFT = -1;
	private final int MOVE_RIGHT = 1;

	private int translation;
	private int forwardspeed = 1;
	private int translationspeed = 1;
	private int movetime;// 移动时间

	public Solider1(int stage, int width, int height) {
		switch (stage) {
		case Main.STAGE_A:
			this.image = Main.soldier_A1;
			break;
		case Main.STAGE_B:
			this.image = Main.soldier_B1;
			break;
		case Main.STAGE_C:
			this.image = Main.soldier_C1;
			break;
		}
		this.width = width;
		this.height = height;
		y = -this.height;
		Random rand = new Random();
		x = rand.nextInt(Main.SCREENWIDTH - this.width);
		if (x < Main.SCREENWIDTH / 2) {
			translation = MOVE_RIGHT;
		} else {
			translation = MOVE_LEFT;
		}
		movetype = rand.nextInt(2) + 1;
	}

	@Override
	public boolean outOfBounds() {
		return y > Main.SCREENHEIGHT || x < 0 - this.width
				|| x > Main.SCREENWIDTH;
	}

	@Override
	public void step() {
		switch (movetype) {
		case 1:
			action();
			break;
		case 2:
			forwardspeed = 2;
			y += forwardspeed;
			break;
		}
	}

	@Override
	public void step(int actionType) {
	}

	@Override
	public int getScore() {
		return 100;
	}

	@Override
	public int getType() {
		return SOLIDER1;
	}

	private void action() {
		movetime++;
		switch (translation) {
		case MOVE_RIGHT:
			y += forwardspeed;
			if (movetime % 75 == 0) {
				translationspeed++;
			}
			x += translationspeed;
			break;
		case MOVE_LEFT:
			y += forwardspeed;
			if (movetime % 75 == 0) {
				translationspeed++;
			}
			x -= translationspeed;
			break;
		}
	}

	public Bullet shoot(int hx, int hy) {
		Bullet bullet = new Bullet(x + width / 2
				- Main.bulletD1img.getImagewidth() / 2, y + height + 5,
				Main.bulletD1, Main.bulletD1img, 2, 0, hx, hy);
		return bullet;
	}
}
