package ShootingGame;

import java.awt.image.BufferedImage;

public class Explosion extends FlyingObject {
	private BufferedImage image;
	private boolean isover = false;
	private int index;
	private int times;

	private int x1;
	private int x2;
	private int y1;
	private int y2;

	public Explosion(int x, int y) {// 之所以不直接将爆炸对象作为参数是为了防止
		this.x = x;
		this.y = y;
		image = Main.explosion;
		// 爆炸图片的宽高都一样
		width = Main.explosion0.getImagewidth();
		height = Main.explosion0.getImageheight();
		x1 = Main.explosion0.getX1();
		x2 = Main.explosion0.getX2();
		y1 = Main.explosion0.getY1();
		y2 = Main.explosion0.getY2();
		index = 0;
	}

	public boolean getIsOver() {
		return isover;
	}

	public int getX1() {
		return x1;
	}

	public int getX2() {
		return x2;
	}

	public int getY1() {
		return y1;
	}

	public int getY2() {
		return y2;
	}

	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void step() {
		times++;

		if (index < 15) {
			switch (index) {
			case 0:
				x1 = Main.explosion0.getX1();
				x2 = Main.explosion0.getX2();
				y1 = Main.explosion0.getY1();
				y2 = Main.explosion0.getY2();
				break;
			case 1:
				x1 = Main.explosion1.getX1();
				x2 = Main.explosion1.getX2();
				y1 = Main.explosion1.getY1();
				y2 = Main.explosion1.getY2();
				break;
			case 2:
				x1 = Main.explosion2.getX1();
				x2 = Main.explosion2.getX2();
				y1 = Main.explosion2.getY1();
				y2 = Main.explosion2.getY2();
				break;
			case 3:
				x1 = Main.explosion3.getX1();
				x2 = Main.explosion3.getX2();
				y1 = Main.explosion3.getY1();
				y2 = Main.explosion3.getY2();
				break;
			case 4:
				x1 = Main.explosion4.getX1();
				x2 = Main.explosion4.getX2();
				y1 = Main.explosion4.getY1();
				y2 = Main.explosion4.getY2();
				break;
			case 5:
				x1 = Main.explosion5.getX1();
				x2 = Main.explosion5.getX2();
				y1 = Main.explosion5.getY1();
				y2 = Main.explosion5.getY2();
				break;
			case 6:
				x1 = Main.explosion6.getX1();
				x2 = Main.explosion6.getX2();
				y1 = Main.explosion6.getY1();
				y2 = Main.explosion6.getY2();
				break;
			case 7:
				x1 = Main.explosion7.getX1();
				x2 = Main.explosion7.getX2();
				y1 = Main.explosion7.getY1();
				y2 = Main.explosion7.getY2();
				break;
			case 8:
				x1 = Main.explosion8.getX1();
				x2 = Main.explosion8.getX2();
				y1 = Main.explosion8.getY1();
				y2 = Main.explosion8.getY2();
				break;
			case 9:
				x1 = Main.explosion9.getX1();
				x2 = Main.explosion9.getX2();
				y1 = Main.explosion9.getY1();
				y2 = Main.explosion9.getY2();
				break;
			case 10:
				x1 = Main.explosion10.getX1();
				x2 = Main.explosion10.getX2();
				y1 = Main.explosion10.getY1();
				y2 = Main.explosion10.getY2();
				break;
			case 11:
				x1 = Main.explosion11.getX1();
				x2 = Main.explosion11.getX2();
				y1 = Main.explosion11.getY1();
				y2 = Main.explosion11.getY2();
				break;
			case 12:
				x1 = Main.explosion12.getX1();
				x2 = Main.explosion12.getX2();
				y1 = Main.explosion12.getY1();
				y2 = Main.explosion12.getY2();
				break;
			case 13:
				x1 = Main.explosion13.getX1();
				x2 = Main.explosion13.getX2();
				y1 = Main.explosion13.getY1();
				y2 = Main.explosion13.getY2();
				break;
			case 14:
				x1 = Main.explosion14.getX1();
				x2 = Main.explosion14.getX2();
				y1 = Main.explosion14.getY1();
				y2 = Main.explosion14.getY2();
				break;
			}
			if (times % 5 == 0) {
				index++;
			}
		} else {
			isover = true;
		}
	}

	@Override
	public void step(int actionType) {
		// TODO Auto-generated method stub

	}

	@Override
	public Bullet shoot(int hx, int hy) {
		// TODO Auto-generated method stub
		return null;
	}

}
