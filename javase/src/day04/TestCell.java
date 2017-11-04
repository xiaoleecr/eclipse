package day04;

public class TestCell {
	public static void main(String[] args) {
		Cell<Integer,Integer> c1 
				= new Cell<Integer,Integer>(1,2);
		System.out.println(c1);
		Integer x1 = c1.getX();
		
		Cell<Double,Double> c2
				= new Cell<Double,Double>(1.1,2.2);
		System.out.println(c2);
		Double x2 = c2.getX();
		
		Cell<String,Double> c3
				= new Cell<String,Double>("һ",2.2);
		System.out.println(c3);
		String x3 = c3.getX();
		Double y3 = c3.getY();
	}
}



