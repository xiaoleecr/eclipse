package day04;
/**
 * ���͵�ʵ��������Object��ֻ�Ǹ�֪������Ӧ������
 * ����ʲô���Ͷ��ѡ�
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
		c2.setX("һ");
		System.out.println(c2.getX());
		
		//ClassCastException
		x1 = c1.getX();
		
	}
}



