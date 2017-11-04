package ShootingGame;

public interface Award {
	/**
	 * 普通火力
	 */
	int NORMAL=0;
	/**
	 * 散射火力
	 */
	int SCATTER=1;
	/**
	 * 生命
	 */
	int LIFE=2;
	/**
	 * 僚机
	 */
	int SUBUNIT=3;
	/**
	 * 炸弹
	 */
	int BOMB=4;
	/**
	 * 返回类型
	 * @return
	 */
	int getType();
}
