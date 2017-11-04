package com.tarena.shoot;
import java.awt.image.BufferedImage;

/** 英雄机: 是飞行物 */
public class Hero extends FlyingObject {
	private int life; //命
	private int doubleFire; //火力值
	private BufferedImage[] images = {}; //两张图片
	private int index; //协助图片切换

	/** Hero构造方法 */
	public Hero(){
		image = ShootGame.hero0; //图片
		width = image.getWidth();   //宽
		height = image.getHeight(); //高
		x = 150; //英雄机x坐标固定
		y = 400; //英雄机y坐标固定
		life = 3; //命数为3
		doubleFire = 0; //火力值为0(单倍火力)
		images = new BufferedImage[]{ShootGame.hero0,ShootGame.hero1}; //两张图片(heor0和hero1)
		index = 0; //协助切换设计为0
	}
	
	/** 重写step() */
	public void step(){ //10毫秒走一次
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

	/** 发射子弹(创建子弹对象) */
	public Bullet[] shoot(){
		int xStep = this.width/4; //1/4英雄机的宽
		if(doubleFire > 0){ //双倍火力
			Bullet[] bullets = new Bullet[2]; //2发子弹
			bullets[0] = new Bullet(this.x+1*xStep,this.y-20); //x:英雄机x+1/4英雄机的宽 y:英雄机y-20
			bullets[1] = new Bullet(this.x+3*xStep,this.y-20); //x:英雄机x+3/4英雄机的宽 y:英雄机y-20
			doubleFire -= 2; //发射一次双倍火力时火力值减2
			return bullets;  //返回
		}else{  //单倍火力
			Bullet[] bullets = new Bullet[1]; //1发子弹
			bullets[0] = new Bullet(this.x+2*xStep,this.y-20); //x:英雄机x+2/4英雄机的宽 y:英雄机y-20
			return bullets;  //返回
		}
	}
	
	/** 英雄机移动 x:鼠标的x坐标 y:鼠标的y坐标 */
	public void moveTo(int x,int y){
		this.x = x - this.width/2; //英雄机x:鼠标的x-1/2英雄机的宽
		this.y = y - this.height/2;//英雄机y:鼠标的y-1/2英雄机的高
	}
	
	/** 增命 */
	public void addLife(){
		life++; //命数增1
	}
	/** 增火力值 */
	public void addDoubleFire(){
		doubleFire += 40; //火力值增40
	}
	
}




