package day04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 使用新循环遍历集合
 * @author Administrator
 *
 */
public class NewForDemo2 {
	public static void main(String[] args) {
		Collection c = new ArrayList();
		c.add("one");
		c.add("#");
		c.add("two");
		c.add("#");
		c.add("three");
		/*
		 * 新循环是编译器认可的，不是虚拟机
		 * 编译器在编译源程序时，会将使用新循环
		 * 遍历集合的方式改为使用迭代器。所以
		 * 在新循环内部不能通过集合的方法删除
		 * 集合元素。
		 */
		for(Object o : c){
			String str = (String)o;
			System.out.println(str);
		}
		
	}
}






