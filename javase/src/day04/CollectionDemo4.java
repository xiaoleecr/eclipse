package day04;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 遍历集合
 * 集合提供了统一的遍历方式:迭代器模式
 * 集合提供了一个方法
 * Iterator iterator()
 * 该方法会获取一个可以遍历当前集合的迭代器实例。
 * 
 * 需要注意的是Iterator本身是一个接口，不同的集合
 * 提供了可以遍历自身的迭代器实现类。但是从该方法
 * 我们可以看出，我们无需记住具体迭代器名字，只需要
 * 将它看做是Iterator即可
 * 使用迭代器遍历集合遵循:问取删,其中删除不是必须的
 * @author Administrator
 *
 */
public class CollectionDemo4 {
	public static void main(String[] args) {
		Collection c = new HashSet();
		c.add("one");
		c.add("#");
		c.add("two");
		c.add("#");
		c.add("three");
		c.add("#");
		c.add("four");
		
		System.out.println(c);
		
		/*
		 * 若想遍历集合，先获取迭代器
		 */
		//java.util.Iterator
		Iterator it = c.iterator();
		
		/*
		 * boolean hasNext()
		 * 问的工作
		 * 询问迭代器，集合是否还有元素可以遍历
		 */
		while(it.hasNext()){
			/*
			 * E next()
			 * 取的工作
			 * 通过迭代器从集合中取出元素
			 */
			Object o = it.next();
			String str = (String)o;
//			System.out.println(str);
			if("#".equals(str)){
//				c.remove(str);
				/*
				 * 需要注意，在使用迭代器遍历
				 * 集合的过程中，不得使用集合
				 * 的方法删除元素。
				 * 只能使用迭代器提供的方法删除。
				 * 该方法会删除通过next方法获取
				 * 的元素。
				 */
				it.remove();				
			}
		}
		System.out.println(c);
	}	
}













