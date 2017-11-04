package ShootingGame;

public interface Enemy {
	int SOLIDER1=1;
	int SOLIDER2=2;
	int SOLIDER3=3;
	int ELITE1=4;
	int ELITE2=5;
	int BOSS=10;
	
	int getType();
	int getScore();
}
