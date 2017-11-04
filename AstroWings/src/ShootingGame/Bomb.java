package ShootingGame;

import java.awt.image.BufferedImage;

public class Bomb {
	private int x;
	private int y;
	private int width;
	private int height;
	private int index;
	private BufferedImage image;
	private BufferedImage[] images;
	private int time;

	public Bomb(int index) {
		this.index = 0;
		image = Main.beam1;
		images = new BufferedImage[] { Main.beam1, Main.beam2, Main.beam3, };
		width = image.getWidth() * 9;
		height = image.getHeight() * 9;
		x = Main.SCREENWIDTH / 2 - width / 2;
		y = Main.SCREENHEIGHT * index;
	}

	public void step() {
		time++;
		if (y < -height) {
			y = height*3;
		}
		y -= 10;
		if (time % 10 == 0) {
			index++;
		}
		image = images[index % 3];

	}

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

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
