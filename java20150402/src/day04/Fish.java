package day04;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

//鱼类型
public class Fish {
	public BufferedImage fish;// 整张鱼图片
	public BufferedImage[] fishImages = new BufferedImage[4];// 存储截取的图片
	public int x;// 鱼的横坐标
	public int y;// 鱼的纵坐标
	public int speed;// 游动速度
	public boolean isCatch=false;//表示是否被抓

	public Fish() {// 给以上属性赋值
		try {
			fish = ImageIO.read(new File("捕鱼图片/fish2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		fishImages[0] = fish.getSubimage(0, 0, 78, 64);
		fishImages[1] = fish.getSubimage(0, 64, 78, 64);
		fishImages[2] = fish.getSubimage(0, 128, 78, 64);
		fishImages[3] = fish.getSubimage(0, 192, 78, 64);
		x=new Random().nextInt(799)+1;
		y=new Random().nextInt(499)+1;
		speed=new Random().nextInt(5)+1;
	}

}
