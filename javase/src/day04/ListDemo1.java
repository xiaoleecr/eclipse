package day04;

import java.util.ArrayList;
import java.util.List;

/**
 * List继承自Collection接口
 * List是可重复集，并且有序。
 * List的特点:可以根据下标操作元素，和数组很像。
 * 常用实现类:ArrayList,LinkedList
 * @author Administrator
 *
 */
public class ListDemo1 {
	public static void main(String[] args) {
		//java.util.List
		List<String> list 
			= new ArrayList<String>();
		//向集合添加元素
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		
		/*
		 * List提供了一组get,set方法
		 * 可以根据下标操作元素
		 * 
		 * E get(int index)
		 * 获取当前集合中给定下标对应的元素
		 */
		System.out.println(list);
		//获取第二个元素
		String str = list.get(1);
		System.out.println(str);
		
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		
		/*
		 * E set(int index,E e)
		 * 将给定的元素设置到给定的位置上，返回值
		 * 为原位置上的元素。
		 * 替换元素操作。
		 */
		//[one,二,three,four]
		String old = list.set(1, "二");
		System.out.println(list);
		System.out.println("被替换的是:"+old);
	}
}







