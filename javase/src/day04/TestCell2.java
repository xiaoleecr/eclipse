package day04;
/**
 * Cell支持泛型，但是使用时若不指定泛型的具体
 * 类型时，默认认为是Object
 * @author Administrator
 *
 */
public class TestCell2 {
	public static void main(String[] args) {
		Cell c = new Cell(1,2);
		System.out.println(c);
		Integer x = (Integer)c.getX();
	}
}



