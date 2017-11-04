package day04;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 删除集合中的元素2
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
		 * 将集合中与给定元素equals比较为true
		 * 的第一个元素删除。
		 */
		c.remove(p);
		
		System.out.println(c);
	}
}



