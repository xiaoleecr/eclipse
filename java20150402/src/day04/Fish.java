package day04;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

//������
public class Fish {
	public BufferedImage fish;// ������ͼƬ
	public BufferedImage[] fishImages = new BufferedImage[4];// �洢��ȡ��ͼƬ
	public int x;// ��ĺ�����
	public int y;// ���������
	public int speed;// �ζ��ٶ�
	public boolean isCatch=false;//��ʾ�Ƿ�ץ

	public Fish() {// ���������Ը�ֵ
		try {
			fish = ImageIO.read(new File("����ͼƬ/fish2.png"));
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
