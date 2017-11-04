package day04;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Pool extends JPanel implements Runnable,MouseListener {// ��������
	public BufferedImage bg;// ����ͼ
	public Fish[] fishes;// �ܶ���
	public Net net;// ����һ������
	int j = 0;

	public Pool() {
		// ����������
		fishes = new Fish[1000];
		//Fish fish1 = new Fish();
		//Fish fish2 = new Fish();
		//Fish fish3 = new Fish();
		//Fish fish4 = new Fish();
		//Fish fish5 = new Fish();
		//fishes[0] = fish1;
		//fishes[1] = fish2;
		//fishes[2] = fish3;
		//fishes[3] = fish4;
		//fishes[4] = fish5;
		for(int k=0;k<100;k++){
			fishes[k]=new Fish();
		}
		net=new Net();//����һ������
	}

	public void paint(Graphics g) {

		try {
			bg = ImageIO.read(new File("����ͼƬ/bg_03.png"));
		} catch (IOException e) {
			e.printStackTrace();

		}

		g.drawImage(bg, 0, 0, null);// ���ر���ͼ
		Fish fish = new Fish();
		for (int i = 0; i < 100; i++) {
			if(fishes[i].isCatch==false)
			g.drawImage(fishes[i].fishImages[j], fishes[i].x,
							fishes[i].y, null);
			
		}
        if(net!=null);//���������ھͻ����� 
		g.drawImage(net.netImage,net.a,net.b,null);//��������ͼƬ
		// g.drawImage(fish.fishImages[i++], fish.x, fish.y, null);
	}

	public static void main(String[] args) {
		JFrame window = new JFrame("�������");
		window.setBounds(1440 / 2 - 800 / 2, 900 / 2 - 500 / 2, 800, 500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Pool pool = new Pool();
		window.add(pool);
		new Thread(pool).start();
		pool.addMouseListener(pool);
		window.setVisible(true);

	}

	@Override
	public void run() {
		while (true) {// ��ѭ����ͣˢ�½���
			try {
				Thread.sleep(new Random().nextInt(50)+3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			j++;
			if (j >= 4) {
				j = 0;
			}
			for (int i = 0; i < 100; i++) {
				fishes[i].x = fishes[i].x + fishes[i].speed;

				if (fishes[i].x >= 800) {
					fishes[i].x = -78;
				}
				if(count%5==0)
				if(count==100){//���������ǩ�ĳ�false
					for(int i1=0;i1<100;i1++){
						fishes[i1].isCatch=false;}
					
				}
			}
			repaint();
		}
	}
	int count=0;//������������
	@Override
	public void mouseClicked(MouseEvent e) {//����¼�
		net=new Net();
		net.a=e.getX()-163/2;//��꽫���������X���긴�Ƹ�a
		net.b=e.getY()-155/2;//��꽫���������y���긴�Ƹ�b
		for(int i=0;i<100;i++){
			Fish fish=fishes[i];
			int x0=fish.x;
			int y0=fish.y;
			if(x0>=net.a&&x0<=net.a+163&&y0>=net.b&&y0<=net.b+155){
				fish.isCatch=true;//��ʾ�㱻�����޸����״̬
				count++;
				
			}
		}
		//����ȫ������������������ʾ
		repaint();
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