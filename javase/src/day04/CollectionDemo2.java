package day04;

import java.util.ArrayList;
import java.util.Collection;

/**
 * ɾ������Ԫ��
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
		 * ɾ��Ԫ��"two"
		 * boolean remove(E e)
		 * �ӵ�ǰ������ɾ��������Ԫ�أ���ɾ��
		 * �ɹ�����true
		 */
		c.remove("two");
		System.out.println(c);
		
	}
}







