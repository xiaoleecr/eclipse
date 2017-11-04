package day04;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 集合转换为数组
 * Collection提供了一个方法toArray()
 * 可以将当前集合转换为数组
 * @author Administrator
 *
 */
public class CollectionToArrayDemo {
	public static void main(String[] args) {
		Collection<String> c
			= new ArrayList<String>();
		c.add("one");
		c.add("two");
		c.add("three");
		
//		Object[] array = c.toArray();
		/*
		 * toArray方法会判断当前给定的数组是否
		 * 可以存下集合中所有元素，可以就使用，
		 * 不可以就创建一个数组。
		 */
		String[] array 
		  = c.toArray(new String[c.size()]);
		
		for(String s : array){
			System.out.println(s);
		}
		
		
	}
}












