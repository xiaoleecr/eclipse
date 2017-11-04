package day04;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Net {
	public BufferedImage netImage;//网的图片
	public int a;//网的横坐标
	public int b;//网的纵坐标
	public Net(){//给渔网属性赋值
		
		try {
			netImage=ImageIO.read(new File("捕鱼图片/web5.png"));//载入网的图片
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
