package day04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * ʹ����ѭ����������
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
		 * ��ѭ���Ǳ������Ͽɵģ����������
		 * �������ڱ���Դ����ʱ���Ὣʹ����ѭ��
		 * �������ϵķ�ʽ��Ϊʹ�õ�����������
		 * ����ѭ���ڲ�����ͨ�����ϵķ���ɾ��
		 * ����Ԫ�ء�
		 */
		for(Object o : c){
			String str = (String)o;
			System.out.println(str);
		}
		
	}
}






