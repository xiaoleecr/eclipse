package dingwei;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class test {
	public static void main(String[] args) {
		JFrame window = new JFrame("定位图片");
		window.setResizable(false);
		window.setSize(512, 512);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setAlwaysOnTop(true);
		TestPanel tp = new TestPanel();
		window.setVisible(true);
		window.add(tp);
	}
}

class TestPanel extends JPanel {
	private static BufferedImage pic;
	private static int fx1 = 50;
	private static int fy1 = 50;
	private static int px1 = 50;
	private static int px2 = 68;
	private static int py1 = 145;
	private static int py2 = 161;
	private static int width = px2 - px1;
	private static int height = py2 - py1;

	static {
		try {
			pic = ImageIO.read(test.class.getResource("image" + File.separator
					+ "equips.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		g.drawImage(pic, fx1, fy1, fx1 + width, fy1 + height, px1, py1, px2,
				py2, null);
		System.out.println("WIDTH：" + width);
		System.out.println("HEIGHT:" + height);
	}
}