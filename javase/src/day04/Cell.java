package day04;
/**
 * 想表示一个点
 * @author Administrator
 */
public class Cell<X,Y> {
	private X x;
	private Y y;
	public Cell(X x, Y y) {
		this.x = x;
		this.y = y;
	}
	public X getX() {
		return x;
	}
	public void setX(X x) {
		this.x = x;
	}
	public Y getY() {
		return y;
	}
	public void setY(Y y) {
		this.y = y;
	}
	public String toString(){
		return "("+x+","+y+")";
	}
}




