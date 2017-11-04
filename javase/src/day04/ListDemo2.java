package day04;

import java.util.LinkedList;
import java.util.List;

/**
 * List独有方法介绍2
 * @author Administrator
 *
 */
public class ListDemo2 {
	public static void main(String[] args) {
		List<String> list
			= new LinkedList<String>();
		list.add("one");
		list.add("two");
		list.add("three");
		/*
		 * void add(int index,E e)
		 * 将给定的元素添加到给定的位置上，原位置
		 * 上及后续元素都顺序向后移动
		 * 插入操作
		 */
		//[one,two,three]
		System.out.println(list);
		//[one,2,two,three]
		list.add(1,"2");
		System.out.println(list);
		
		/*
		 * E remove(int index)
		 * 删除当前集合中给定位置对应的元素，返回值
		 * 为被删除的元素
		 */
		//[one,2,three]
		String old = list.remove(2);
		System.out.println(list);
		System.out.println("被删除的是:"+old);
	}
}









