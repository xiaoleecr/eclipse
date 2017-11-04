package ShootingGame;

import java.awt.image.BufferedImage;

public class Bullet extends FlyingObject {
	/**
	 * 子弹的前进速度
	 */
	private int forwardspeed;
	/**
	 * 子弹的横向速度
	 */
	private int translationspeed = 1;
	/**
	 * 射击的对象
	 */
	private int shootingObject;
	/**
	 * 飞行的方式
	 */
	private int flyingType;
	/**
	 * 出界结果
	 */
	private boolean outResult;
	/**
	 * 子弹名字
	 */
	private String bulletName;

	/**
	 * 英雄机X坐标
	 */
	private int herox;

	/**
	 * 英雄机Y坐标
	 */
	private int heroy;

	/**
	 * 射击时的X坐标
	 */
	private int shootx;

	/**
	 * 射击时的Y坐标
	 */
	private int shooty;

	public Bullet(int x, int y, BufferedImage image, ImageInfo img,
			int shootingObject, int flyingType) {
		this.x = x;
		this.y = y;
		this.image = image;
		this.shootingObject = shootingObject;
		this.flyingType = flyingType;
		bulletName = img.getName();
		this.width = img.getImagewidth();
		this.height = img.getImageheight();
	}

	public Bullet(int x, int y, BufferedImage image, ImageInfo img,
			int shootingObject, int flyingType, int herox, int heroy) {
		this.x = x;
		this.y = y;
		this.image = image;
		this.shootingObject = shootingObject;
		this.flyingType = flyingType;
		bulletName = img.getName();
		this.width = img.getImagewidth();
		this.height = img.getImageheight();
		this.herox = herox;
		this.heroy = heroy;
		shootx = x;
		shooty = y;
	}

	@Override
	public boolean outOfBounds() {
		switch (shootingObject) {
		case 0:// 英雄机
			outResult = y < -height || x < -width || x > Main.SCREENWIDTH;
			break;
		case 1:// Boss
			outResult = y > Main.SCREENHEIGHT || x < -width
					|| x > Main.SCREENWIDTH;
			break;
		case 2:// 敌机
			outResult = y > Main.SCREENHEIGHT || x < -width
					|| x > Main.SCREENWIDTH;
			break;
		case 3://僚机
			outResult = y < -height || x < -width || x > Main.SCREENWIDTH;
			break;
		}
		return outResult;
	}

	@Override
	public void step() {
		switch (shootingObject) {
		case 0:// 英雄机
			forwardspeed = 3;
			bulletType(flyingType);
			break;
		case 1:// Boss
			forwardspeed = -2;
			bulletType(flyingType);
			break;
		case 2:// 敌机
			forwardspeed = 1;
			enemyBulletType(flyingType);
			break;
		case 3://僚机
			forwardspeed = 2;
			bulletType(flyingType);
			break;
		}
	}

	@Override
	public void step(int actionType) {
		// TODO Auto-generated method stub

	}

	/**
	 * 英雄机与BOSS的子弹飞行方式
	 * 
	 * @param flyingType
	 */
	private void bulletType(int flyingType) {
		switch (flyingType) {
		case 0:// 直线型
			y -= forwardspeed;
			break;
		case 1:// 左移型1
			x -= translationspeed;
			y -= forwardspeed;
			break;
		case 2:// 右移型1
			x += translationspeed;
			y -= forwardspeed;
			break;
		case 3:// 左移型2
			x -= translationspeed + 1;
			y -= forwardspeed;
			break;
		case 4:// 右移型2
			x += translationspeed + 1;
			y -= forwardspeed;
			break;
		}
	}

	/**
	 * 敌机子弹飞行方式
	 */
	private void enemyBulletType(int flyingType) {
		switch (flyingType) {
		case 0:
			y += forwardspeed+1;
			if (herox < shootx) {
				x -= translationspeed;
			} else {
				x += translationspeed;
			}
			break;
		case 1:
			y += forwardspeed+2;
			if (herox < shootx) {
				x -= translationspeed + 1;
			} else {
				x += translationspeed + 1;
			}
			break;
		case 2:
			y += forwardspeed+2;
			if (herox < shootx) {
				x -= translationspeed + 2;
			} else {
				x += translationspeed + 2;
			}
			break;
		}
	}

	public String getBulletName() {
		return bulletName;
	}

	public int getType() {
		switch (shootingObject) {
		case 0:
			if (bulletName.equals("bulletA1")) {
				return 11;
			} else if (bulletName.equals("bulletA2")) {
				return 12;
			}
			if (bulletName.equals("bulletB1")) {
				return 21;
			} else if (bulletName.equals("bulletB2")) {
				return 22;
			}
			break;
		case 1:
			if (bulletName.equals("bulletC1")) {
				return 31;
			} else if (bulletName.equals("bulletC2")) {
				return 32;
			} else if (bulletName.equals("bulletC3")) {
				return 33;
			} else if (bulletName.equals("bulletC4")) {
				return 34;
			} else if (bulletName.equals("bulletC5")) {
				return 35;
			}
			break;
		case 2:
			if (bulletName.equals("bulletD1")) {
				return 41;
			} else if (bulletName.equals("bulletD2")) {
				return 42;
			} else if (bulletName.equals("bulletD3")) {
				return 43;
			} 
			break;
		case 3:
			if(bulletName.equals("missile")){
				return 51;
			}
			break;
		}
		return 0;
	}

	@Override
	public Bullet shoot(int hx, int hy) {
		// TODO Auto-generated method stub
		return null;
	}

}
