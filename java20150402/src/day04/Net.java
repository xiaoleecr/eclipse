package day04;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Net {
	public BufferedImage netImage;//����ͼƬ
	public int a;//���ĺ�����
	public int b;//����������
	public Net(){//���������Ը�ֵ
		
		try {
			netImage=ImageIO.read(new File("����ͼƬ/web5.png"));//��������ͼƬ
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
