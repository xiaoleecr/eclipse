package ShootingGame;

import java.awt.image.BufferedImage;

public class Background {
	private int x;
	private int y;
	private BufferedImage image;
	private int rollspeed;
	
	public Background(int stage,int imageIndex){
		switch(stage){
		case 1:
			image=Main.backgroundA;
			break;
		case 2:
			image=Main.backgroundB;
			break;
		case 3:
			image=Main.backgroundC;
			break;
		}
		switch(imageIndex){
		case 1:
			x=0;
			y=0;
			break;
		case 2:
			x=0;
			y=-512;
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

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public void setRollspeed(int speed){
		rollspeed=speed;
	}
	
	public void step(){
		if(y<Main.SCREENHEIGHT){
			y+=rollspeed;
		}else{
			y=-Main.SCREENHEIGHT;
		}
	}
}
