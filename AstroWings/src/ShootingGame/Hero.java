package ShootingGame;

import java.awt.image.BufferedImage;

public class Hero extends FlyingObject {
	private int forwardspeed = 1;

	private static final int STATIC = 0;
	private static final int MOVELEFT1 = -1;
	private static final int MOVELEFT2 = -2;
	private static final int MOVERIGHT1 = 1;
	private static final int MOVERIGHT2 = 2;

	private static final int OUT_TOP = 1;
	private static final int OUT_RIGHT = 2;
	private static final int OUT_BOTTOM = 3;
	private static final int OUT_LEFT = 4;

	public static final int REBORN = 0;
	public static final int FIGHT = 1;
	public static final int FLYING = 2;

	private boolean leaving = false;
	/**
	 * 英雄机图片组
	 */
	private BufferedImage[] images = {};
	/**
	 * 英雄机图片索引
	 */
	private int index = 0;
	/**
	 * 英雄机的火力类型(默认值为1)
	 */
	private int fireType;
	/**
	 * 英雄机的火力级别(默认值为1)
	 */
	private int fireLevel;
	/**
	 * 英雄机的生命
	 */
	private int life;
	/**
	 * 英雄机的子弹类型图片
	 */
	private BufferedImage bulletImage;

	// /**
	// * 英雄机中心点x坐标
	// */
	// private int cx = x + width / 2;
	// /**
	// * 英雄机中心点y坐标
	// */
	// private int cy = y + height / 2;
	/**
	 *英雄机图片信息
	 */
	private ImageInfo imgi;

	/**
	 * 英雄机状态
	 */
	private int herogamestate;

	public Hero() {
		life = 3;
		fireType = 1;
		imgi = Main.bulletA1img;
		fireLevel = 1;
		images = new BufferedImage[] { Main.hero1, Main.hero1l1, Main.hero1l2,
				Main.hero1r1, Main.hero1r2 };
		image = Main.hero1;
		width = Main.hero1img.getImagewidth();
		height = Main.hero1img.getImageheight();
		x = Main.SCREENWIDTH / 2 - width / 2;
		y = Main.SCREENHEIGHT;
		herogamestate = REBORN;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getFireType() {
		return fireType;
	}

	public void setFireType(int fireType) {
		this.fireType = fireType;
		switch (fireType) {
		case 1:
			bulletImage = Main.bulletA1;
			changeBulletImage();
			break;
		case 2:
			bulletImage = Main.bulletB2;
			changeBulletImage();
			break;
		}
	}

	public int getFireLevel() {
		return fireLevel;
	}

	public void setFireLevel(int fireLevel) {
		this.fireLevel = fireLevel;
	}

	public void addFireLevel() {
		if (fireLevel < 5) {
			fireLevel++;
		}
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public void addLife() {
		life++;
	}

	public void subtractLife() {
		life--;
	}

	public int getHerogamestate() {
		return herogamestate;
	}

	public void setHerogamestate(int state) {
		herogamestate = state;
	}

	public void setForwardspeed(int speed) {
		forwardspeed = speed;
	}

	/**
	 * 因为传进来的不再是鼠标的坐标所以不需要减
	 * 
	 * @param x
	 * @param y
	 * @param herostate
	 */
	public void moveTo(int x, int y, int herostate) {
		heroState(herostate);
		this.x = x - width / 2;
		this.y = y - height / 2;
		// this.x = x;
		// this.y = y;
		switch (outOfBounds(x, y)) {
		case OUT_TOP:
			this.y = 0;
			break;
		case OUT_BOTTOM:
			this.y = Main.SCREENHEIGHT - height - 25;
			break;
		case OUT_LEFT:
			this.x = 0;
			break;
		case OUT_RIGHT:
			this.x = Main.SCREENWIDTH - width;
			break;
		}
	}

	public Bullet[] shoot() {
		switch (fireType) {
		case 1:// 直线型
			return bulletA();
		case 2:// 散射型
			return bulletB();
		}
		return null;
	}

	/**
	 * 切换飞机移动图片时的宽高修改
	 * 
	 * @param herostatus飞机移动状态
	 */
	public void heroState(int herostate) {
		switch (herostate) {
		case STATIC:
			width = Main.hero1img.getImagewidth();
			height = Main.hero1img.getImageheight();
			break;
		case MOVELEFT1:
			width = Main.hero1l1img.getImagewidth();
			height = Main.hero1l1img.getImageheight();
			break;
		case MOVELEFT2:
			width = Main.hero1l2img.getImagewidth();
			height = Main.hero1l2img.getImageheight();
			break;
		case MOVERIGHT1:
			width = Main.hero1r1img.getImagewidth();
			height = Main.hero1r1img.getImageheight();
			break;
		case MOVERIGHT2:
			width = Main.hero1r2img.getImagewidth();
			height = Main.hero1r2img.getImageheight();
			break;
		}
	}

	@Override
	public boolean outOfBounds() {
		// if(x<1||x+width>512||y<1||y+height>512){
		// return true;
		// }
		return false;
	}

	/**
	 * 重写英雄机出界判定
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int outOfBounds(int x, int y) {
		if (x < width / 2) {
			return OUT_LEFT;
		}
		if (x > Main.SCREENWIDTH - width / 2) {
			return OUT_RIGHT;
		}
		if (y < height / 2 && herogamestate != FLYING) {
			return OUT_TOP;
		}
		if (y > Main.SCREENHEIGHT - height && herogamestate == FIGHT) {
			return OUT_BOTTOM;
		}
		return 0;
	}

	@Override
	public void step() {
		switch (herogamestate) {
		case REBORN:
			forwardspeed = 1;
			// System.out.println(y);
			if (y > 322 + height / 2) {
				y -= forwardspeed;
			} else {
				herogamestate = FIGHT;
			}
			break;
		case FIGHT:
			if (leaving) {
				leaving = false;
			}
			break;
		case FLYING:
			if (!leaving) {
				if (x != 256 + width / 2 && y != 356 + height / 2) {
					if (x < 256 + width / 2) {
						x += 1;
					} else {
						x -= 1;
					}
					if (y < 322 + height / 2) {
						y += 1;
					} else {
						y -= 1;
					}
				} else {
					leaving = true;
					forwardspeed = 6;
				}
			} else {
				y -= forwardspeed;
			}
			break;
		}
	}

	@Override
	public void step(int actionType) {

	}

	/**
	 * 更换英雄机子弹的属性
	 */
	private void changeBulletImage() {
		switch (fireType) {
		case 1:
			imgi = Main.bulletA1img;
			break;
		case 2:
			imgi = Main.bulletB2img;
			break;
		}
	}

	private Bullet[] bulletA() {
		int xstep = this.width / 9;
		int ystep = 20;
		Bullet[] bullet = {};
		switch (fireLevel) {
		case 1:
			bullet = new Bullet[1];
			bullet[0] = new Bullet(x + 4 * xstep - imgi.getImagewidth() / 2, y
					- ystep, bulletImage, imgi, 0, 0);
			break;
		case 2:
			bullet = new Bullet[2];
			bullet[0] = new Bullet(x + 2 * xstep - imgi.getImagewidth() / 2, y
					- ystep, bulletImage, imgi, 0, 0);
			bullet[1] = new Bullet(x + 7 * xstep - imgi.getImagewidth() / 2, y
					- ystep, bulletImage, imgi, 0, 0);
			break;
		case 3:
			bullet = new Bullet[3];
			bullet[0] = new Bullet(x + 4 * xstep - imgi.getImagewidth() / 2, y
					- ystep, bulletImage, imgi, 0, 0);
			bullet[1] = new Bullet(x + 2 * xstep - imgi.getImagewidth() / 2, y
					- ystep / 8, bulletImage, imgi, 0, 0);
			bullet[2] = new Bullet(x + 7 * xstep - imgi.getImagewidth() / 2, y
					- ystep / 8, bulletImage, imgi, 0, 0);
			break;
		case 4:
			bullet = new Bullet[4];
			bullet[0] = new Bullet(x + 1 * xstep - imgi.getImagewidth() / 2, y
					- ystep / 8, bulletImage, imgi, 0, 0);
			bullet[1] = new Bullet(x + 7 * xstep - imgi.getImagewidth() / 2, y
					- ystep / 8, bulletImage, imgi, 0, 0);
			bullet[2] = new Bullet(x + 2 * xstep - imgi.getImagewidth() / 2, y
					- ystep, bulletImage, imgi, 0, 0);
			bullet[3] = new Bullet(x + 6 * xstep - imgi.getImagewidth() / 2, y
					- ystep, bulletImage, imgi, 0, 0);
			break;
		case 5:
			bullet = new Bullet[5];
			bullet[0] = new Bullet(x + 4 * xstep - imgi.getImagewidth() / 2, y
					- ystep, bulletImage, imgi, 0, 0);
			bullet[1] = new Bullet(x + 1 * xstep - imgi.getImagewidth() / 2, y
					+ ystep / 5, bulletImage, imgi, 0, 0);
			bullet[2] = new Bullet(x + 7 * xstep - imgi.getImagewidth() / 2, y
					+ ystep / 5, bulletImage, imgi, 0, 0);
			bullet[3] = new Bullet(x + 2 * xstep - imgi.getImagewidth() / 2, y
					- ystep / 8, bulletImage, imgi, 0, 0);
			bullet[4] = new Bullet(x + 6 * xstep - imgi.getImagewidth() / 2, y
					- ystep / 8, bulletImage, imgi, 0, 0);
			break;
		}
		return bullet;
	}

	private Bullet[] bulletB() {
		int xstep = this.width / 9;
		int ystep = 20;
		Bullet[] bullet = {};
		switch (fireLevel) {
		case 1:
			bullet = new Bullet[1];
			bullet[0] = new Bullet(x + 4 * xstep - imgi.getImagewidth() / 2, y
					- ystep, bulletImage, imgi, 0, 0);
			break;
		case 2:
			bullet = new Bullet[2];
			bullet[0] = new Bullet(x + 2 * xstep - imgi.getImagewidth() / 2, y
					- ystep, bulletImage, imgi, 0, 1);
			bullet[1] = new Bullet(x + 7 * xstep - imgi.getImagewidth() / 2, y
					- ystep, bulletImage, imgi, 0, 2);
			break;
		case 3:
			bullet = new Bullet[3];
			bullet[0] = new Bullet(x + 4 * xstep - imgi.getImagewidth() / 2, y
					- ystep, bulletImage, imgi, 0, 0);
			bullet[1] = new Bullet(x + 2 * xstep - imgi.getImagewidth() / 2, y
					- ystep / 8, bulletImage, imgi, 0, 1);
			bullet[2] = new Bullet(x + 7 * xstep - imgi.getImagewidth() / 2, y
					- ystep / 8, bulletImage, imgi, 0, 2);
			break;
		case 4:
			bullet = new Bullet[4];
			bullet[0] = new Bullet(x + 1 * xstep - imgi.getImagewidth() / 2, y
					- ystep / 8, bulletImage, imgi, 0, 3);
			bullet[1] = new Bullet(x + 7 * xstep - imgi.getImagewidth() / 2, y
					- ystep / 8, bulletImage, imgi, 0, 4);
			bullet[2] = new Bullet(x + 2 * xstep - imgi.getImagewidth() / 2, y
					- ystep, bulletImage, imgi, 0, 1);
			bullet[3] = new Bullet(x + 6 * xstep - imgi.getImagewidth() / 2, y
					- ystep, bulletImage, imgi, 0, 2);
			break;
		case 5:
			bullet = new Bullet[5];
			bullet[0] = new Bullet(x + 4 * xstep - imgi.getImagewidth() / 2, y
					- ystep, bulletImage, imgi, 0, 0);
			bullet[1] = new Bullet(x + 1 * xstep - imgi.getImagewidth() / 2, y
					+ ystep / 5, bulletImage, imgi, 0, 3);
			bullet[2] = new Bullet(x + 7 * xstep - imgi.getImagewidth() / 2, y
					+ ystep / 5, bulletImage, imgi, 0, 4);
			bullet[3] = new Bullet(x + 2 * xstep - imgi.getImagewidth() / 2, y
					- ystep / 8, bulletImage, imgi, 0, 1);
			bullet[4] = new Bullet(x + 6 * xstep - imgi.getImagewidth() / 2, y
					- ystep / 8, bulletImage, imgi, 0, 2);
			break;
		}
		return bullet;
	}

	/**
	 * 英雄机与飞行物碰撞算法
	 * 
	 * @param obj
	 * @return
	 */
	public boolean hit(FlyingObject obj) {
		int x1 = obj.x - this.width / 3;
		int x2 = obj.x + this.width / 3 + obj.width;
		int y1 = obj.y - this.height / 3;
		int y2 = obj.y + this.height / 3 + obj.height;

		int herox = this.x + this.width / 2;
		int heroy = this.y + this.height / 2;

		return herox > x1 && herox < x2 && heroy > y1 && heroy < y2;
	}

	/**
	 * 英雄机与子弹碰撞算法
	 * 
	 * @param bullet
	 * @return
	 */
	public boolean hit(Bullet bullet) {
		int x1 = bullet.x - this.width / 5; // x坐标最小距离
		int x2 = bullet.x + this.width / 5 + bullet.width; // x坐标最大距离
		int y1 = bullet.y - this.height / 5; // y坐标最小距离
		int y2 = bullet.y + this.height / 5 + bullet.height; // y坐标最大距离

		int herox = this.x + this.width / 2; // 英雄机x坐标中心点距离
		int heroy = this.y + this.height / 2; // 英雄机y坐标中心点距离

		return herox > x1 && herox < x2 && heroy > y1 && heroy < y2; // 区间范围内为撞上了
	}

	/**
	 * 英雄机与道具碰撞
	 * 
	 * @param item
	 * @return
	 */
	public boolean hit(Item item) {
		int x1 = item.x - this.width / 2;
		int x2 = item.x + this.width / 2 + item.width;
		int y1 = item.y - this.height / 2;
		int y2 = item.y + this.height / 2 + item.height;

		int itemx = this.x + this.width / 2;
		int itemy = this.y + this.height / 2;

		return itemx > x1 && itemx < x2 && itemy > y1 && itemy < y2;
	}

	/**
	 * 英雄机与BSS碰撞
	 * 
	 * @param boss
	 * @return
	 */
	public boolean hit(Boss boss) {
		int x1 = boss.x - this.width / 3;
		int x2 = boss.x + this.width / 3 + boss.width;
		int y1 = boss.y - this.height / 3;
		int y2 = boss.y + this.height / 3 + boss.height;

		int bossx = this.x + this.width / 2;
		int bossy = this.y + this.height / 2;

		return bossx > x1 && bossx < x2 && bossy > y1 && bossy < y2;
	}

	@Override
	public Bullet shoot(int hx, int hy) {
		// TODO Auto-generated method stub
		return null;
	}
}
