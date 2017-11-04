package day04;

import java.util.ArrayList;
import java.util.List;

/**
 * List独有的方法介绍3
 * @author Administrator
 *
 */
public class ListDemo3 {
	public static void main(String[] args) {
		List<Integer> list
			= new ArrayList<Integer>();
		//向集合中添加数字0-9
		for(int i=0;i<10;i++){
			list.add(i);
		}
		System.out.println(list);
		
		/*
		 * 取当前list集合的子集
		 * 3-7
		 * List subList(int start,int end)
		 * 获取当前集合中指定范围内的元素并返回。
		 */
		List<Integer> subList
			= list.subList(3, 8);
		System.out.println(subList);
		/*
		 * 将子集中每个元素扩大10倍
		 */
		for(int i=0;i<subList.size();i++){
			int num = subList.get(i);
			num = num * 10;
			subList.set(i, num);
//			下边一句顶上边三句
//			subList.set(i, subList.get(i) * 10);
		}
		System.out.println(subList);
		/*
		 * 修改子集中的元素就是修改原集合的元素。
		 */
		System.out.println(list);
		
	
		/*
		 * 删除集合中2-8
		 */
		list.subList(2, 9).clear();
		System.out.println(list);
	}
}








