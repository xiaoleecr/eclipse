package CrazyBird;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FlyingBird {

	public static void main(String[] args) {
		JFrame window = new JFrame("FlyingBird");
		window.setSize(432, 664);
		window.setLocation(1366 / 2 - 432 / 2, 768 / 2 - 664 / 2);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BirdPanel panel = new BirdPanel();
		window.add(panel);

		new Thread(panel).start();// 动起来
		panel.addMouseListener(panel);
		window.setVisible(true);
	}
}

class BirdPanel extends JPanel implements Runnable, MouseListener {
	public BufferedImage[] birds = new BufferedImage[8];// 准备一个容器装载8张图片

	public BirdPanel() {// 在BirdPanel创建的时候先将图片加载

		try {
			birds[0] = ImageIO.read(new File("bird/0.png"));
			birds[1] = ImageIO.read(new File("bird/1.png"));
			birds[2] = ImageIO.read(new File("bird/2.png"));
			birds[3] = ImageIO.read(new File("bird/3.png"));
			birds[4] = ImageIO.read(new File("bird/4.png"));
			birds[5] = ImageIO.read(new File("bird/5.png"));
			birds[6] = ImageIO.read(new File("bird/6.png"));
			birds[7] = ImageIO.read(new File("bird/7.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	int k;
	int index = 0;// 第一张小鸟的图
	int x0 = 0;// 代表地面x坐标
	int i = 1;
	int y = 250;// 鸟的初始位置
	int v0 = 50;// 鸟向上的速度
	int v1 = 0, g = 0, t;// 重力加速度
	int xc1 = 432;// 让第一根管道贴近右侧窗口
	int xc2 = 687;// 加上第二根管子
	int yc1 = -470 + new Random().nextInt(259);
	int yc2 = -470 + new Random().nextInt(259);
	boolean isDead = false;// 判断死亡
	int yc = y + 24;// 鸟的y坐标点
	// v1=v1+a*t;y=v1*t+g*t
	boolean isStart = false;// 表示还没开始，true表示开始后

	public void paint(Graphics g) {

		g.drawImage(new ImageIcon("bird/bg.png").getImage(), 0, 0, null);// 贴背景

		if (isStart == false) {
			g.drawImage(new ImageIcon("bird/start.png").getImage(), 0, 0, null);// 贴Logo
		}
		g.drawImage(birds[index % 8], 100, y, null);// 贴鸟
		if (isStart == true) {
			g.drawImage(new ImageIcon("bird/column.png").getImage(), xc1, yc1,
					null);
			g.drawImage(new ImageIcon("bird/column.png").getImage(), xc2, yc2,
					null);
		}
		g.drawImage(new ImageIcon("bird/ground.png").getImage(), x0, 498, null);// 贴地面

		if (isDead == true) {
			g.drawImage(new ImageIcon("bird/gameover.png").getImage(), 0, 0,
					null);
		}
	}

	@Override
	public void run() {
		// if(isDead=false){
		while (isDead == false) {// 死循环
			x0 = x0 - 3;
			if (x0 <= -106) {
				x0 = 0;

			}
			index++;// 每次自增1

			if (isStart == true) {
				if (isDead == false) {
					g = 5;
					y = y + g;// 开始后鸟立即下降
					xc1 = xc1 - 3;// 管子向左移动
					xc2 = xc2 - 3;// 管子向左移动
					
					if (xc1 <= -78) {
						xc1 = 432;
						yc1 = -470 + new Random().nextInt(259);

					}

					if (xc2 <= -78) {
						xc2 = 432;
						yc2 = -470 + new Random().nextInt(259);

					}
					
					if (y + 48 > 498) {
						isDead = true;// 触地
						System.out.println(y);
					}
					System.out.println("y="+y);
					if (xc1 <= 100 + 56 && xc1 >= 156-78) {// 判断碰撞
						if (y <= yc1 + 523|| y+46 >= yc1 + 672) {
							isDead = true;
							k=yc1+527;
							
							System.out.println("yc1="+yc1);
							System.out.println("up"+k);
							k=yc1+672;
							System.out.println("down"+k);
						}
					}
					if (xc2 <= 100 + 56 && xc2 >= 156-78) {// 判断碰撞
						if (y <= yc2 + 523 || y+46>= yc2 + 672) {
							isDead = true;
						}
					}
				}
			}
		
			System.out.println(isDead);
			repaint();

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	// 处理点击事件
	public void mouseClicked(MouseEvent e) {
		y = y - v0;// 点击一次，鸟向上20的像素
		isStart = true;// 表示游戏开始
		if(isDead=false){
		repaint();// refresh
	}
	}	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
