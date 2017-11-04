package ShootingGame;

import java.util.Random;

public class Solider2 extends FlyingObject implements Enemy {
	private final int MOVE_LEFT=-1;
	private final int MOVE_RIGHT=1;
	
	private int translation;
	private int forwardspeed = 2;
	private int translationspeed = 1;
	private int movetime;// 移动时间

	public Solider2(int stage, int width, int height) {
		switch (stage) {
		case Main.STAGE_A:
			this.image = Main.soldier_A2;
			break;
		case Main.STAGE_B:
			this.image = Main.soldier_B2;
			break;
		case Main.STAGE_C:
			this.image = Main.soldier_C2;
			break;
		}
		this.width = width;
		this.height = height;
		y = -this.height;
		Random rand = new Random();
		x = rand.nextInt(Main.SCREENWIDTH - this.width);
		if(x<Main.SCREENWIDTH/2){
			translation=MOVE_RIGHT;
		}else{
			translation=MOVE_LEFT;
		}
		movetype=rand.nextInt(2)+1;
	}

	@Override
	public boolean outOfBounds() {
		return y > Main.SCREENHEIGHT || x < 0 - this.width
				|| x > Main.SCREENWIDTH || y < 0 - this.height - 20;
	}

	@Override
	public void step() {
		switch (movetype) {
		case 1:
			action();
			break;
		case 2:
			forwardspeed=2;
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
		return SOLIDER2;
	}

	private void action() {
		movetime++;
		switch(translation){
		case MOVE_RIGHT:
			y += forwardspeed;
			if (movetime % 80 == 0) {
				forwardspeed-=2;
//				translationspeed++;
			}
			x += translationspeed;
			break;
		case MOVE_LEFT:
			y += forwardspeed;
			if (movetime % 80 == 0) {
				forwardspeed-=2;
//				translationspeed++;
			}
			x -= translationspeed;
			break;
		}
	}

	@Override
	public Bullet shoot(int hx, int hy) {
		Bullet bullet = new Bullet(x + width / 2- Main.bulletD2img.getImagewidth() / 2, y + height + 5,
				Main.bulletD2, Main.bulletD2img, 2, 1, hx, hy);
		return bullet;
	}
}
