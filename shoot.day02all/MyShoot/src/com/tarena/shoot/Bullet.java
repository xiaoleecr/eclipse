package com.tarena.shoot;
/** �ӵ�: �Ƿ����� */
public class Bullet extends FlyingObject {
	private int speed = 3; //�߲��Ĳ���
	
	/** Bullet���췽�� x��yΪ�ӵ���xy���꣬����Ӣ�ۻ��� */
	public Bullet(int x,int y){
		image = ShootGame.bullet; //ͼƬ
		width = image.getWidth();   //��
		height = image.getHeight(); //��
		this.x = x;
		this.y = y;
	}
	
	/** ��дstep() */
	public void step(){
		y -= speed; //y��
	}
	
}
