package ShootingGame;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Asteroids {
	private int index;
	private int x;
	private int y;
	private int width;
	private int height;
	private BufferedImage image;
	private ImageInfo[] imgis;
	private int time;
	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public Asteroids() {
		index = 0;
		image = Main.asteroids;
		imgis = Main.asteroidsimg;
		width = imgis[0].getImagewidth();
		height = imgis[0].getImageheight();
		y = -imgis[0].getImageheight();
		x1 = imgis[0].getX1();
		y1 = imgis[0].getY1();
		x2 = imgis[0].getX2();
		y2 = imgis[0].getY2();
		Random random = new Random();
		x = random.nextInt(Main.SCREENWIDTH - width);
	}
	
	public boolean outOfBounds(){
		return y>Main.SCREENHEIGHT;
	}

	public void step() {
		time++;
		if (time % 15 == 0) {
			index++;
		}
		y += 2;
		switch (index % 8) {
		case 0:
			width = imgis[0].getImagewidth();
			height = imgis[0].getImageheight();
			x1 = imgis[0].getX1();
			y1 = imgis[0].getY1();
			x2 = imgis[0].getX2();
			y2 = imgis[0].getY2();
			break;
		case 1:
			width = imgis[1].getImagewidth();
			height = imgis[1].getImageheight();
			x1 = imgis[1].getX1();
			y1 = imgis[1].getY1();
			x2 = imgis[1].getX2();
			y2 = imgis[1].getY2();
			break;
		case 2:
			width = imgis[2].getImagewidth();
			height = imgis[2].getImageheight();
			x1 = imgis[2].getX1();
			y1 = imgis[2].getY1();
			x2 = imgis[2].getX2();
			y2 = imgis[2].getY2();
			break;
		case 3:
			width = imgis[3].getImagewidth();
			height = imgis[3].getImageheight();
			x1 = imgis[3].getX1();
			y1 = imgis[3].getY1();
			x2 = imgis[3].getX2();
			y2 = imgis[3].getY2();
			break;
		case 4:
			width = imgis[4].getImagewidth();
			height = imgis[4].getImageheight();
			x1 = imgis[4].getX1();
			y1 = imgis[4].getY1();
			x2 = imgis[4].getX2();
			y2 = imgis[4].getY2();
			break;
		case 5:
			width = imgis[5].getImagewidth();
			height = imgis[5].getImageheight();
			x1 = imgis[5].getX1();
			y1 = imgis[5].getY1();
			x2 = imgis[5].getX2();
			y2 = imgis[5].getY2();
			break;
		case 6:
			width = imgis[6].getImagewidth();
			height = imgis[6].getImageheight();
			x1 = imgis[6].getX1();
			y1 = imgis[6].getY1();
			x2 = imgis[6].getX2();
			y2 = imgis[6].getY2();
			break;
		case 7:
			width = imgis[7].getImagewidth();
			height = imgis[7].getImageheight();
			x1 = imgis[7].getX1();
			y1 = imgis[7].getY1();
			x2 = imgis[7].getX2();
			y2 = imgis[7].getY2();
			break;
		}
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

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

}
