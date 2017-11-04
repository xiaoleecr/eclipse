package day04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * 集合的批量操作
 * boolean addAll(Collection c)
 * 将给定的集合中的所有元素添加到当前集合中。
 * 当调用该方法后若当前集合的元素产生了变化就返回
 * true。
 * 
 * boolean containsAll(Collection c)
 * 判断当前集合是否包含给定集合中的所有元素。
 * 全部包含就返回true。包含也是依靠元素equals方法
 * 比较的结果。
 * 
 * @author Administrator
 *
 */
public class CollectionDemo1 {
	public static void main(String[] args) {
		Collection c1 = new HashSet();
		c1.add("java");
		c1.add("php");
		c1.add(".net");
		
		Collection c2 = new ArrayList();
		c2.add("objective-c");
		c2.add("c");
		c2.add("vb");
		c2.add("java");
		/*
		 * 将c2中的所有元素添加到c1集合中
		 */
		c1.addAll(c2);
		System.out.println(c1);
		
		Collection c3 = new ArrayList();
		c3.add(".net");
		c3.add("php");
		c3.add("java");
		/*
		 * 查看c1中是否包含c3中的所有元素
		 */
		boolean contains = c1.containsAll(c3);
		System.out.println("全包含:"+contains);
		
		
	}
}














