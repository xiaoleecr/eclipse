package day04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 泛型在集合中的应用
 * 泛型用来声明集合中的元素类型
 * @author Administrator
 *
 */
public class CollectionDemo5 {
	public static void main(String[] args) {
		Collection<String> c 
				= new ArrayList<String>();
		c.add("one");
		c.add("two");
		c.add("three");
		//迭代器的泛型类型应当与其遍历的集合一致
		Iterator<String> it = c.iterator();
		while(it.hasNext()){
			String str = it.next();
			System.out.println(str);
		}
		
		for(String str : c){
			System.out.println(str);
		}
	}
}








