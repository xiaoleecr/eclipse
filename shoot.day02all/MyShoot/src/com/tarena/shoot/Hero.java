package com.tarena.shoot;
import java.awt.image.BufferedImage;

/** Ӣ�ۻ�: �Ƿ����� */
public class Hero extends FlyingObject {
	private int life; //��
	private int doubleFire; //����ֵ
	private BufferedImage[] images = {}; //����ͼƬ
	private int index; //Э��ͼƬ�л�

	/** Hero���췽�� */
	public Hero(){
		image = ShootGame.hero0; //ͼƬ
		width = image.getWidth();   //��
		height = image.getHeight(); //��
		x = 150; //Ӣ�ۻ�x����̶�
		y = 400; //Ӣ�ۻ�y����̶�
		life = 3; //����Ϊ3
		doubleFire = 0; //����ֵΪ0(��������)
		images = new BufferedImage[]{ShootGame.hero0,ShootGame.hero1}; //����ͼƬ(heor0��hero1)
		index = 0; //Э���л����Ϊ0
	}
	
	/** ��дstep() */
	public void step(){ //10������һ��
		image = images[index++/10%images.length];
		
		/*
		index++;
		int a=index/10;
		int b=a%2;
		image = images[b];
		*/
		/*
		 * 10M  index=1  a=0 b=0
		 * 20M  index=2  a=0 b=0
		 * 30M  index=3  a=0 b=0
		 * ...
		 * 100M index=10 a=1 b=1
		 * 110M index=11 a=1 b=1
		 * ...
		 * 200M index=20 a=2 b=0
		 * 210M index=21 a=2 b=0
		 * ...
		 * 300M index=30 a=3 b=1
		 * ...
		 * 400M index=40 a=4 b=0
		*/
	}

	/** �����ӵ�(�����ӵ�����) */
	public Bullet[] shoot(){
		int xStep = this.width/4; //1/4Ӣ�ۻ��Ŀ�
		if(doubleFire > 0){ //˫������
			Bullet[] bullets = new Bullet[2]; //2���ӵ�
			bullets[0] = new Bullet(this.x+1*xStep,this.y-20); //x:Ӣ�ۻ�x+1/4Ӣ�ۻ��Ŀ� y:Ӣ�ۻ�y-20
			bullets[1] = new Bullet(this.x+3*xStep,this.y-20); //x:Ӣ�ۻ�x+3/4Ӣ�ۻ��Ŀ� y:Ӣ�ۻ�y-20
			doubleFire -= 2; //����һ��˫������ʱ����ֵ��2
			return bullets;  //����
		}else{  //��������
			Bullet[] bullets = new Bullet[1]; //1���ӵ�
			bullets[0] = new Bullet(this.x+2*xStep,this.y-20); //x:Ӣ�ۻ�x+2/4Ӣ�ۻ��Ŀ� y:Ӣ�ۻ�y-20
			return bullets;  //����
		}
	}
	
	/** Ӣ�ۻ��ƶ� x:����x���� y:����y���� */
	public void moveTo(int x,int y){
		this.x = x - this.width/2; //Ӣ�ۻ�x:����x-1/2Ӣ�ۻ��Ŀ�
		this.y = y - this.height/2;//Ӣ�ۻ�y:����y-1/2Ӣ�ۻ��ĸ�
	}
	
	/** ���� */
	public void addLife(){
		life++; //������1
	}
	/** ������ֵ */
	public void addDoubleFire(){
		doubleFire += 40; //����ֵ��40
	}
	
}




