package day04;

import java.util.ArrayList;
import java.util.Collection;

/**
 * ����ת��Ϊ����
 * Collection�ṩ��һ������toArray()
 * ���Խ���ǰ����ת��Ϊ����
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
		 * toArray�������жϵ�ǰ�����������Ƿ�
		 * ���Դ��¼���������Ԫ�أ����Ծ�ʹ�ã�
		 * �����Ծʹ���һ�����顣
		 */
		String[] array 
		  = c.toArray(new String[c.size()]);
		
		for(String s : array){
			System.out.println(s);
		}
		
		
	}
}












