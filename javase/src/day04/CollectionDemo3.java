package day04;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

/**
 * ɾ�������е�Ԫ��2
 * @author Administrator
 *
 */
public class CollectionDemo3 {
	public static void main(String[] args) {
		Collection c = new ArrayList();
		c.add(new Point(1,2));
		c.add(new Point(3,4));
		c.add(new Point(5,6));
		c.add(new Point(1,2));
		System.out.println(c);		
		Point p = new Point(1,2);
		/*
		 * �������������Ԫ��equals�Ƚ�Ϊtrue
		 * �ĵ�һ��Ԫ��ɾ����
		 */
		c.remove(p);
		
		System.out.println(c);
	}
}



