package day04;
/**
 * 泛型的实际类型是Object，只是告知编译器应当把它
 * 当做什么类型而已。
 * @author Administrator
 *
 */
public class TestCell3 {
	public static void main(String[] args) {
		Cell<Integer,Integer> c1
		 = new Cell<Integer,Integer>(1,2);
		
		Integer x1 = c1.getX();		
		System.out.println(x1);
		
		Cell c2 = c1;
		c2.setX("一");
		System.out.println(c2.getX());
		
		//ClassCastException
		x1 = c1.getX();
		
	}
}



