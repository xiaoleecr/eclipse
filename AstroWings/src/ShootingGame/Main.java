package ShootingGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
	// 以下图片变量其中大部分其实是引用的同一对象，为了区分所以单独列出，但是在赋值时指向的都是同一对象。

	// 游戏窗口的高度和宽度
	private static JFrame frame;
	public static final int SCREENWIDTH = 512;// 窗口宽度
	public static final int SCREENHEIGHT = 512;// 窗口高度

	// 键盘控制时方向键状态（按下时为TRUE）
	private boolean leftKey = false;// 左键
	private boolean rightKey = false;// 右键
	private boolean upKey = false;// 上键
	private boolean downKey = false;// 下键

	// 关卡
	private static int stagestate;// 关卡状态，记录当前关卡
	public static final int STAGE_A = 1;// 第一关
	public static final int STAGE_B = 2;// 第二关
	public static final int STAGE_C = 3;// 第三关

	// 游戏进行状态（整个游戏流程皆由此控制）
	private int gamestateold;// 之前的游戏状态，用于记录英雄机击毁后，复活后重新进入正确的流程
	private int gamestate;// 当前游戏状态
	private static final int START = 0;// 开始界面
	private static final int START_ANIME = 1;// 游戏开场动画
	private static final int REBORN = 2;// 复活
	private static final int RUNNING = 3;// 正常型状态
	private static final int BOSS_READY = 4;// BOSS战前准备（敌机逐步消失）
	private static final int BOSS_WARNING = 5;// BOSS战前预警
	private static final int BOSS_INCOME = 6;// BOSS登场
	private static final int BOSS = 7;// BOSS战
	private static final int BOSS_OVER = 8;// BOSS战结束（BOSS退场）
	private static final int STAGE_CLEAR = 9;// 过关提示
	private static final int LOADING = 10;// 加载页面（用于参数重置和缓冲节奏（避免关卡之间切换太快，显得突兀））
	private static final int GAME_OVER = 11;// 英雄机彻底阵亡（生命为0）
	private static final int END = 12;// 游戏通关
	private static final int TEST = 100;// 测试用（然并卵）

	// 其他变量
	private static int bombnum;// 炸弹数量
	private boolean boom = false;// 是否大招
	private int boomtime;// 大招冷却时间
	private boolean canboom = true;// 是否允许放大招
	private int score = 0;// 游戏得分
	private Timer timer;// 计时器
	private int intervel = 1000 / 100;// 计时间隔（10毫秒一次）

	// 时间变量（此处用于各个游戏阶段的时间长度控制）
	private int startmenutimes;// 开始菜单计时
	private int startanimetimes;// 开场动画计时
	private int godtimes;// 英雄机重生无敌时间
	private int warningtimes;// 警告时间
	private int playtimes;// 关卡时间
	private int bossovertimes;// BOSSOVER时间
	private int stagecleartimes;// 过关状态计时
	private int gameovertimes;// 游戏结束时间
	private int bgspeed = 1;// 背景图速度
	private int loadingtimes;// 加载时间

	// 英雄机的飞行姿态
	private int mousex1 = -1;// 此处两个变量用于判断鼠标左右移动状态
	private int mousex2 = -1;
	private int heroFlyingState;// 英雄机的飞行状态
	private static final int HERO_NORMAL = 0;// 平飞
	private static final int HERO_LEFT1 = -1;// 左偏1
	private static final int HERO_LEFT2 = -2;// 左偏2
	private static final int HERO_RIGHT1 = 1;// 右偏1
	private static final int HERO_RIGHT2 = 2;// 右偏2

	// 图片参数属性类（此处用于读取各个图片的参数，因为素材图片每个单位并不是单独图片，需要自行定位分割所以采用了，将参数存于XML文件中统一读取的方式）
	// 英雄机
	public static ImageInfo hero1img = new ImageInfo("hero1");// 平飞
	public static ImageInfo hero1l1img = new ImageInfo("hero1l1");// 左1
	public static ImageInfo hero1l2img = new ImageInfo("hero1l2");// 左2
	public static ImageInfo hero1r1img = new ImageInfo("hero1r1");// 右1
	public static ImageInfo hero1r2img = new ImageInfo("hero1r2");// 右2

	// 僚机
	public static ImageInfo[] subunitimg = subunitImageInfo();

	// 敌人
	public static ImageInfo boss1img = new ImageInfo("boss1");// 第一关BOSS
	public static ImageInfo boss2img = new ImageInfo("boss2");// 第二关BOSS
	public static ImageInfo boss3img = new ImageInfo("boss3");// 第三关BOSS

	// 敌机部分(此部分中所有elite为原定加入的精英机，因时间不够所以放弃了)
	// 第一关
	public static ImageInfo elite_A1img = new ImageInfo("elite_A1");
	public static ImageInfo elite_A2img = new ImageInfo("elite_A2");
	public static ImageInfo soldier_A1img = new ImageInfo("soldier_A1");
	public static ImageInfo soldier_A2img = new ImageInfo("soldier_A2");
	public static ImageInfo soldier_A3img = new ImageInfo("soldier_A3");
	// 第二关
	public static ImageInfo elite_B1img = new ImageInfo("elite_B1");
	public static ImageInfo elite_B2img = new ImageInfo("elite_B2");
	public static ImageInfo soldier_B1img = new ImageInfo("soldier_B1");
	public static ImageInfo soldier_B2img = new ImageInfo("soldier_B2");
	public static ImageInfo soldier_B3img = new ImageInfo("soldier_B3");

	// 第三关
	public static ImageInfo elite_C1img = new ImageInfo("elite_C1");
	public static ImageInfo elite_C2img = new ImageInfo("elite_C2");
	public static ImageInfo soldier_C1img = new ImageInfo("soldier_C1");
	public static ImageInfo soldier_C2img = new ImageInfo("soldier_C2");
	public static ImageInfo soldier_C3img = new ImageInfo("soldier_C3");

	// 子弹
	public static ImageInfo bulletA1img = new ImageInfo("bulletA1");
	public static ImageInfo bulletA2img = new ImageInfo("bulletA2");
	public static ImageInfo bulletB1img = new ImageInfo("bulletB1");
	public static ImageInfo bulletB2img = new ImageInfo("bulletB2");
	public static ImageInfo bulletC1img = new ImageInfo("bulletC1");
	public static ImageInfo bulletC2img = new ImageInfo("bulletC2");
	public static ImageInfo bulletC3img = new ImageInfo("bulletC3");
	public static ImageInfo bulletC4img = new ImageInfo("bulletC4");
	public static ImageInfo bulletC5img = new ImageInfo("bulletC5");
	public static ImageInfo bulletD1img = new ImageInfo("bulletD1");
	public static ImageInfo bulletD2img = new ImageInfo("bulletD2");
	public static ImageInfo bulletD3img = new ImageInfo("bulletD3");
	public static ImageInfo missileimg = new ImageInfo("missile");
	public static ImageInfo waveimg = new ImageInfo("wave");

	// 道具
	public static ImageInfo itemAimg = new ImageInfo("itemA");
	public static ImageInfo itemBimg = new ImageInfo("itemB");
	public static ImageInfo itemCimg = new ImageInfo("itemC");
	public static ImageInfo itemDimg = new ImageInfo("itemD");
	public static ImageInfo itemEimg = new ImageInfo("itemE");

	// 陨石
	public static ImageInfo[] asteroidsimg = asteroidsImageInfo();

	// 爆炸效果
	public static ImageInfo explosion0 = new ImageInfo("explosion0");
	public static ImageInfo explosion1 = new ImageInfo("explosion1");
	public static ImageInfo explosion2 = new ImageInfo("explosion2");
	public static ImageInfo explosion3 = new ImageInfo("explosion3");
	public static ImageInfo explosion4 = new ImageInfo("explosion4");
	public static ImageInfo explosion5 = new ImageInfo("explosion5");
	public static ImageInfo explosion6 = new ImageInfo("explosion6");
	public static ImageInfo explosion7 = new ImageInfo("explosion7");
	public static ImageInfo explosion8 = new ImageInfo("explosion8");
	public static ImageInfo explosion9 = new ImageInfo("explosion9");
	public static ImageInfo explosion10 = new ImageInfo("explosion10");
	public static ImageInfo explosion11 = new ImageInfo("explosion11");
	public static ImageInfo explosion12 = new ImageInfo("explosion12");
	public static ImageInfo explosion13 = new ImageInfo("explosion13");
	public static ImageInfo explosion14 = new ImageInfo("explosion14");

	// 图片分类(大部分引用的都是同一个对象，只不过为了区分所以将其显式化(因为是静态所以不存在资源浪费))
	// 英雄机
	public static BufferedImage hero1;
	public static BufferedImage hero1l1;
	public static BufferedImage hero1l2;
	public static BufferedImage hero1r1;
	public static BufferedImage hero1r2;

	// 僚机
	public static BufferedImage subunit;

	// 第一关
	public static BufferedImage elite_A1;
	public static BufferedImage elite_A2;
	public static BufferedImage soldier_A1;
	public static BufferedImage soldier_A2;
	public static BufferedImage soldier_A3;

	// 第二关
	public static BufferedImage elite_B1;
	public static BufferedImage elite_B2;
	public static BufferedImage soldier_B1;
	public static BufferedImage soldier_B2;
	public static BufferedImage soldier_B3;

	// 第三关
	public static BufferedImage elite_C1;
	public static BufferedImage elite_C2;
	public static BufferedImage soldier_C1;
	public static BufferedImage soldier_C2;
	public static BufferedImage soldier_C3;

	// BOSS
	public static BufferedImage boss1;
	public static BufferedImage boss2;
	public static BufferedImage boss3;
	// 子弹
	public static BufferedImage bulletA1;
	public static BufferedImage bulletA2;
	public static BufferedImage bulletB1;
	public static BufferedImage bulletB2;
	public static BufferedImage bulletC1;
	public static BufferedImage bulletC2;
	public static BufferedImage bulletC3;
	public static BufferedImage bulletC4;
	public static BufferedImage bulletC5;
	public static BufferedImage bulletD1;
	public static BufferedImage bulletD2;
	public static BufferedImage bulletD3;
	public static BufferedImage missile;
	public static BufferedImage wave;

	// 道具
	public static BufferedImage item;

	// 爆炸效果
	public static BufferedImage explosion;

	// 大招
	public static BufferedImage beam1;
	public static BufferedImage beam2;
	public static BufferedImage beam3;

	// 陨石
	public static BufferedImage asteroids;

	// 关卡背景
	public static BufferedImage backgroundA;
	public static BufferedImage backgroundB;
	public static BufferedImage backgroundC;

	// 界面
	public static BufferedImage menu;// 开始菜单背景
	public static BufferedImage logo;// LOGO
	public static BufferedImage loading;// 读取图标
	public static BufferedImage loading_bg;// 读取时的背景图
	public static BufferedImage go;
	public static BufferedImage ready;
	public static BufferedImage end;

	// 实例化对象
	private FlyingObject[] flyings = {};// 敌机组
	private Bullet[] bulletsEnemy = {};// 敌机子弹组
	private Hero hero = new Hero();// 英雄机
	private Bullet[] bulletsHero = {};// 英雄机子弹
	private Subunit[] subunits = {};// 僚机组
	private Bullet[] bulletsSub = {};// 僚机子弹组
	private Boss boss = new Boss(stagestate);// BOSS机
	private Bullet[] bulletsBoss = {};// BOSS机子弹
	private Item[] items = {};// 道具组
	private Background bg1 = new Background(stagestate, 1);// 背景图案1（运动的背景图案由两张相同的循环移动的图案组成）
	private Background bg2 = new Background(stagestate, 2);// 背景图案2
	private Explosion[] booms = {};// 爆炸效果组
	private Asteroids[] atds = {};// 陨石组
	private Bomb[] bomb = { new Bomb(1), new Bomb(2), new Bomb(3), new Bomb(4) };

	// 静态加载组，将各个单位的图片加载
	static {
		try {
			hero1 = hero1l1 = hero1l2 = hero1r1 = hero1r2 = ImageIO
					.read(Main.class.getResource("image" + File.separator
							+ "hero.png"));

			subunit = ImageIO.read(Main.class.getResource("image"
					+ File.separator + "subunit.png"));

			boss1 = boss2 = boss3 = ImageIO.read(Main.class.getResource("image"
					+ File.separator + "boss.png"));
			elite_A1 = elite_A2 = soldier_A1 = soldier_A2 = soldier_A3 = ImageIO
					.read(Main.class.getResource("image" + File.separator
							+ "EnemyStageA.png"));
			elite_B1 = elite_B2 = soldier_B1 = soldier_B2 = soldier_B3 = ImageIO
					.read(Main.class.getResource("image" + File.separator
							+ "EnemyStageB.png"));
			elite_C1 = elite_C2 = soldier_C1 = soldier_C2 = soldier_C3 = ImageIO
					.read(Main.class.getResource("image" + File.separator
							+ "EnemyStageC.png"));
			bulletA1 = bulletA2 = bulletB1 = bulletB2 = missile = wave = ImageIO
					.read(Main.class.getResource("image" + File.separator
							+ "bulletsAB.png"));
			bulletC1 = bulletC2 = bulletC3 = bulletC4 = bulletC5 = bulletD1 = bulletD2 = bulletD3 = ImageIO
					.read(Main.class.getResource("image" + File.separator
							+ "bulletsCD.png"));
			item = ImageIO.read(Main.class.getResource("image" + File.separator
					+ "Item.png"));
			beam1 = ImageIO.read(Main.class.getResource("image"
					+ File.separator + "beam1.png"));
			beam2 = ImageIO.read(Main.class.getResource("image"
					+ File.separator + "beam2.png"));
			beam3 = ImageIO.read(Main.class.getResource("image"
					+ File.separator + "beam3.png"));
			asteroids = ImageIO.read(Main.class.getResource("image"
					+ File.separator + "asteroids.png"));
			backgroundA = ImageIO.read(Main.class.getResource("image"
					+ File.separator + "stageA.png"));
			backgroundB = ImageIO.read(Main.class.getResource("image"
					+ File.separator + "stageB.png"));
			backgroundC = ImageIO.read(Main.class.getResource("image"
					+ File.separator + "stageC.png"));
			menu = ImageIO.read(Main.class.getResource("image" + File.separator
					+ "main.png"));
			logo = ImageIO.read(Main.class.getResource("image" + File.separator
					+ "logo.png"));
			loading = ImageIO.read(Main.class.getResource("image"
					+ File.separator + "loading.png"));
			loading_bg = ImageIO.read(Main.class.getResource("image"
					+ File.separator + "loading_bg.png"));
			go = ImageIO.read(Main.class.getResource("image" + File.separator
					+ "go.png"));
			ready = ImageIO.read(Main.class.getResource("image"
					+ File.separator + "ready.png"));
			explosion = ImageIO.read(Main.class.getResource("image"
					+ File.separator + "explosion.png"));
			end = ImageIO.read(Main.class.getResource("image" + File.separator
					+ "end.png"));
			stagestate = STAGE_A;
			bombnum = 3;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 总绘（所有单位全部在此绘制）
	 */
	public void paint(Graphics g) {
		switch (gamestate) {
		case START:
			paintStartMenu(g);
			break;
		case START_ANIME:
			paintBackground(g);
			paintAsteroids(g);
			if (boom) {
				paintBomb(g);
			}
			paintHero(g);
			paintSubunit(g);
			paintStartAnime(g);
			break;
		case REBORN:
			paintBackground(g);
			paintAsteroids(g);
			if (godtimes % 25 < 13) {
				paintHero(g);
			}
			if (gamestateold == BOSS) {
				paintBoss(g);
				paintBulletsBoss(g);
			} else {
				paintFlyingObjects(g);
				paintBulletsEnemy(g);
				paintItem(g);
			}
			paintSubunit(g);
			paintBulletsSubunit(g);
			paintBulletsHero(g);
			if (boom) {
				paintBomb(g);
			}
			paintScore(g);
			paintExplosion(g);
			break;
		case RUNNING:
			paintBackground(g);
			paintAsteroids(g);
			paintHero(g);
			paintBulletsHero(g);
			paintSubunit(g);
			paintBulletsSubunit(g);
			paintFlyingObjects(g);
			paintBulletsEnemy(g);
			if (boom) {
				paintBomb(g);
			}
			paintItem(g);
			paintScore(g);
			paintExplosion(g);
			break;
		case BOSS_READY:
			paintBackground(g);
			paintAsteroids(g);
			paintHero(g);
			paintBulletsHero(g);
			paintSubunit(g);
			paintBulletsSubunit(g);
			paintFlyingObjects(g);
			paintBulletsEnemy(g);
			if (boom) {
				paintBomb(g);
			}
			paintItem(g);
			paintScore(g);
			paintExplosion(g);
			break;
		case BOSS_WARNING:
			paintBackground(g);
			paintAsteroids(g);
			paintHero(g);
			paintBulletsHero(g);
			paintSubunit(g);
			paintBulletsSubunit(g);
			if (boom) {
				paintBomb(g);
			}
			paintScore(g);
			paintBossWarning(g);
			paintExplosion(g);
			break;
		case BOSS_INCOME:
			paintBackground(g);
			paintAsteroids(g);
			paintHero(g);
			paintBulletsHero(g);
			paintSubunit(g);
			paintBulletsSubunit(g);
			if (boom) {
				paintBomb(g);
			}
			paintScore(g);
			paintBoss(g);
			break;
		case BOSS:
			paintBackground(g);
			paintAsteroids(g);
			paintHero(g);
			paintBulletsHero(g);
			paintSubunit(g);
			paintBulletsSubunit(g);
			paintBoss(g);
			paintBulletsBoss(g);
			if (boom) {
				paintBomb(g);
			}
			paintScore(g);
			break;
		case BOSS_OVER:
			paintBackground(g);
			paintAsteroids(g);
			paintHero(g);
			paintBulletsHero(g);
			paintSubunit(g);
			paintBulletsSubunit(g);
			paintScore(g);
			if (bossovertimes % 50 < 25) {
				paintBoss(g);
			}
			paintBulletsBoss(g);
			if (boom) {
				paintBomb(g);
			}
			paintExplosion(g);
			break;
		case STAGE_CLEAR:
			paintBackground(g);
			paintAsteroids(g);
			paintHero(g);
			paintBulletsHero(g);
			paintSubunit(g);
			paintBulletsSubunit(g);
			paintStageClear(g);
			break;
		case LOADING:
			paintLoading(g);
			break;
		case GAME_OVER:
			paintBackground(g);

			if (bulletsHero.length > 0) {
				paintBulletsHero(g);
			}

			if (gamestateold == BOSS) {
				paintBoss(g);
				paintBulletsBoss(g);
			} else {
				paintFlyingObjects(g);
				paintItem(g);
			}
			paintGameOver(g);
			break;
		case END:
			paintEnd(g);
			break;
		}
	}

	/**
	 * 画英雄机（各种姿态） 根据英雄机的姿态绘制不同图案
	 * 
	 * @param g
	 */
	private void paintHero(Graphics g) {
		switch (heroFlyingState) {
		case HERO_NORMAL:
			g.drawImage(hero1, hero.getX(), hero.getY(), hero.getX()
					+ hero1img.getImagewidth(), hero.getY()
					+ hero1img.getImageheight(), hero1img.getX1(), hero1img
					.getY1(), hero1img.getX2(), hero1img.getY2(), null);
			break;
		case HERO_LEFT1:
			g.drawImage(hero1l1, hero.getX(), hero.getY(), hero.getX()
					+ hero1l1img.getImagewidth(), hero.getY()
					+ hero1l1img.getImageheight(), hero1l1img.getX1(),
					hero1l1img.getY1(), hero1l1img.getX2(), hero1l1img.getY2(),
					null);
			break;
		case HERO_LEFT2:
			g.drawImage(hero1l2, hero.getX(), hero.getY(), hero.getX()
					+ hero1l2img.getImagewidth(), hero.getY()
					+ hero1l2img.getImageheight(), hero1l2img.getX1(),
					hero1l2img.getY1(), hero1l2img.getX2(), hero1l2img.getY2(),
					null);
			break;
		case HERO_RIGHT1:
			g.drawImage(hero1r1, hero.getX(), hero.getY(), hero.getX()
					+ hero1r1img.getImagewidth(), hero.getY()
					+ hero1r1img.getImageheight(), hero1r1img.getX1(),
					hero1r1img.getY1(), hero1r1img.getX2(), hero1r1img.getY2(),
					null);
			break;
		case HERO_RIGHT2:
			g.drawImage(hero1r2, hero.getX(), hero.getY(), hero.getX()
					+ hero1r2img.getImagewidth(), hero.getY()
					+ hero1r2img.getImageheight(), hero1r2img.getX1(),
					hero1r2img.getY1(), hero1r2img.getX2(), hero1r2img.getY2(),
					null);
			break;
		}
	}

	/**
	 * 画英雄机子弹 循环遍历整个英雄机子弹组，然后更具返回的类型，判断绘制的是那种图案
	 * 
	 * @param g
	 */
	private void paintBulletsHero(Graphics g) {
		for (int i = 0; i < bulletsHero.length; i++) {
			Bullet b = bulletsHero[i];
			switch (hero.getFireType()) {
			case 1:
				g.drawImage(bulletA1, b.getX(), b.getY(), b.getX()
						+ bulletA1img.getImagewidth(), b.getY()
						+ bulletA1img.getImageheight(), bulletA1img.getX1(),
						bulletA1img.getY1(), bulletA1img.getX2(), bulletA1img
								.getY2(), null);
				break;
			case 2:
				g.drawImage(bulletB2, b.getX(), b.getY(), b.getX()
						+ bulletB2img.getImagewidth(), b.getY()
						+ bulletB2img.getImageheight(), bulletB2img.getX1(),
						bulletB2img.getY1(), bulletB2img.getX2(), bulletB2img
								.getY2(), null);
				break;
			}
		}
	}

	/**
	 * 画僚机
	 * 
	 * @param g
	 */
	private void paintSubunit(Graphics g) {
		for (int i = 0; i < subunits.length; i++) {
			Subunit sbu = subunits[i];
			g.drawImage(sbu.getImage(), sbu.getX(), sbu.getY(), sbu.getX()
					+ sbu.getWidth(), sbu.getY() + sbu.getHeight(),
					sbu.getX1(), sbu.getY1(), sbu.getX2(), sbu.getY2(), null);
		}
	}

	/**
	 * 画僚机子弹组
	 * 
	 * @param g
	 */
	private void paintBulletsSubunit(Graphics g) {
		for (int i = 0; i < bulletsSub.length; i++) {
			Bullet bsbu = bulletsSub[i];
			g.drawImage(wave, bsbu.getX(), bsbu.getY(), bsbu.getX()
					+ waveimg.getImagewidth(), bsbu.getY()
					+ waveimg.getImageheight(), waveimg.getX1(), waveimg
					.getY1(), waveimg.getX2(), waveimg.getY2(), null);
		}
	}

	/**
	 * 画BOSS及血条 BOSS的图案根据当前关卡状态(stagestate)决定 BOSS的血条根据返回的BOSS血量实时变化
	 * 
	 * @param g
	 */
	private void paintBoss(Graphics g) {
		switch (stagestate) {
		case STAGE_A:
			g.drawImage(boss1, boss.getX(), boss.getY(), boss.getX()
					+ boss.getWidth(), boss.getY() + boss.getHeight(), boss1img
					.getX1(), boss1img.getY1(), boss1img.getX2(), boss1img
					.getY2(), null);
			break;
		case STAGE_B:
			g.drawImage(boss2, boss.getX(), boss.getY(), boss.getX()
					+ boss.getWidth(), boss.getY() + boss.getHeight(), boss2img
					.getX1(), boss2img.getY1(), boss2img.getX2(), boss2img
					.getY2(), null);
			break;
		case STAGE_C:
			g.drawImage(boss3, boss.getX(), boss.getY(), boss.getX()
					+ boss.getWidth(), boss.getY() + boss.getHeight(), boss3img
					.getX1(), boss3img.getY1(), boss3img.getX2(), boss3img
					.getY2(), null);
			break;
		}

		g.setColor(Color.yellow);
		g.fillRect(485, 10, 15, 210);
		g.setColor(Color.red);
		g.fillRect(490, 15, 5,
				(int) ((200 * (boss.getLifepoint() / 50000)) / stagestate));
	}

	/**
	 * 画BOSS机子弹 同英雄机子弹根据返回类型进行绘制
	 * 
	 * @param g
	 */
	private void paintBulletsBoss(Graphics g) {
		for (int i = 0; i < bulletsBoss.length; i++) {
			Bullet bb = bulletsBoss[i];
			switch (bb.getType()) {
			case 31:
				g.drawImage(bulletC1, bb.getX(), bb.getY(), bb.getX()
						+ bb.getWidth(), bb.getY() + bb.getHeight(),
						bulletC1img.getX1(), bulletC1img.getY1(), bulletC1img
								.getX2(), bulletC1img.getY2(), null);
				break;
			case 32:
				g.drawImage(bb.getImage(), bb.getX(), bb.getY(), bb.getX()
						+ bb.getWidth(), bb.getY() + bb.getHeight(),
						bulletC2img.getX1(), bulletC2img.getY1(), bulletC2img
								.getX2(), bulletC2img.getY2(), null);
				break;
			case 33:
				g.drawImage(bb.getImage(), bb.getX(), bb.getY(), bb.getX()
						+ bb.getWidth(), bb.getY() + bb.getHeight(),
						bulletC3img.getX1(), bulletC3img.getY1(), bulletC3img
								.getX2(), bulletC3img.getY2(), null);
				break;
			case 34:
				g.drawImage(bb.getImage(), bb.getX(), bb.getY(), bb.getX()
						+ bb.getWidth(), bb.getY() + bb.getHeight(),
						bulletC4img.getX1(), bulletC4img.getY1(), bulletC4img
								.getX2(), bulletC4img.getY2(), null);
				break;
			case 35:
				g.drawImage(bb.getImage(), bb.getX(), bb.getY(), bb.getX()
						+ bb.getWidth(), bb.getY() + bb.getHeight(),
						bulletC5img.getX1(), bulletC5img.getY1(), bulletC5img
								.getX2(), bulletC5img.getY2(), null);
				break;
			}
		}
	}

	/**
	 * 画敌机 根据当前关卡状态绘制每关的敌机
	 * 
	 * @param g
	 */
	private void paintFlyingObjects(Graphics g) {
		switch (stagestate) {
		case STAGE_A:
			paintStageAEnemy(g);
			break;
		case STAGE_B:
			paintStageBEnemy(g);
			break;
		case STAGE_C:
			paintStageCEnemy(g);
			break;
		}
	}

	/**
	 * 关卡A敌机
	 * 
	 * @param g
	 */
	private void paintStageAEnemy(Graphics g) {
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			if (f instanceof Enemy) {
				Enemy e = (Enemy) f;
				switch (e.getType()) {
				case Enemy.SOLIDER1:
					g.drawImage(f.getImage(), f.getX(), f.getY(), f.getX()
							+ f.getWidth(), f.getY() + f.getHeight(),
							soldier_A1img.getX1(), soldier_A1img.getY1(),
							soldier_A1img.getX2(), soldier_A1img.getY2(), null);
					break;
				case Enemy.SOLIDER2:
					g.drawImage(f.getImage(), f.getX(), f.getY(), f.getX()
							+ f.getWidth(), f.getY() + f.getHeight(),
							soldier_A2img.getX1(), soldier_A2img.getY1(),
							soldier_A2img.getX2(), soldier_A2img.getY2(), null);
					break;
				case Enemy.SOLIDER3:
					g.drawImage(f.getImage(), f.getX(), f.getY(), f.getX()
							+ f.getWidth(), f.getY() + f.getHeight(),
							soldier_A3img.getX1(), soldier_A3img.getY1(),
							soldier_A3img.getX2(), soldier_A3img.getY2(), null);
					break;
				case Enemy.ELITE1:
					g.drawImage(f.getImage(), f.getX(), f.getY(), f.getX()
							+ f.getWidth(), f.getY() + f.getHeight(),
							elite_A1img.getX1(), elite_A1img.getY1(),
							elite_A1img.getX2(), elite_A1img.getY2(), null);
					break;
				case Enemy.ELITE2:
					g.drawImage(f.getImage(), f.getX(), f.getY(), f.getX()
							+ f.getWidth(), f.getY() + f.getHeight(),
							elite_A2img.getX1(), elite_A2img.getY1(),
							elite_A2img.getX2(), elite_A2img.getY2(), null);
					break;
				}
			}
		}
	}

	/**
	 * 关卡B敌机
	 * 
	 * @param g
	 */
	private void paintStageBEnemy(Graphics g) {
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			if (f instanceof Enemy) {
				Enemy e = (Enemy) f;
				switch (e.getType()) {
				case Enemy.SOLIDER1:
					g.drawImage(f.getImage(), f.getX(), f.getY(), f.getX()
							+ f.getWidth(), f.getY() + f.getHeight(),
							soldier_B1img.getX1(), soldier_B1img.getY1(),
							soldier_B1img.getX2(), soldier_B1img.getY2(), null);
					break;
				case Enemy.SOLIDER2:
					g.drawImage(f.getImage(), f.getX(), f.getY(), f.getX()
							+ f.getWidth(), f.getY() + f.getHeight(),
							soldier_B2img.getX1(), soldier_B2img.getY1(),
							soldier_B2img.getX2(), soldier_B2img.getY2(), null);
					break;
				case Enemy.SOLIDER3:
					g.drawImage(f.getImage(), f.getX(), f.getY(), f.getX()
							+ f.getWidth(), f.getY() + f.getHeight(),
							soldier_B3img.getX1(), soldier_B3img.getY1(),
							soldier_B3img.getX2(), soldier_B3img.getY2(), null);
					break;
				case Enemy.ELITE1:
					g.drawImage(f.getImage(), f.getX(), f.getY(), f.getX()
							+ f.getWidth(), f.getY() + f.getHeight(),
							elite_B1img.getX1(), elite_B1img.getY1(),
							elite_B1img.getX2(), elite_B1img.getY2(), null);
					break;
				case Enemy.ELITE2:
					g.drawImage(f.getImage(), f.getX(), f.getY(), f.getX()
							+ f.getWidth(), f.getY() + f.getHeight(),
							elite_B2img.getX1(), elite_B2img.getY1(),
							elite_B2img.getX2(), elite_B2img.getY2(), null);
					break;
				}
			}
		}
	}

	/**
	 * 关卡C敌机
	 * 
	 * @param g
	 */
	private void paintStageCEnemy(Graphics g) {
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			if (f instanceof Enemy) {
				Enemy e = (Enemy) f;
				switch (e.getType()) {
				case Enemy.SOLIDER1:
					g.drawImage(f.getImage(), f.getX(), f.getY(), f.getX()
							+ f.getWidth(), f.getY() + f.getHeight(),
							soldier_C1img.getX1(), soldier_C1img.getY1(),
							soldier_C1img.getX2(), soldier_C1img.getY2(), null);
					break;
				case Enemy.SOLIDER2:
					g.drawImage(f.getImage(), f.getX(), f.getY(), f.getX()
							+ f.getWidth(), f.getY() + f.getHeight(),
							soldier_C2img.getX1(), soldier_C2img.getY1(),
							soldier_C2img.getX2(), soldier_C2img.getY2(), null);
					break;
				case Enemy.SOLIDER3:
					g.drawImage(f.getImage(), f.getX(), f.getY(), f.getX()
							+ f.getWidth(), f.getY() + f.getHeight(),
							soldier_C3img.getX1(), soldier_C3img.getY1(),
							soldier_C3img.getX2(), soldier_C3img.getY2(), null);
					break;
				case Enemy.ELITE1:
					g.drawImage(f.getImage(), f.getX(), f.getY(), f.getX()
							+ f.getWidth(), f.getY() + f.getHeight(),
							elite_C1img.getX1(), elite_C1img.getY1(),
							elite_C1img.getX2(), elite_C1img.getY2(), null);
					break;
				case Enemy.ELITE2:
					g.drawImage(f.getImage(), f.getX(), f.getY(), f.getX()
							+ f.getWidth(), f.getY() + f.getHeight(),
							elite_C2img.getX1(), elite_C2img.getY1(),
							elite_C2img.getX2(), elite_C2img.getY2(), null);
					break;
				}
			}
		}
	}

	/**
	 * 画敌机子弹
	 * 
	 * @param g
	 */
	private void paintBulletsEnemy(Graphics g) {
		for (int i = 0; i < bulletsEnemy.length; i++) {
			Bullet be = bulletsEnemy[i];
			switch (be.getType()) {
			case 41:
				g.drawImage(bulletD1, be.getX(), be.getY(), be.getX()
						+ be.getWidth(), be.getY() + be.getHeight(),
						bulletD1img.getX1(), bulletD1img.getY1(), bulletD1img
								.getX2(), bulletD1img.getY2(), null);
				break;
			case 42:
				g.drawImage(bulletD2, be.getX(), be.getY(), be.getX()
						+ be.getWidth(), be.getY() + be.getHeight(),
						bulletD2img.getX1(), bulletD2img.getY1(), bulletD2img
								.getX2(), bulletD2img.getY2(), null);
				break;
			case 43:
				g.drawImage(bulletD3, be.getX(), be.getY(), be.getX()
						+ be.getWidth(), be.getY() + be.getHeight(),
						bulletD3img.getX1(), bulletD3img.getY1(), bulletD3img
								.getX2(), bulletD3img.getY2(), null);
				break;
			}
		}
	}

	/**
	 * 画道具 道具类同样是根据返回的类型判断绘制
	 * 
	 * @param g
	 */
	private void paintItem(Graphics g) {
		for (int i = 0; i < items.length; i++) {
			Item it = items[i];
			switch (it.getType()) {
			case 0:
				g.drawImage(it.getImage(), it.getX(), it.getY(), it.getX()
						+ it.getWidth(), it.getY() + it.getHeight(), itemAimg
						.getX1(), itemAimg.getY1(), itemAimg.getX2(), itemAimg
						.getY2(), null);
				break;
			case 1:
				g.drawImage(it.getImage(), it.getX(), it.getY(), it.getX()
						+ it.getWidth(), it.getY() + it.getHeight(), itemBimg
						.getX1(), itemBimg.getY1(), itemBimg.getX2(), itemBimg
						.getY2(), null);
				break;
			case 2:
				g.drawImage(it.getImage(), it.getX(), it.getY(), it.getX()
						+ it.getWidth(), it.getY() + it.getHeight(), itemCimg
						.getX1(), itemCimg.getY1(), itemCimg.getX2(), itemCimg
						.getY2(), null);
				break;
			case 3:
				g.drawImage(it.getImage(), it.getX(), it.getY(), it.getX()
						+ it.getWidth(), it.getY() + it.getHeight(), itemDimg
						.getX1(), itemDimg.getY1(), itemDimg.getX2(), itemDimg
						.getY2(), null);
				break;
			case 4:
				g.drawImage(it.getImage(), it.getX(), it.getY(), it.getX()
						+ it.getWidth(), it.getY() + it.getHeight(), itemEimg
						.getX1(), itemEimg.getY1(), itemEimg.getX2(), itemEimg
						.getY2(), null);
				break;
			}
		}
	}

	/**
	 * 画分数和生命和炸弹
	 * 
	 * @param g
	 */
	private void paintScore(Graphics g) {
		int x = 10;
		int y = 25;
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 22);
		g.setColor(new Color(0x00ff00));
		g.setFont(font);
		g.drawString("得分：" + score, x, y);
		y += 20;
		g.drawString("生命：" + hero.getLife(), x, y);

		for (int i = 0; i < bombnum; i++) {
			g.drawImage(item, 10 + itemEimg.getImagewidth() * i, 440, 10
					+ itemEimg.getImagewidth() + itemEimg.getImagewidth() * i,
					440 + itemEimg.getImageheight(), itemEimg.getX1(), itemEimg
							.getY1(), itemEimg.getX2(), itemEimg.getY2(), null);
		}
	}

	/**
	 * 画背景图
	 * 
	 * @param g
	 */
	private void paintBackground(Graphics g) {
		g.drawImage(bg1.getImage(), bg1.getX(), bg1.getY(), null);
		g.drawImage(bg2.getImage(), bg2.getX(), bg2.getY(), null);
	}

	/**
	 * 开始菜单 开始菜单根据开始菜单时间(startmenutimes)可以让图案产生闪烁效果,(之后的闪烁效果都是如此不在做说明)
	 * 
	 * @param g
	 */
	private void paintStartMenu(Graphics g) {
		g.drawImage(menu, 0, 0, 512, 512, 0, 0, 480, 512, null);
		g.drawImage(logo, 66, 100, 446, 210, 0, 0, 380, 110, null);
		if (startmenutimes % 100 < 50) {// 0.5秒的闪与不闪交替
			Font font = new Font(Font.SANS_SERIF, Font.BOLD, 22);
			g.setColor(Color.red);
			g.setFont(font);
			g.drawString("请按鼠标左键开始", 160, 300);
		}
	}

	/**
	 * 加载界面
	 * 
	 * @param g
	 */
	private void paintLoading(Graphics g) {
		g.drawImage(loading_bg, 0, 0, 512, 512, 0, 0, 512, 512, null);
		if (loadingtimes % 100 < 50) {
			g.drawImage(loading, 256 - loading.getWidth() / 2, 256 - loading
					.getHeight() / 2, 256 + loading.getWidth() / 2,
					256 + loading.getHeight() / 2, 0, 0, 296, 21, null);
		}
	}

	/**
	 * 关卡开场动画
	 * 
	 * @param g
	 */
	private void paintStartAnime(Graphics g) {
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 22);
		g.setColor(Color.red);
		g.setFont(font);
		switch (stagestate) {
		case STAGE_A:
			g.drawString("STAGE A", 200, 156);
			break;
		case STAGE_B:
			g.drawString("STAGE B", 200, 156);
			break;
		case STAGE_C:
			g.drawString("STAGE C", 200, 156);
			break;
		}

		if (startanimetimes > 0 && startanimetimes < 200) {
			g.drawImage(ready, 256 - ready.getWidth() / 2, 256 - ready
					.getHeight() / 2, 256 + ready.getWidth() / 2, 256 + ready
					.getHeight() / 2, 0, 0, ready.getWidth(),
					ready.getHeight(), null);
		}
		if (startanimetimes > 200 && startanimetimes < 400) {
			g.drawImage(go, 256 - go.getWidth() / 2, 256 - go.getHeight() / 2,
					256 + go.getWidth() / 2, 256 + go.getHeight() / 2, 0, 0, go
							.getWidth(), go.getHeight(), null);
		}
		if (startanimetimes > 400) {
			gamestate = RUNNING;
		}
	}

	/**
	 * BOSS战前警告
	 */
	private void paintBossWarning(Graphics g) {
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 22);
		g.setColor(Color.red);
		g.setFont(font);
		if (warningtimes % 100 < 50) {
			g.drawString("WARNING", 190, 156);
		}
	}

	/**
	 * 游戏结束
	 * 
	 * @param g
	 */
	private void paintGameOver(Graphics g) {
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 22);
		g.setColor(Color.yellow);
		g.setFont(font);
		g.drawString("GAME OVER", 190, 156);
		g.drawString("本次得分：" + score, 170, 256);
		if (gameovertimes % 100 < 50) {
			g.drawString("请按鼠标左键回到开始界面", 120, 356);
		}
	}

	/**
	 * 过关动画
	 * 
	 * @param g
	 */
	private void paintStageClear(Graphics g) {
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 22);
		g.setColor(Color.yellow);
		g.setFont(font);
		switch (stagestate) {
		case STAGE_A:
			g.drawString("CONGRATULATION!", 140, 156);
			g.drawString("STAGE A CLEAR", 140, 256);
			break;
		case STAGE_B:
			g.drawString("CONGRATULATION!", 140, 156);
			g.drawString("STAGE B CLEAR", 140, 256);
			break;
		case STAGE_C:
			g.drawString("CONGRATULATION!", 140, 156);
			g.drawString("GAME CLEAR", 140, 256);
			break;
		}
	}

	/**
	 * 画爆炸效果
	 * 
	 * @param g
	 */
	private void paintExplosion(Graphics g) {
		for (int i = 0; i < booms.length; i++) {
			Explosion exp = booms[i];
			g.drawImage(explosion, exp.getX(), exp.getY(), exp.getX()
					+ exp.getWidth(), exp.getY() + exp.getHeight(),
					exp.getX1(), exp.getY1(), exp.getX2(), exp.getY2(), null);
		}
	}

	/**
	 * 画陨石
	 * 
	 * @param g
	 */
	private void paintAsteroids(Graphics g) {
		for (int i = 0; i < atds.length; i++) {
			Asteroids a = atds[i];
			g.drawImage(asteroids, a.getX(), a.getY(), a.getX() + a.getWidth(),
					a.getY() + a.getHeight(), a.getX1(), a.getY1(), a.getX2(),
					a.getY2(), null);
		}
	}

	/**
	 * 画大招
	 * 
	 * @param g
	 */
	private void paintBomb(Graphics g) {
		for (int i = 0; i < bomb.length; i++) {
			Bomb bm = bomb[i];
			g.drawImage(bm.getImage(), bm.getX(), bm.getY(), bm.getX()
					+ bm.getWidth(), bm.getY() + bm.getHeight(), 0, 0, 64, 64,
					null);
		}
	}

	/**
	 * 画结束
	 * 
	 * @param g
	 */
	private void paintEnd(Graphics g) {
		g.drawImage(end, 0, 0, 512, 512, 0, 0, 480, 512, null);
		Font font1 = new Font(Font.SANS_SERIF, Font.BOLD, 32);
		g.setColor(new Color(0x00ff00));
		g.setFont(font1);
		g.drawString("恭喜你通过了所有的关卡！", 50, 156);
		g.drawString("你的得分：" + score, 80, 256);
		Font font2 = new Font(Font.SANS_SERIF, Font.BOLD, 22);
		g.setColor(new Color(0xc0c0c0));
		g.setFont(font2);
		g.drawString("按鼠标左键退出游戏|右键回到开始界面", 50, 356);

	}

	// 别问我这是啥，不知道的去下个班重读再来
	public static void main(String[] args) {
		frame = new JFrame("宇宙之翼");
		Main game = new Main();
		frame.setSize(SCREENWIDTH, SCREENHEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		frame.add(game);
		frame.setVisible(true);
		game.action();
	}

	// 动作，也就是整个流程的中枢，整个游戏运行于此
	public void action() {
		// 鼠标监听事件
		MouseAdapter k = new MouseAdapter() {
			// 鼠标移动
			@Override
			public void mouseMoved(MouseEvent e) {
				/*
				 * 判断鼠标左右移动是由两个X坐标判断的
				 * 第二次赋值为何在第一次赋值前面，请仔细看看第二次赋值的条件，如果第一次赋值在二之前会怎么样
				 */
				if (hero.getHerogamestate() == Hero.FIGHT) {
					// 第二次赋值
					if (mousex2 == -1 && mousex1 != -1) {
						mousex2 = e.getX();
						// X1大于X2是向左移动的，反之相同就是不动或者是上下，剩下的则是右移动了
						if (mousex1 > mousex2) {
							if (heroFlyingState != HERO_LEFT2) {
								heroFlyingState = HERO_LEFT1;
								mousex1 = -1;
								mousex2 = -1;
							} else {
								heroFlyingState = HERO_LEFT2;
								mousex1 = -1;
								mousex2 = -1;
							}
						} else if (mousex1 < mousex2) {
							if (heroFlyingState != HERO_RIGHT2) {
								heroFlyingState = HERO_RIGHT1;
								mousex1 = -1;
								mousex2 = -1;
							} else {
								heroFlyingState = HERO_RIGHT2;
								mousex1 = -1;
								mousex2 = -1;
							}
						} else {
							heroFlyingState = HERO_NORMAL;
							mousex1 = -1;
							mousex2 = -1;
						}
					}
					// 第一次赋值
					if (mousex1 == -1) {
						mousex1 = e.getX();
					}
					int x = e.getX();
					int y = e.getY();

					hero.moveTo(x, y, heroFlyingState);
				}
			}

			// 鼠标点击时间
			@Override
			public void mouseClicked(MouseEvent e) {
				if (hero.getHerogamestate() == Hero.FIGHT) {
					if (e.getButton() == MouseEvent.BUTTON3) {
						if (bombnum > 0) {
							if (canboom) {
								canboom = false;
								boom = true;
								bombnum -= 1;
							}
						}
					}
				}

				// 此处控制开始菜单的开始游戏和游戏结束返回开始菜单的时间
				if (gamestate == START || gamestate == GAME_OVER) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						switch (gamestate) {
						case START:
							gamestate = START_ANIME;
							break;
						case GAME_OVER:// 游戏结束后重新开始需重置所有有关变量与对象，不然会发生许多难以想象的事情
							flyings = new FlyingObject[0];// 重置敌机
							bulletsHero = new Bullet[0];// 重置英雄机子弹
							bulletsBoss = new Bullet[0];// 重置BOSS子弹
							bulletsEnemy = new Bullet[0];// 重置敌机子弹
							atds = new Asteroids[0];// 重置陨石
							hero = new Hero();// 重置英雄
							stagestate = STAGE_A;
							boss = new Boss(stagestate);// 重置BOSS
							items = new Item[0];// 重置道具
							booms = new Explosion[0];// 重置爆炸
							bg1 = new Background(stagestate, 1);
							bg2 = new Background(stagestate, 2);
							gamestate = START;
							startmenutimes = 0;
							godtimes = 0;
							playtimes = 0;
							warningtimes = 0;
							startanimetimes = 0;
							gameovertimes = 0;
							mousex1 = -1;
							mousex2 = -1;
							score = 0;
							bombnum = 3;
							break;
						}
					}
				}

				// 此处控制游戏通关后是返回开始菜单还是退出
				if (gamestate == END) {
					switch (e.getButton()) {
					case MouseEvent.BUTTON1:
						System.exit(1);
						break;
					case MouseEvent.BUTTON3:
						flyings = new FlyingObject[0];
						bulletsHero = new Bullet[0];
						bulletsBoss = new Bullet[0];
						atds = new Asteroids[0];
						hero = new Hero();
						stagestate = STAGE_A;
						boss = new Boss(stagestate);
						items = new Item[0];
						booms = new Explosion[0];
						gamestate = START;
						bg1 = new Background(stagestate, 1);
						bg2 = new Background(stagestate, 2);
						startmenutimes = 0;// 开始菜单计时
						startanimetimes = 0;// 开场动画计时
						godtimes = 0;// 英雄机重生无敌时间
						warningtimes = 0;// 警告时间
						playtimes = 0;// 关卡时间
						bossovertimes = 0;// BOSSOVER时间
						stagecleartimes = 0;// 过关状态计时
						gameovertimes = 0;// 游戏结束时间
						bgspeed = 1;// 背景图速度
						loadingtimes = 0;// 加载时间
						mousex1 = -1;
						mousex2 = -1;
						score = 0;
						bombnum = 3;
						break;
					}
				}
			}
		};

		// 键盘监听事件，因为无法坚决卡頓的问题，所以改用鼠标事件代替移动
		KeyAdapter l = new KeyAdapter() {
			// keyCode只对press和release有效
			// @Override
			// public void keyTyped(KeyEvent e) {
			// System.out.println(e.getKeyCode());
			// System.out.println(e.getKeyChar());
			// if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// switch (gamestate) {
			// case START:
			// gamestate = START_ANIME;
			// break;
			// case GAME_OVER:
			// flyings = new FlyingObject[0];
			// bulletsHero = new Bullet[0];
			// bulletsBoss = new Bullet[0];
			// hero = new Hero();
			// stagestate = STAGE_A;
			// boss = new Boss(stagestate);
			// items = new Item[0];
			// gamestate = START;
			// break;
			// }
			// }
			// }

			// 键盘按下事件
			@Override
			public void keyPressed(KeyEvent e) {
				int x;
				int y;
				// 此处根据按下的键返回的KEYCODE来判断是哪个键
				if (hero.getHerogamestate() == Hero.FIGHT) {
					int pressKey = e.getKeyCode();
					// 按下的方向键为TRUE，为了防止冲突相反的为FALSE
					switch (pressKey) {
					case KeyEvent.VK_LEFT:
						leftKey = true;
						rightKey = false;
						break;
					case KeyEvent.VK_UP:
						upKey = true;
						downKey = false;
						break;
					case KeyEvent.VK_DOWN:
						downKey = true;
						upKey = false;
						break;
					case KeyEvent.VK_RIGHT:
						rightKey = true;
						leftKey = false;
						break;
					}
				}

				if (upKey == false && downKey == false && leftKey == false
						&& rightKey == false) {// 啥都没按
					x = hero.getX();
					y = hero.getY();
					hero.moveTo(x, y, heroFlyingState);
				} else if (upKey == true && downKey == false
						&& leftKey == false && rightKey == false) {
					// 上
					x = hero.getX();
					y = hero.getY() - 5;
					hero.moveTo(x, y, heroFlyingState);
				} else if (upKey == false && downKey == true & leftKey == false
						&& rightKey == false) {
					// 下
					x = hero.getX();
					y = hero.getY() + 5;
					hero.moveTo(x, y, heroFlyingState);
				} else if (upKey == false && downKey == true & leftKey == true
						&& rightKey == false) {
					// 左下
					if (heroFlyingState != HERO_LEFT2) {
						heroFlyingState = HERO_LEFT1;
					} else {
						heroFlyingState = HERO_LEFT2;
					}
					x = hero.getX() - 5;
					y = hero.getY() + 5;
					hero.moveTo(x, y, heroFlyingState);
				} else if (upKey == true && downKey == false & leftKey == false
						&& rightKey == true) {
					// 右上
					if (heroFlyingState != HERO_RIGHT2) {
						heroFlyingState = HERO_RIGHT1;
					} else {
						heroFlyingState = HERO_RIGHT2;
					}
					x = hero.getX() + 5;
					y = hero.getY() - 5;
					hero.moveTo(x, y, heroFlyingState);
				} else if (upKey == true && downKey == false & leftKey == true
						&& rightKey == false) {
					// 左上
					if (heroFlyingState != HERO_LEFT2) {
						heroFlyingState = HERO_LEFT1;
					} else {
						heroFlyingState = HERO_LEFT2;
					}
					x = hero.getX() - 5;
					y = hero.getY() - 5;
					hero.moveTo(x, y, heroFlyingState);

				} else if (upKey == false && downKey == true & leftKey == false
						&& rightKey == true) {
					// 右下
					if (heroFlyingState != HERO_RIGHT2) {
						heroFlyingState = HERO_RIGHT1;
					} else {
						heroFlyingState = HERO_RIGHT2;
					}
					x = hero.getX() + 5;
					y = hero.getY() + 5;
					hero.moveTo(x, y, heroFlyingState);

				} else if (upKey == false && downKey == false & leftKey == true
						&& rightKey == false) {
					// 左
					if (heroFlyingState != HERO_LEFT2) {
						heroFlyingState = HERO_LEFT1;
					} else {
						heroFlyingState = HERO_LEFT2;
					}
					x = hero.getX() - 5;
					y = hero.getY();
					hero.moveTo(x, y, heroFlyingState);

				} else if (upKey == false && downKey == false
						& leftKey == false && rightKey == true) {
					// 右
					if (heroFlyingState != HERO_RIGHT2) {
						heroFlyingState = HERO_RIGHT1;
					} else {
						heroFlyingState = HERO_RIGHT2;
					}
					x = hero.getX() + 5;
					y = hero.getY();
					hero.moveTo(x, y, heroFlyingState);
				}
				// // 左移
				// if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				// if (heroFlyingState != HERO_LEFT2) {
				// heroFlyingState = HERO_LEFT1;
				// } else {
				// heroFlyingState = HERO_LEFT2;
				// }
				// x = hero.getX() - 3;
				// y = hero.getY();
				// hero.moveTo(x, y, heroFlyingState);
				// }
				//
				// if(e.getKeyCode()==KeyEvent.VK_RIGHT){
				// if(heroFlyingState!=HERO_RIGHT2){
				// heroFlyingState=HERO_RIGHT1;
				// }else{
				// heroFlyingState=HERO_RIGHT2;
				// }
				// x=hero.getX()+3;
				// y=hero.getY();
				// if(e.getKeyCode()==KeyEvent.VK_UP){
				// y=hero.getY()+3;
				// }
				// hero.moveTo(x, y, heroFlyingState);
				// }
			}

			// 键盘松开事件
			@Override
			public void keyReleased(KeyEvent e) {
				// 将松开的方向键状态还原为FALSE
				heroFlyingState = HERO_NORMAL;
				int i = e.getKeyCode();
				switch (i) {
				case KeyEvent.VK_LEFT:
					leftKey = false;
					break;
				case KeyEvent.VK_UP:
					upKey = false;
					break;
				case KeyEvent.VK_DOWN:
					downKey = false;
					break;
				case KeyEvent.VK_RIGHT:
					rightKey = false;
					break;
				}

				// 此处同鼠标的游戏状态控制
				if (gamestate == START || gamestate == GAME_OVER) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						switch (gamestate) {
						case START:
							gamestate = START_ANIME;
							break;
						case GAME_OVER:
							flyings = new FlyingObject[0];
							bulletsHero = new Bullet[0];
							bulletsBoss = new Bullet[0];
							hero = new Hero();
							stagestate = STAGE_A;
							boss = new Boss(stagestate);
							items = new Item[0];
							gamestate = START;
							startmenutimes = 0;
							godtimes = 0;
							playtimes = 0;
							warningtimes = 0;
							startanimetimes = 0;
							gameovertimes = 0;
							break;
						}
					}
				}
			}
		};

		// 键盘事件必须添加到游戏框上，不然无效
		// frame.addKeyListener(l);
		// 添加鼠标事件
		this.addMouseListener(k);
		this.addMouseMotionListener(k);

		// 实例化计时器
		timer = new Timer();
		// 这个是啥不知道的去查API，别告诉我不会。
		timer.schedule(new TimerTask() {
			// 整个游戏的流程皆在此处完成
			@Override
			public void run() {
				// 下面的所有调用的方法不知道的请将鼠标悬停其上
				switch (gamestate) {
				case START:
					startmenutimes++;
					startanimetimes = 0;// 开场动画计时
					godtimes = 0;// 英雄机重生无敌时间
					warningtimes = 0;// 警告时间
					playtimes = 0;// 关卡时间
					bossovertimes = 0;// BOSSOVER时间
					stagecleartimes = 0;// 过关状态计时
					gameovertimes = 0;// 游戏结束时间
					bgspeed = 1;// 背景图速度
					loadingtimes = 0;// 加载时间
					mousex1 = -1;
					mousex2 = -1;
					break;
				case START_ANIME:
					startanimetimes++;
					bg1.setRollspeed(bgspeed);// 重设背景图滚动速度
					bg2.setRollspeed(bgspeed);// 重设背景图滚动速度
					stepAction_Background();
					enterAciton_Asteroids();
					stepAction_Asteroids();
					stepAction_Hero();
					stepAction_Sub();
					if (!canboom) {
						boomtime++;
						if (boom) {
							stepAction_Bomb();
							flyings = new FlyingObject[0];
							bulletsEnemy = new Bullet[0];
							if (boomtime == 200) {
								boom = false;
								bomb = new Bomb[] { new Bomb(1), new Bomb(2),
										new Bomb(3), new Bomb(4) };
							}
						}
						if (boomtime == 300) {
							canboom = true;
							boomtime = 0;
						}
					}
					break;
				case REBORN:
					godtimes++;
					playtimes++;
					stepAction_Background();
					enterAciton_Asteroids();
					stepAction_Asteroids();
					stepAction_Hero();
					stepAction_Sub();
					if (gamestateold == BOSS) {
						stepAction_HeroB();
						stepAction_SubB();
						shootAction_Boss();
						stepAction_Boss();
						stepAction_BossB();

					} else {
						enterAction_Enemy();
						stepAction_Enemy();
						shootAction_Enemy();
						stepAction_EnemyB();
						stepAction_Item();
						stepAction_HeroB();
						stepAction_SubB();
					}
					if (hero.getHerogamestate() == Hero.FIGHT) {
						if (gamestateold == BOSS) {
							shootAction_Hero();
							shootAction_Sub();
							bangAction_HTB();
							bangAction_SBTB();
						} else {
							shootAction_Hero();
							shootAction_Sub();
							bangAction_HTE();
							bangAction_STE();
						}
					}
					if (godtimes > 500) {
						gamestate = gamestateold;
					}
					bangAction_ITH();
					stepAction_Explosion();
					outOfBoundsAction();
					if (!canboom) {
						boomtime++;
						if (boom) {
							stepAction_Bomb();
							if (gamestateold == BOSS) {
								boss.setLifepoint(boss.getLifepoint() - 50);
								bulletsBoss = new Bullet[0];
							} else {
								flyings = new FlyingObject[0];
								bulletsEnemy = new Bullet[0];
							}
							if (boomtime == 200) {
								boom = false;
								bomb = new Bomb[] { new Bomb(1), new Bomb(2),
										new Bomb(3), new Bomb(4) };
							}
						}
						if (boomtime == 300) {
							canboom = true;
							boomtime = 0;
						}
					}
					checkGameOverAction();
					// checkGameState();
					break;
				case RUNNING:
					playtimes++;
					godtimes = 0;
					stepAction_Background();
					enterAciton_Asteroids();
					stepAction_Asteroids();
					enterAction_Enemy();
					stepAction_Enemy();
					shootAction_Enemy();
					stepAction_EnemyB();
					shootAction_Hero();
					stepAction_HeroB();
					stepAction_Sub();
					shootAction_Sub();
					stepAction_SubB();
					stepAction_Item();
					bangAction_HTE();
					bangAction_ITH();
					bangAction_ETH();
					bangAction_STE();
					hitAction_ETH();
					stepAction_Explosion();
					outOfBoundsAction();
					if (!canboom) {
						boomtime++;
						if (boom) {
							stepAction_Bomb();
							flyings = new FlyingObject[0];
							bulletsEnemy = new Bullet[0];
							if (boomtime == 200) {
								boom = false;
								bomb = new Bomb[] { new Bomb(1), new Bomb(2),
										new Bomb(3), new Bomb(4) };
							}
						}
						if (boomtime == 300) {
							canboom = true;
							boomtime = 0;
						}
					}
					checkGameOverAction();
					if (playtimes % 4000 == 0) {
						gamestate = BOSS_READY;
					}
					// checkGameState();
					break;
				case BOSS_READY:
					godtimes = 0;
					stepAction_Background();
					enterAciton_Asteroids();
					stepAction_Asteroids();
					stepAction_Enemy();
					shootAction_Enemy();
					stepAction_EnemyB();
					shootAction_Hero();
					stepAction_HeroB();
					stepAction_Sub();
					shootAction_Sub();
					stepAction_SubB();
					stepAction_Item();
					bangAction_HTE();
					bangAction_ITH();
					bangAction_ETH();
					bangAction_STE();
					hitAction_ETH();
					stepAction_Explosion();
					outOfBoundsAction();
					if (!canboom) {
						boomtime++;
						if (boom) {
							stepAction_Bomb();
							flyings = new FlyingObject[0];
							bulletsEnemy = new Bullet[0];
							if (boomtime == 200) {
								boom = false;
								bomb = new Bomb[] { new Bomb(1), new Bomb(2),
										new Bomb(3), new Bomb(4) };
							}
						}
						if (boomtime == 300) {
							canboom = true;
							boomtime = 0;
						}
					}
					checkGameOverAction();
					if (flyings.length == 0 && items.length == 0
							&& bulletsEnemy.length == 0) {
						gamestate = BOSS_WARNING;
					}
					// checkGameState();
					break;
				case BOSS_WARNING:
					godtimes = 0;
					warningtimes++;
					stepAction_Background();
					enterAciton_Asteroids();
					stepAction_Asteroids();
					shootAction_Hero();
					stepAction_HeroB();
					stepAction_Sub();
					shootAction_Sub();
					stepAction_SubB();
					stepAction_Explosion();
					outOfBoundsAction();
					if (!canboom) {
						boomtime++;
						if (boom) {
							stepAction_Bomb();
							if (boomtime == 200) {
								boom = false;
								bomb = new Bomb[] { new Bomb(1), new Bomb(2),
										new Bomb(3), new Bomb(4) };
							}
						}
						if (boomtime == 300) {
							canboom = true;
							boomtime = 0;
						}
					}
					checkGameOverAction();
					if (warningtimes % 300 == 0) {
						gamestate = BOSS_INCOME;
					}
					// checkGameState();
					break;
				case BOSS_INCOME:
					stepAction_Background();
					enterAciton_Asteroids();
					stepAction_Asteroids();
					shootAction_Hero();
					stepAction_HeroB();
					stepAction_Sub();
					shootAction_Sub();
					stepAction_SubB();
					stepAction_Boss();
					outOfBoundsAction();
					if (!canboom) {
						boomtime++;
						if (boom) {
							stepAction_Bomb();
							if (boomtime == 200) {
								boom = false;
								bomb = new Bomb[] { new Bomb(1), new Bomb(2),
										new Bomb(3), new Bomb(4) };
							}
						}
						if (boomtime == 300) {
							canboom = true;
							boomtime = 0;
						}
					}
					checkGameOverAction();
					// checkGameState();
					break;
				case BOSS:
					godtimes = 0;
					stepAction_Background();
					enterAciton_Asteroids();
					stepAction_Asteroids();
					shootAction_Hero();
					stepAction_HeroB();
					stepAction_Sub();
					shootAction_Sub();
					stepAction_SubB();
					stepAction_Boss();
					shootAction_Boss();
					stepAction_BossB();
					bangAction_BTH();
					bangAction_HTB();
					bangAction_SBTB();
					hitAction_BTH();
					stepAction_Explosion();
					outOfBoundsAction();
					if (!canboom) {
						boomtime++;
						if (boom) {
							stepAction_Bomb();
							boss.setLifepoint(boss.getLifepoint() - 50);
							bulletsBoss = new Bullet[0];
							if (boomtime == 200) {

								boom = false;
								bomb = new Bomb[] { new Bomb(1), new Bomb(2),
										new Bomb(3), new Bomb(4) };
							}
						}
						if (boomtime == 300) {
							canboom = true;
							boomtime = 0;
						}
					}
					checkGameOverAction();
					if (boss.getLifepoint() <= 0) {
						score += boss.getScore();
						gamestate = BOSS_OVER;
					}
					// checkGameState();
					break;
				case BOSS_OVER:
					bossovertimes++;
					if (bossovertimes % 5 == 0) {
						Explosion boom = boss.dead();
						booms = Arrays.copyOf(booms, booms.length + 1);
						booms[booms.length - 1] = boom;
					}
					stepAction_Background();
					enterAciton_Asteroids();
					stepAction_Asteroids();
					shootAction_Hero();
					stepAction_HeroB();
					stepAction_Sub();
					shootAction_Sub();
					stepAction_SubB();
					stepAction_Boss();
					stepAction_BossB();
					stepAction_Explosion();
					outOfBoundsAction();
					if (!canboom) {
						boomtime++;
						if (boom) {
							stepAction_Bomb();
							if (boomtime == 200) {
								boom = false;
								bomb = new Bomb[] { new Bomb(1), new Bomb(2),
										new Bomb(3), new Bomb(4) };
							}
						}
						if (boomtime == 300) {
							canboom = true;
							boomtime = 0;
						}
					}
					checkGameOverAction();
					if (boss.outOfBounds()) {
						gamestate = STAGE_CLEAR;
						hero.setHerogamestate(Hero.FLYING);
					}
					break;
				case STAGE_CLEAR:
					stagecleartimes++;
					if (stagecleartimes % 100 == 0) {
						bg1.setRollspeed(++bgspeed);
						bg2.setRollspeed(++bgspeed);
					}
					stepAction_Background();
					enterAciton_Asteroids();
					stepAction_Asteroids();
					stepAction_Hero();
					stepAction_HeroB();
					stepAction_Sub();
					stepAction_SubB();
					if (hero.getY() < (-hero.getHeight() - 20)) {
						if (stagestate != STAGE_C) {
							gamestate = LOADING;
						} else {
							gamestate = END;
						}
					}
					outOfBoundsAction();
					break;
				case LOADING:
					loadingtimes++;
					if (loadingtimes == 500) {
						gamestate = START_ANIME;
						switch (stagestate) {
						case STAGE_A:
							stagestate = STAGE_B;
							break;
						case STAGE_B:
							stagestate = STAGE_C;
							break;
						}
						startmenutimes = 0;// 开始菜单计时
						startanimetimes = 0;// 开场动画计时
						godtimes = 0;// 英雄机重生无敌时间
						warningtimes = 0;// 警告时间
						playtimes = 0;// 关卡时间
						bossovertimes = 0;// BOSSOVER时间
						stagecleartimes = 0;// 过关状态计时
						gameovertimes = 0;// 游戏结束时间
						bgspeed = 1;// 背景图速度
						loadingtimes = 0;// 加载时间
						mousex1 = -1;
						mousex2 = -1;
						hero.setHerogamestate(Hero.REBORN);
						hero.setX(SCREENWIDTH / 2 - hero.width / 2);
						hero.setY(SCREENHEIGHT - 1);
						boss = new Boss(stagestate);
						bg1 = new Background(stagestate, 1);
						bg2 = new Background(stagestate, 2);
						booms = new Explosion[0];
						flyings = new FlyingObject[0];
						bulletsBoss = new Bullet[0];
						bulletsHero = new Bullet[0];
						atds = new Asteroids[0];
					}
					break;
				case GAME_OVER:
					gameovertimes++;
					stepAction_Background();
					enterAciton_Asteroids();
					stepAction_Asteroids();
					if (gamestateold == BOSS) {
						shootAction_Boss();
						stepAction_Boss();
						stepAction_BossB();
					} else {
						enterAction_Enemy();
						stepAction_Enemy();
						stepAction_Item();
					}
					if (bulletsHero.length > 0) {
						stepAction_HeroB();
					}
					outOfBoundsAction();
					// checkGameState();
					break;
				case END:
					break;
				}
				repaint();
			}
		}, intervel, intervel);
	}

	/****************************************** 进场部分 ***************************************/
	int enemyFlyEnteredIndex = 0;

	/**
	 * 敌机进场
	 */
	private void enterAction_Enemy() {
		enemyFlyEnteredIndex++;
		if (enemyFlyEnteredIndex % (100 - (stagestate * 20)) == 0) {
			FlyingObject obj = nextOneEnemy();
			flyings = Arrays.copyOf(flyings, flyings.length + 1);
			flyings[flyings.length - 1] = obj;
		}
	}

	int asteroidsEnteredIndex = 0;

	/**
	 * 陨石进场
	 */
	private void enterAciton_Asteroids() {
		asteroidsEnteredIndex++;
		if (asteroidsEnteredIndex % 100 == 0) {
			Asteroids ast = new Asteroids();
			atds = Arrays.copyOf(atds, atds.length + 1);
			atds[atds.length - 1] = ast;
		}
	}

	/****************************************** 移动部分 *********************************************/
	/**
	 * 背景图动作
	 */
	private void stepAction_Background() {
		bg1.step();
		bg2.step();
	}

	/**
	 * 敌机移动
	 */
	private void stepAction_Enemy() {
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			f.step();
		}
	}

	/**
	 * 敌机子弹移动
	 */
	private void stepAction_EnemyB() {
		for (int i = 0; i < bulletsEnemy.length; i++) {
			Bullet be = bulletsEnemy[i];
			be.step();
		}
	}

	/**
	 * 英雄机重生移动
	 * 
	 * @param state
	 */
	private void stepAction_Hero() {
		hero.step();
	}

	/**
	 * 英雄机子弹移动
	 */
	private void stepAction_HeroB() {
		for (int i = 0; i < bulletsHero.length; i++) {
			Bullet bh = bulletsHero[i];
			bh.step();
		}
	}

	/**
	 * 僚机转动和移动
	 */
	private void stepAction_Sub() {
		for (int i = 0; i < subunits.length; i++) {
			Subunit sb = subunits[i];
			sb.step(hero.getX(), hero.getY());
		}
	}

	/**
	 * 僚机子弹移动
	 */
	private void stepAction_SubB() {
		for (int i = 0; i < bulletsSub.length; i++) {
			Bullet bsbu = bulletsSub[i];
			bsbu.step();
		}
	}

	/**
	 * BOSS机移动
	 */
	private void stepAction_Boss() {
		boss.step();
		if (gamestate != REBORN && gamestate != BOSS && gamestate != GAME_OVER) {
			if (boss.getBossstate() == Boss.FIGHT) {
				gamestate = BOSS;
			}
		}
	}

	/**
	 * BOSS子弹移动
	 */
	private void stepAction_BossB() {
		for (int i = 0; i < bulletsBoss.length; i++) {
			Bullet bb = bulletsBoss[i];
			bb.step();
		}
	}

	/**
	 * 道具移动
	 */
	private void stepAction_Item() {
		for (int i = 0; i < items.length; i++) {
			Item it = items[i];
			it.step();
		}
	}

	/**
	 * 爆炸效果显示及消灭
	 */
	private void stepAction_Explosion() {
		for (int i = 0; i < booms.length; i++) {
			Explosion exp = booms[i];
			exp.step();
			if (exp.getIsOver()) {
				Explosion temp = booms[i];
				booms[i] = booms[booms.length - 1];
				booms[booms.length - 1] = temp;
				booms = Arrays.copyOf(booms, booms.length - 1);
			}
		}
	}

	/**
	 * 陨石移动
	 */
	private void stepAction_Asteroids() {
		for (int i = 0; i < atds.length; i++) {
			Asteroids ast = atds[i];
			ast.step();
		}
	}

	/**
	 * 大招移动
	 */
	private void stepAction_Bomb() {
		for (int i = 0; i < bomb.length; i++) {
			Bomb bm = bomb[i];
			bm.step();
		}
	}

	/***************************************** 飞行物生成部分 **********************************************/
	/**
	 * 随机生成敌机
	 */
	public static FlyingObject nextOneEnemy() {
		Random random = new Random();
		int type = random.nextInt(3) + 1;
		switch (type) {
		case 1:
			return choosS1();
		case 2:
			return choosS2();
		case 3:
			return choosS3();
			// case 4:
			// break;
			// case 5:
			// break;
		}
		return null;
	}

	/**
	 * 第一种敌机
	 * 
	 * @return
	 */
	private static FlyingObject choosS1() {
		switch (stagestate) {
		case STAGE_A:
			return new Solider1(stagestate, soldier_A1img.getImagewidth(),
					soldier_A1img.getImageheight());
		case STAGE_B:
			return new Solider1(stagestate, soldier_B1img.getImagewidth(),
					soldier_B1img.getImageheight());
		case STAGE_C:
			return new Solider1(stagestate, soldier_C1img.getImagewidth(),
					soldier_C1img.getImageheight());
		}
		return null;
	}

	/**
	 * 第二种敌机
	 * 
	 * @return
	 */
	private static FlyingObject choosS2() {
		switch (stagestate) {
		case STAGE_A:
			return new Solider2(stagestate, soldier_A2img.getImagewidth(),
					soldier_A2img.getImageheight());
		case STAGE_B:
			return new Solider2(stagestate, soldier_B2img.getImagewidth(),
					soldier_B2img.getImageheight());
		case STAGE_C:
			return new Solider2(stagestate, soldier_C2img.getImagewidth(),
					soldier_C2img.getImageheight());
		}
		return null;
	}

	/**
	 * 第三种敌机
	 * 
	 * @return
	 */
	private static FlyingObject choosS3() {
		switch (stagestate) {
		case STAGE_A:
			return new Solider3(stagestate, soldier_A3img.getImagewidth(),
					soldier_A3img.getImageheight());
		case STAGE_B:
			return new Solider3(stagestate, soldier_B3img.getImagewidth(),
					soldier_B3img.getImageheight());
		case STAGE_C:
			return new Solider3(stagestate, soldier_C3img.getImagewidth(),
					soldier_C3img.getImageheight());
		}
		return null;
	}

	/**
	 * 随机生成道具 (这个好像一次也没调用过?)
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static FlyingObject nextOneAward(int x, int y) {
		Random random = new Random();
		int type = random.nextInt(4);
		switch (type) {
		case 0:
			return new Item(x, y, itemAimg, type);
		case 1:
			return new Item(x, y, itemBimg, type);
		case 2:
			return new Item(x, y, itemCimg, type);
		case 3:
			return new Item(x, y, itemDimg, type);
		}
		return null;
	}

	/****************************************** 射击动作部分 ************************************************************/
	int shootIndexHero = 0;

	/**
	 * 英雄机子弹射击动作
	 */
	private void shootAction_Hero() {
		shootIndexHero++;
		if (shootIndexHero % 15 == 0) {// 发射间隔150毫秒
			Bullet[] bsh = hero.shoot();
			bulletsHero = Arrays.copyOf(bulletsHero, bulletsHero.length
					+ bsh.length);
			System.arraycopy(bsh, 0, bulletsHero, bulletsHero.length
					- bsh.length, bsh.length);
		}
	}

	int shootIndexSub = 0;

	/**
	 * 僚机子弹射击动作
	 */
	private void shootAction_Sub() {
		shootIndexSub++;
		if (shootIndexSub % 150 == 0) {
			for (int i = 0; i < subunits.length; i++) {
				Bullet[] bsbu = subunits[i].shoot();
				bulletsSub = Arrays.copyOf(bulletsSub, bulletsSub.length
						+ bsbu.length);
				System.arraycopy(bsbu, 0, bulletsSub, bulletsSub.length
						- bsbu.length, bsbu.length);
			}

		}
	}

	int shootIndexBoss = 0;

	/**
	 * BOSS子弹射击动作
	 */
	private void shootAction_Boss() {
		shootIndexBoss++;
		if (shootIndexBoss % (100 - (stagestate * 10)) == 0) {// 发射间隔1秒
			Bullet[] bsb = boss.shoot();
			bulletsBoss = Arrays.copyOf(bulletsBoss, bulletsBoss.length
					+ bsb.length);
			System.arraycopy(bsb, 0, bulletsBoss, bulletsBoss.length
					- bsb.length, bsb.length);
		}
	}

	int shootIndexEnemy = 0;

	/**
	 * 敌机子弹射击动作
	 */
	private void shootAction_Enemy() {
		shootIndexEnemy++;
		if (shootIndexEnemy % (100 - (stagestate * 10)) == 0) {
			for (int i = 0; i < flyings.length; i++) {
				Bullet be = flyings[i].shoot(hero.getX(), hero.getY());
				bulletsEnemy = Arrays.copyOf(bulletsEnemy,
						bulletsEnemy.length + 1);
				bulletsEnemy[bulletsEnemy.length - 1] = be;
			}
		}
	}

	/***************************************** 越界删除部分 ********************************************/
	/**
	 * 删除越界的飞行物，子弹，奖励
	 */
	private void outOfBoundsAction() {
		int index = 0;
		// 删除越界的Enemy
		FlyingObject[] flyingLives = new FlyingObject[flyings.length];
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			if (!f.outOfBounds()) {
				flyingLives[index++] = f;
			}
		}
		flyings = Arrays.copyOf(flyingLives, index);

		// 删除越界的敌机子弹
		index = 0;
		Bullet[] bulletsEnemyLives = new Bullet[bulletsEnemy.length];
		for (int i = 0; i < bulletsEnemy.length; i++) {
			Bullet be = bulletsEnemy[i];
			if (!be.outOfBounds()) {
				bulletsEnemyLives[index++] = be;
			}
		}
		bulletsEnemy = Arrays.copyOf(bulletsEnemyLives, index);

		// 删除越界的奖励
		index = 0;
		Item[] itemLives = new Item[items.length];
		for (int i = 0; i < items.length; i++) {
			Item it = items[i];
			if (!it.outOfBounds()) {
				itemLives[index++] = it;
			}
		}
		items = Arrays.copyOf(itemLives, index);

		// 删除越界英雄机子弹
		index = 0;
		Bullet[] bulletsHeroLives = new Bullet[bulletsHero.length];
		for (int i = 0; i < bulletsHero.length; i++) {
			Bullet bh = bulletsHero[i];
			if (!bh.outOfBounds()) {
				bulletsHeroLives[index++] = bh;
			}
		}
		bulletsHero = Arrays.copyOf(bulletsHeroLives, index);

		// 删除越界BOSS机子弹
		index = 0;
		Bullet[] bulletsBossLives = new Bullet[bulletsBoss.length];
		for (int i = 0; i < bulletsBoss.length; i++) {
			Bullet bb = bulletsBoss[i];
			if (!bb.outOfBounds()) {
				bulletsBossLives[index++] = bb;
			}
		}
		bulletsBoss = Arrays.copyOf(bulletsBossLives, index);

		if (gamestate == BOSS_OVER) {
			if (boss.outOfBounds()) {
				gamestate = STAGE_CLEAR;
			}
		}

		// 删除越界僚机子弹
		index = 0;
		Bullet[] bulletsSubLives = new Bullet[bulletsSub.length];
		for (int i = 0; i < bulletsSub.length; i++) {
			Bullet bsbu = bulletsSub[i];
			if (!bsbu.outOfBounds()) {
				bulletsSubLives[index++] = bsbu;
			}
		}
		bulletsSub = Arrays.copyOf(bulletsSubLives, index);

		// 删除越界陨石
		index = 0;
		Asteroids[] asteroidsLives = new Asteroids[atds.length];
		for (int i = 0; i < atds.length; i++) {
			Asteroids ast = atds[i];
			if (!ast.outOfBounds()) {
				asteroidsLives[index++] = ast;
			}
		}
		atds = Arrays.copyOf(asteroidsLives, index);
	}

	/*************************************************** 碰撞部分 **********************************************************/
	/**
	 * 敌机子弹是否击中英雄机
	 */
	private boolean bangHeroE(Bullet bullet) {
		boolean result = false;
		boolean hit = false;
		Hero h = hero;
		if (h.hit(bullet)) {
			hit = true;
			result = true;
		}
		if (hit) {
			h.subtractLife();
			h.setFireLevel(1);
			if (hero.getLife() > 0) {
				gamestateold = gamestate;
				gamestate = REBORN;
			}
			h.setX(SCREENWIDTH / 2 - h.width / 2);
			h.setY(SCREENHEIGHT - 1);
			h.setHerogamestate(Hero.REBORN);
			subunits = new Subunit[0];
		}
		return result;
	}

	/**
	 * 敌机子弹与英雄机碰撞与消失
	 */
	private void bangAction_ETH() {
		for (int i = 0; i < bulletsEnemy.length; i++) {
			Bullet be = bulletsEnemy[i];
			if (bangHeroE(be)) {
				Bullet temp = bulletsEnemy[i];
				bulletsEnemy[i] = bulletsEnemy[bulletsEnemy.length - 1];
				bulletsEnemy[bulletsEnemy.length - 1] = temp;
				bulletsEnemy = Arrays.copyOf(bulletsEnemy,
						bulletsEnemy.length - 1);
			}
		}
	}

	/**
	 * 是否敌机碰到英雄机子弹
	 */
	private boolean bangEnemy(Bullet bullet) {
		boolean result = false;
		int index = -1;
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject obj = flyings[i];
			if (obj.shootBy(bullet)) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			FlyingObject one = flyings[index];
			FlyingObject temp = flyings[index];
			flyings[index] = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = temp;
			flyings = Arrays.copyOf(flyings, flyings.length - 1); // 删除最后一个飞行物(即被击中的)
			result = true;
			if (one instanceof Enemy) {
				int x = one.getX();
				int y = one.getY();
				Enemy e = (Enemy) one;
				int type = e.getType();
				switch (type) {
				case Enemy.SOLIDER1:
					Explosion boom1 = new Explosion(x, y);
					booms = Arrays.copyOf(booms, booms.length + 1);
					booms[booms.length - 1] = boom1;
					score += e.getScore();
					getAward(x, y);
					break;
				case Enemy.SOLIDER2:
					Explosion boom2 = new Explosion(x, y);
					booms = Arrays.copyOf(booms, booms.length + 1);
					booms[booms.length - 1] = boom2;
					score += e.getScore();
					getAward(x, y);
					break;
				case Enemy.SOLIDER3:
					Explosion boom3 = new Explosion(x, y);
					booms = Arrays.copyOf(booms, booms.length + 1);
					booms[booms.length - 1] = boom3;
					score += e.getScore();
					getAward(x, y);
					break;
				}
			}
		}
		return result;
	}

	/**
	 * 随机获得奖励
	 * 
	 * @param x
	 * @param y
	 */
	private void getAward(int x, int y) {
		Random random = new Random();
		switch (random.nextInt(51)) {
		case Award.NORMAL:
		case 5:
		case 6:
			Item it1 = new Item(x, y, itemAimg, Award.NORMAL);
			items = Arrays.copyOf(items, items.length + 1);
			items[items.length - 1] = it1;
			break;
		case Award.SCATTER:
		case 7:
		case 8:
			Item it2 = new Item(x, y, itemBimg, Award.SCATTER);
			items = Arrays.copyOf(items, items.length + 1);
			items[items.length - 1] = it2;
			break;
		case Award.LIFE:
			Item it3 = new Item(x, y, itemCimg, Award.LIFE);
			items = Arrays.copyOf(items, items.length + 1);
			items[items.length - 1] = it3;
			break;
		case Award.SUBUNIT:
			Item it4 = new Item(x, y, itemDimg, Award.SUBUNIT);
			items = Arrays.copyOf(items, items.length + 1);
			items[items.length - 1] = it4;
			break;
		case Award.BOMB:
		case 9:
			Item it5 = new Item(x, y, itemEimg, Award.BOMB);
			items = Arrays.copyOf(items, items.length + 1);
			items[items.length - 1] = it5;
			break;
		}
	}

	/**
	 * 英雄机子弹与敌机碰撞以及删除
	 */
	private void bangAction_HTE() {
		for (int i = 0; i < bulletsHero.length; i++) {
			Bullet bh = bulletsHero[i];
			Bullet temp = null;
			switch (gamestate) {
			case REBORN:
			case RUNNING:
			case BOSS_READY:
				if (bangEnemy(bh)) {
					temp = bulletsHero[i];
					bulletsHero[i] = bulletsHero[bulletsHero.length - 1];
					bulletsHero[bulletsHero.length - 1] = temp;
					bulletsHero = Arrays.copyOf(bulletsHero,
							bulletsHero.length - 1);
				}
				break;
			}
		}
	}

	/**
	 * 是否BOSS子弹击中英雄机
	 */
	private boolean bangHero(Bullet bullet) {
		boolean result = false;
		boolean hit = false;
		Hero h = hero;
		if (h.hit(bullet)) {
			hit = true;
			result = true;
		}
		if (hit) {
			h.subtractLife();
			h.setFireLevel(1);
			if (hero.getLife() > 0) {
				gamestateold = gamestate;
				gamestate = REBORN;
			}
			h.setX(SCREENWIDTH / 2 - h.width / 2);
			h.setY(SCREENHEIGHT - 1);
			h.setHerogamestate(Hero.REBORN);
			subunits = new Subunit[0];
		}
		return result;
	}

	/**
	 * BOSS子弹与英雄机的碰撞与子弹的消灭
	 */
	private void bangAction_BTH() {
		for (int i = 0; i < bulletsBoss.length; i++) {
			Bullet b = bulletsBoss[i];
			if (bangHero(b)) {
				Bullet temp = bulletsBoss[i];
				bulletsBoss[i] = bulletsBoss[bulletsBoss.length - 1];
				bulletsBoss[bulletsBoss.length - 1] = temp;
				bulletsBoss = Arrays
						.copyOf(bulletsBoss, bulletsBoss.length - 1);
			}
		}
	}

	/**
	 * 是否撞上道具
	 * 
	 * @param item
	 * @return
	 */
	private boolean bangItem(Item item) {
		boolean result = false;
		boolean hit = false;
		Hero h = hero;
		if (h.hit(item)) {
			hit = true;
			result = true;
		}
		if (hit) {
			switch (item.getType()) {
			case Award.NORMAL:
				if (h.getFireType() == 1) {
					h.addFireLevel();
				} else {
					h.setFireType(1);
					h.setFireLevel(1);
				}
				break;
			case Award.SCATTER:
				if (h.getFireType() == 2) {
					h.addFireLevel();
				} else {
					h.setFireType(2);
					h.setFireLevel(1);
				}
				break;
			case Award.LIFE:
				h.addLife();
				break;
			case Award.SUBUNIT:
				if (subunits.length != 0) {
					for (int i = 0; i < subunits.length; i++) {
						subunits[i].addFireLevel();
					}
				} else {
					subunits = new Subunit[2];
					subunits[0] = new Subunit(1, hero.getX(), hero.getY(), hero
							.getWidth(), hero.getHeight(), subunitimg);
					subunits[1] = new Subunit(2, hero.getX(), hero.getY(), hero
							.getWidth(), hero.getHeight(), subunitimg);
				}
				break;
			case Award.BOMB:
				bombnum += 1;
			}
		}
		return result;
	}

	/**
	 * 道具与英雄机的碰撞与消灭
	 */
	private void bangAction_ITH() {
		for (int i = 0; i < items.length; i++) {
			Item it = items[i];
			if (bangItem(it)) {
				Item temp = items[i];
				items[i] = items[items.length - 1];
				items[items.length - 1] = temp;
				items = Arrays.copyOf(items, items.length - 1);
			}
		}
	}

	/**
	 * 是否英雄机子弹与BOSS碰撞
	 * 
	 * @param bullet
	 * @return
	 */
	private boolean bangBoss(Bullet bullet) {
		boolean result = false;
		boolean hit = false;
		Boss b = boss;
		if (b.shootBy(bullet)) {
			result = true;
			hit = true;
		}
		if (hit) {
			b.getDamage();
		}
		return result;
	}

	/**
	 * 英雄机子弹与BOSS碰撞
	 */
	private void bangAction_HTB() {
		for (int i = 0; i < bulletsHero.length; i++) {
			Bullet bh = bulletsHero[i];
			Bullet temp = null;
			switch (gamestate) {
			case REBORN:
			case BOSS:
				if (bangBoss(bh)) {
					temp = bulletsHero[i];
					bulletsHero[i] = bulletsHero[bulletsHero.length - 1];
					bulletsHero[bulletsHero.length - 1] = temp;
					bulletsHero = Arrays.copyOf(bulletsHero,
							bulletsHero.length - 1);
				}
				break;
			}
		}
	}

	/**
	 * 是否与敌机相撞
	 * 
	 * @param obj
	 * @return
	 */
	private boolean hitEnemy(FlyingObject obj) {
		boolean result = false;
		boolean hit = false;
		Hero h = hero;
		if (h.hit(obj)) {
			hit = true;
			result = true;
		}
		if (hit) {
			h.subtractLife();
			h.setFireLevel(1);
			if (hero.getLife() > 0) {
				gamestateold = gamestate;
				gamestate = REBORN;
			}
			h.setX(SCREENWIDTH / 2 - h.width / 2);
			h.setY(SCREENHEIGHT - 1);
			h.setHerogamestate(Hero.REBORN);
			subunits = new Subunit[0];
		}
		return result;
	}

	/**
	 * 敌机与英雄机的相撞与消灭
	 */
	private void hitAction_ETH() {
		for (int i = 0; i < flyings.length; i++) {
			int index = -1;
			FlyingObject obj = flyings[i];
			if (hitEnemy(obj)) {
				index = i;
			}
			if (index != -1) {
				FlyingObject temp = flyings[index];
				flyings[index] = flyings[flyings.length - 1];
				flyings[flyings.length - 1] = temp;
				flyings = Arrays.copyOf(flyings, flyings.length - 1);
			}
		}
	}

	/**
	 * 是否与BOSS相撞
	 * 
	 * @param boss
	 * @return
	 */
	private boolean hitBoss(Boss boss) {
		boolean result = false;
		Hero h = hero;
		if (h.hit(boss)) {
			result = true;
		}
		return result;
	}

	/**
	 * 英雄机与BOSS的碰撞
	 */
	private void hitAction_BTH() {
		Boss b = boss;
		if (hitBoss(b)) {
			hero.subtractLife();
			hero.setFireLevel(1);
			if (hero.getLife() > 0) {
				gamestateold = gamestate;
				gamestate = REBORN;
			}
			hero.setX(SCREENWIDTH / 2 - hero.width / 2);
			hero.setY(SCREENHEIGHT - 1);
			hero.setHerogamestate(Hero.REBORN);
			subunits = new Subunit[0];
		}
	}

	/**
	 * 是否僚机子弹与敌机碰撞
	 * 
	 * @param bullet
	 * @return
	 */
	private boolean bangEnemy_Subunit(Bullet bullet) {
		boolean result = false;
		int index = -1;
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject obj = flyings[i];
			if (obj.shootBy(bullet)) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			FlyingObject one = flyings[index];
			FlyingObject temp = flyings[index];
			flyings[index] = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = temp;
			flyings = Arrays.copyOf(flyings, flyings.length - 1); // 删除最后一个飞行物(即被击中的)
			result = true;
			if (one instanceof Enemy) {
				int x = one.getX();
				int y = one.getY();
				Enemy e = (Enemy) one;
				int type = e.getType();
				switch (type) {
				case Enemy.SOLIDER1:
					Explosion boom1 = new Explosion(x, y);
					booms = Arrays.copyOf(booms, booms.length + 1);
					booms[booms.length - 1] = boom1;
					score += e.getScore();
					getAward(x, y);
					break;
				case Enemy.SOLIDER2:
					Explosion boom2 = new Explosion(x, y);
					booms = Arrays.copyOf(booms, booms.length + 1);
					booms[booms.length - 1] = boom2;
					score += e.getScore();
					getAward(x, y);
					break;
				case Enemy.SOLIDER3:
					Explosion boom3 = new Explosion(x, y);
					booms = Arrays.copyOf(booms, booms.length + 1);
					booms[booms.length - 1] = boom3;
					score += e.getScore();
					getAward(x, y);
					break;
				}
			}
		}
		return result;
	}

	/**
	 * 僚机子弹与敌机相撞
	 */
	private void bangAction_STE() {
		for (int i = 0; i < bulletsSub.length; i++) {
			Bullet bsbu = bulletsSub[i];
			Bullet temp = null;
			switch (gamestate) {
			case REBORN:
			case RUNNING:
			case BOSS_READY:
				if (bangEnemy_Subunit(bsbu)) {
					temp = bulletsSub[i];
					bulletsSub[i] = bulletsSub[bulletsSub.length - 1];
					bulletsSub[bulletsSub.length - 1] = temp;
					bulletsSub = Arrays.copyOf(bulletsSub,
							bulletsSub.length - 1);
				}
				break;
			}
		}
	}

	/**
	 * 是否英雄机子弹与BOSS碰撞
	 * 
	 * @param bullet
	 * @return
	 */
	private boolean bangBoss_Subunit(Bullet bullet) {
		boolean result = false;
		boolean hit = false;
		Boss b = boss;
		if (b.shootBy(bullet)) {
			result = true;
			hit = true;
		}
		if (hit) {
			b.getDamage();
		}
		return result;
	}

	/**
	 * 英雄机子弹与BOSS碰撞
	 */
	private void bangAction_SBTB() {
		for (int i = 0; i < bulletsSub.length; i++) {
			Bullet bsbb = bulletsSub[i];
			Bullet temp = null;
			switch (gamestate) {
			case REBORN:
			case BOSS:
				if (bangBoss_Subunit(bsbb)) {
					temp = bulletsSub[i];
					bulletsSub[i] = bulletsSub[bulletsSub.length - 1];
					bulletsSub[bulletsSub.length - 1] = temp;
					bulletsSub = Arrays.copyOf(bulletsSub,
							bulletsSub.length - 1);
				}
				break;
			}
		}
	}

	/*********************************** 状态检查 ***********************************/
	/**
	 * 检测英雄机生命，是否游戏结束
	 */
	private void checkGameOverAction() {
		if (hero.getLife() <= 0) {
			gamestateold = gamestate;
			gamestate = GAME_OVER;
		}
	}

	/**
	 * 查看当前游戏状态
	 */
	private void checkGameState() {
		switch (gamestate) {
		case START:
			System.out.println("START");
			break;
		case START_ANIME:
			System.out.println("START_ANIME");
			break;
		case REBORN:
			System.out.println("REBORN");
			break;
		case RUNNING:
			System.out.println("RUNNING");
			break;
		case BOSS_READY:
			System.out.println("BOSS_READY");
			break;
		case BOSS_WARNING:
			System.out.println("BOSS_WARNING");
			break;
		case BOSS_INCOME:
			System.out.println("BOSS_INCOME");
			break;
		case BOSS:
			System.out.println("BOSS");
			break;
		case BOSS_OVER:
			System.out.println("BOSS_OVER");
			break;
		case STAGE_CLEAR:
			System.out.println("STAGE_CLEAR");
			break;
		case LOADING:
			System.out.println("LOADING");
			break;
		case GAME_OVER:
			System.out.println("GAME_OVER");
			break;
		case END:
			System.out.println("END");
			break;
		default:
			System.out.println("未录入");
			break;
		}
	}

	/*********************************** 特殊部分 ***********************************/

	private static ImageInfo[] subunitImageInfo() {
		ImageInfo[] imgis = new ImageInfo[31];
		for (int i = 0; i < imgis.length; i++) {
			String name = "subunit" + (i + 1);
			imgis[i] = new ImageInfo(name);
		}
		return imgis;
	}

	private static ImageInfo[] asteroidsImageInfo() {
		ImageInfo[] imgis = new ImageInfo[8];
		for (int i = 0; i < imgis.length; i++) {
			String name = "stone" + (i + 1);
			imgis[i] = new ImageInfo(name);
		}
		return imgis;
	}
}
