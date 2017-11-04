package day04;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

//��ͼ�Ŀ�
public class PictureFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame("�������");

		frame.setIconImage(new ImageIcon("����ͼƬ/icon.jpg").getImage());// ���ͼ��
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
	public BufferedImage[] fishes = new BufferedImage[4];// �����ȡ��Сͼ

	public PicturePanel() {// ����ĳ�������ж���ͼƬ

		try {
			fish = ImageIO.read(new File("����ͼƬ/fish1.png"));
		} catch (IOException e) {
			e.printStackTrace();// ������ͼ��ȡ�ɼ���Сͼ
		}
		fishes[0] = fish.getSubimage(0, 0, 55, 37);// ������ȡͼƬ
		fishes[1] = fish.getSubimage(0, 37, 55, 37);
		fishes[2] = fish.getSubimage(0, 74, 55, 37);
		fishes[3] = fish.getSubimage(0, 111, 55, 37);
	}
	int x=0;
	int y=300;
	int i=0;//����һ��������ʾ���ζ�ͼƬ�����
	public void paint(Graphics g) {
		if (i>=4){
			i=0;}
		g.drawImage(new ImageIcon("����ͼƬ/bg_03.png").getImage(), 0, 0, null);
		g.drawImage(new ImageIcon("����ͼƬ/level.png").getImage(), 379, 410, null);
		
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

	
