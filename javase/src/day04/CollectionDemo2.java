package day04;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 删除集合元素
 * @author Administrator
 *
 */
public class CollectionDemo2 {
	public static void main(String[] args) {
		Collection c = new ArrayList();
		c.add("one");
		c.add("two");
		c.add("three");
		c.add("four");
		
		System.out.println(c);
		
		/*
		 * 删除元素"two"
		 * boolean remove(E e)
		 * 从当前集合中删除给定的元素，若删除
		 * 成功返回true
		 */
		c.remove("two");
		System.out.println(c);
		
	}
}







