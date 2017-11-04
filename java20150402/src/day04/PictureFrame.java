package day04;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

//带图的框
public class PictureFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame("捕鱼达人");

		frame.setIconImage(new ImageIcon("捕鱼图片/icon.jpg").getImage());// 添加图标
		frame.setBounds(1440 / 2 - 800 / 2, 900 / 2 - 500 / 2, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PicturePanel panel = new PicturePanel();
		frame.add(panel);
		frame.setVisible(true);
		new Thread(panel).start();
	}
}

class PicturePanel extends JPanel implements Runnable{
	public BufferedImage fish;// defined a picture to load all gestures of fish
	public BufferedImage[] fishes = new BufferedImage[4];// 储存截取的小图

	public PicturePanel() {// 加载某条鱼所有动作图片

		try {
			fish = ImageIO.read(new File("捕鱼图片/fish1.png"));
		} catch (IOException e) {
			e.printStackTrace();// 将整张图截取成几张小图
		}
		fishes[0] = fish.getSubimage(0, 0, 55, 37);// 按规格截取图片
		fishes[1] = fish.getSubimage(0, 37, 55, 37);
		fishes[2] = fish.getSubimage(0, 74, 55, 37);
		fishes[3] = fish.getSubimage(0, 111, 55, 37);
	}
	int x=0;
	int y=300;
	int i=0;//定义一个整数表示鱼游动图片的序号
	public void paint(Graphics g) {
		if (i>=4){
			i=0;}
		g.drawImage(new ImageIcon("捕鱼图片/bg_03.png").getImage(), 0, 0, null);
		g.drawImage(new ImageIcon("捕鱼图片/level.png").getImage(), 379, 410, null);
		
		g.drawImage(fishes[i++], x, y, null);
		
		
	
	

	}
	@Override
	public void run() {
		while(true){
			
			x=x+2;
			y=y-1;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 repaint();
			}
		
		 
	}}

	
