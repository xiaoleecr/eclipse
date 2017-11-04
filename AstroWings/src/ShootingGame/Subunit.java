package ShootingGame;

import java.awt.image.BufferedImage;

public class Subunit extends FlyingObject {
	private int subunitindex;// 僚机图片序号
	private int index;// 僚机序号
	private int herowidth;
	private int heroheight;
	private int time;
	private ImageInfo[] imgis;
	private int firelevel;
	private int x1;
	private int x2;
	private int y1;
	private int y2;

	public Subunit(int index, int herox, int heroy, int herowidth,
			int heroheight, ImageInfo[] imgis) {
		this.image = Main.subunit;
		this.imgis = imgis;
		this.width = imgis[0].getImagewidth();
		this.height = imgis[0].getImageheight();
		this.herowidth = herowidth;
		this.heroheight = heroheight;
		firelevel = 1;
		this.index = index;
		switch (index) {
		case 1:// 左僚机
			this.x = herox - herowidth / 2 - width - 5;
			this.y = heroy - height / 2;
			break;
		case 2:// 右僚机
			this.x = herox + herowidth / 2 + width + 5;
			this.y = heroy - height / 2;
			break;
		}
	}

	/**
	 * 增加僚机火力
	 */
	public void addFireLevel() {
		if (firelevel < 3) {
			firelevel += 1;
		}
	}

	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return false;
	}

	public Bullet[] shoot() {
		Bullet[] bullets = {};
		switch (firelevel) {
		case 1:
			bullets = new Bullet[1];
			bullets[0] = new Bullet(x + width / 2
					- Main.waveimg.getImagewidth() / 2, y - 5, Main.wave,
					Main.waveimg, 3, 0);
			break;
		case 2:
			bullets = new Bullet[2];
			bullets[0] = new Bullet(x + width / 3
					- Main.waveimg.getImagewidth() / 2, y - 5, Main.wave,
					Main.waveimg, 3, 1);
			bullets[1] = new Bullet(x + width * 2 / 3
					- Main.waveimg.getImagewidth() / 2, y - 5, Main.wave,
					Main.waveimg, 3, 2);
			break;
		case 3:
			bullets = new Bullet[3];
			bullets[0] = new Bullet(x + width / 2
					- Main.waveimg.getImagewidth() / 2, y - 5, Main.wave,
					Main.waveimg, 3, 0);
			bullets[1] = new Bullet(x + width / 3
					- Main.waveimg.getImagewidth() / 2, y - 5, Main.wave,
					Main.waveimg, 3, 1);
			bullets[2] = new Bullet(x + width * 2 / 3
					- Main.waveimg.getImagewidth() / 2, y - 5, Main.wave,
					Main.waveimg, 3, 2);
			break;
		}
		return bullets;
	}

	@Override
	public Bullet shoot(int hx, int hy) {
		// TODO Auto-generated method stub
		return null;
	}

	public void step(int herox, int heroy) {
		time++;
		if (time % 10 == 0) {
			subunitindex++;
		}
		x1 = imgis[subunitindex % 31].getX1();
		x2 = imgis[subunitindex % 31].getX2();
		y1 = imgis[subunitindex % 31].getY1();
		y2 = imgis[subunitindex % 31].getY2();
		switch (index) {
		case 1:// 左僚机
			this.x = herox - herowidth / 2 - 5;
			this.y = heroy - height / 2;
			break;
		case 2:// 右僚机
			this.x = herox + herowidth / 2 + width + 5;
			this.y = heroy - height / 2;
			break;
		}
	}

	@Override
	public void step(int actionType) {
		// TODO Auto-generated method stub

	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub

	}

}
