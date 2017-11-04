package ShootingGame;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Item extends FlyingObject implements Award {
	private int forwardspeed = 1;
	private int translationspeed;
	private int awardType;

	public Item(int x, int y, ImageInfo img, int awardType) {
		this.image = Main.item;
		width = img.getImagewidth();
		height = img.getImageheight();
		this.x = x;
		this.y = y;
		this.awardType = awardType;
		Random random = new Random();
		switch (random.nextInt(2)) {
		case 0:
			translationspeed = -1;
			break;
		case 1:
			translationspeed = 1;
			break;
		}
	}

	@Override
	public boolean outOfBounds() {
		return y > Main.SCREENHEIGHT;
	}

	@Override
	public void step() {
		if (x < 0) {
			translationspeed=1;
		}
		if(x>Main.SCREENWIDTH-width){
			translationspeed=-1;
		}
		x += translationspeed;
		y += forwardspeed;

	}

	@Override
	public void step(int actionType) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getType() {
		return awardType;
	}

	@Override
	public Bullet shoot(int hx, int hy) {
		// TODO Auto-generated method stub
		return null;
	}

}
