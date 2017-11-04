package ShootingGame;

import java.awt.image.BufferedImage;

public abstract class FlyingObject {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected BufferedImage image;
	protected int movetype;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	/**
	 * 出界检查
	 * 
	 * @return 是否出界
	 */
	public abstract boolean outOfBounds();

	/**
	 * 飞行物的移动
	 */
	public abstract void step();
	
	/**
	 * 飞行物的移动（有参）
	 * @param actionType移动方式
	 */
	public abstract void step(int actionType);
	
	/**
	 * 命中判定
	 * @param bullet 击中的子弹
	 * @return 命中判定
	 */
	public boolean shootBy(Bullet bullet) {
		int x = bullet.x;
		int y = bullet.y;
		return this.x < x && x < this.x + width && this.y < y
				&& y < this.y + height;
	}
	
	/**
	 * 敌机射击专用
	 * @param hx
	 * @param hy
	 * @return
	 */
	public abstract Bullet shoot(int hx,int hy);
}
