package ShootingGame;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Boss extends FlyingObject implements Enemy {
	/**
	 * BOSS生命值
	 */
	private double lifepoint;
	/**
	 * BOSS火力状态
	 */
	private int firestate;
	/**
	 * BOSS火力等级
	 */
	private int firelevel;
	/**
	 * BOSS前进速度
	 */
	private int forwardspeed;
	/**
	 * BOSS横向速度
	 */
	private int translationspeed;
	/**
	 * 图片属性
	 */
	private ImageInfo imgi;

	/**
	 * BOSS机的子弹类型图片
	 */
	private BufferedImage bulletImage;

	/**
	 * BOSS当前状态
	 */
	private int bossstate;

	/**
	 * 爆炸顺序
	 */
	private int index;

	private int stage;

	/**
	 * 进场
	 */
	public static final int GOIN = 1;
	/**
	 * 战斗
	 */
	public static final int FIGHT = 2;
	/**
	 * 退场
	 */
	public static final int GOOUT = 3;

	public Boss(int stage) {
		lifepoint = 50000 * stage;
		firestate = 1;
		firelevel = 1;
		bulletImage = Main.bulletC1;
		bossstate = GOIN;
		this.stage = stage;
		switch (stage) {
		case Main.STAGE_A:
			image = Main.boss1;
			width = Main.boss1img.getImagewidth();
			height = Main.boss1img.getImageheight();
			break;
		case Main.STAGE_B:
			image = Main.boss2;
			width = Main.boss2img.getImagewidth();
			height = Main.boss2img.getImageheight();
			break;
		case Main.STAGE_C:
			image = Main.boss3;
			width = Main.boss3img.getImagewidth();
			height = Main.boss3img.getImageheight();
			break;
		}
		Random random = new Random();
		switch (random.nextInt(2)) {
		case 0:
			translationspeed = 1;
			break;
		case 1:
			translationspeed = -1;
			break;
		}
		x = Main.SCREENWIDTH / 2 - width / 2;
		y = 0 - height;
	}

	public double getLifepoint() {
		if (lifepoint < 40000 * stage) {
			firestate = 2;
			if (lifepoint < 30000 * stage) {
				firestate = 3;
				if (lifepoint < 20000 * stage) {
					firestate = 4;
					if (lifepoint < 10000 * stage) {
						firestate = 5;
					}
				}
			}
		}
		if (lifepoint <= 0) {
			bossstate = GOOUT;
		}
		return lifepoint;
	}

	public void setLifepoint(double lifepoint) {
		this.lifepoint = lifepoint;
	}

	public void getDamage() {
		lifepoint -= 250;
	}

	public int getFirestate() {
		return firestate;
	}

	public void setFirestate(int firestate) {
		this.firestate = firestate;
	}

	public int getFirelevel() {
		return firelevel;
	}

	public void setFirelevel(int firelevel) {
		this.firelevel = firelevel;
	}

	public void addFirelevel() {
		if (firelevel < 3) {
			firelevel++;
		}
	}

	public void setBossstate(int state) {
		bossstate = state;
	}

	public int getBossstate() {
		return bossstate;
	}

	@Override
	public boolean outOfBounds() {
		return y < 0 - height - 10;
	}

	@Override
	public void step() {
		switch (bossstate) {
		case GOIN:
			forwardspeed = 1;
			y += forwardspeed;
			if (y > 20) {
				bossstate = FIGHT;
				y = y;
			}
			break;
		case FIGHT:
			if (x < 0) {
				translationspeed = 1;
			}
			if (x > Main.SCREENWIDTH - width) {
				translationspeed = -1;
			}
			x += translationspeed;
			break;
		case GOOUT:
			forwardspeed = -1;
			y += forwardspeed;
			break;
		}
	}

	@Override
	public void step(int actionType) {
	}

	private void changeBulletImage() {
		switch (firestate) {
		case 1:
			imgi = Main.bulletC1img;
			break;
		case 2:
			imgi = Main.bulletC2img;
			break;
		case 3:
			imgi = Main.bulletC3img;
			break;
		case 4:
			imgi = Main.bulletC4img;
			break;
		case 5:
			imgi = Main.bulletC5img;
			break;
		}
	}

	public Bullet[] shoot() {
		changeBulletImage();
		int xstep = this.width / 8;
		int ystep = 20;
		Bullet[] bullet = {};
		switch (firestate) {
		case 1:
			bullet = new Bullet[1];
			bullet[0] = new Bullet(x + 4 * xstep, y + width + ystep,
					bulletImage, imgi, 1, 0);
			break;
		case 2:
			bullet = new Bullet[2];
			bullet[0] = new Bullet(x + 3 * xstep, y + width + ystep,
					bulletImage, imgi, 1, 1);
			bullet[1] = new Bullet(x + 5 * xstep, y + width + ystep,
					bulletImage, imgi, 1, 2);
			break;
		case 3:
			bullet = new Bullet[3];
			bullet[0] = new Bullet(x + 4 * xstep, y + width + ystep,
					bulletImage, imgi, 1, 0);
			bullet[1] = new Bullet(x + 3 * xstep, y + width + ystep / 8,
					bulletImage, imgi, 1, 1);
			bullet[2] = new Bullet(x + 5 * xstep, y + width + ystep / 8,
					bulletImage, imgi, 1, 2);
			break;
		case 4:
			bullet = new Bullet[4];
			bullet[0] = new Bullet(x + 2 * xstep, y + width + ystep / 8,
					bulletImage, imgi, 1, 3);
			bullet[1] = new Bullet(x + 6 * xstep, y + width + ystep / 8,
					bulletImage, imgi, 1, 4);
			bullet[2] = new Bullet(x + 3 * xstep, y + width + ystep,
					bulletImage, imgi, 1, 1);
			bullet[3] = new Bullet(x + 5 * xstep, y + width + ystep,
					bulletImage, imgi, 1, 2);
			break;
		case 5:
			bullet = new Bullet[5];
			bullet[0] = new Bullet(x + 4 * xstep, y + width + ystep,
					bulletImage, imgi, 1, 0);
			bullet[1] = new Bullet(x + 2 * xstep, y + width + ystep / 8,
					bulletImage, imgi, 1, 3);
			bullet[2] = new Bullet(x + 6 * xstep, y + width + ystep / 8,
					bulletImage, imgi, 1, 4);
			bullet[3] = new Bullet(x + 3 * xstep, y + width + ystep / 2,
					bulletImage, imgi, 1, 1);
			bullet[4] = new Bullet(x + 5 * xstep, y + width + ystep / 2,
					bulletImage, imgi, 1, 2);
			break;
		}
		return bullet;
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 10000;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public boolean shootBy(Bullet bullet) {
		int x = bullet.x; // 子弹横坐标
		int y = bullet.y; // 子弹纵坐标
		return this.x < x && x < this.x + width && this.y < y
				&& y < this.y + height;
	}

	public Explosion dead() {
		int xstep2 = Main.SCREENWIDTH / 3;
		int ystep2 = Main.SCREENHEIGHT / 3;
		index++;
		switch (index % 9) {
		case 0:
			return new Explosion(0, 0);// 左上
		case 1:
			return new Explosion(xstep2 * 2, ystep2 * 2);// 右下
		case 2:
			return new Explosion(0, ystep2 * 2);// 左下
		case 3:
			return new Explosion(xstep2 * 2, 0);// 右上
		case 4:
			return new Explosion(0, ystep2);// 左中
		case 5:
			return new Explosion(xstep2 * 2, ystep2);// 右中
		case 6:
			return new Explosion(xstep2, ystep2);// 中心
		case 7:
			return new Explosion(xstep2, ystep2 * 2);// 中下
		case 8:
			return new Explosion(xstep2, 0);// 中上
		}
		return null;
	}

	@Override
	public Bullet shoot(int hx, int hy) {
		// TODO Auto-generated method stub
		return null;
	}
}
