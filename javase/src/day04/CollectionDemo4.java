package day04;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * ��������
 * �����ṩ��ͳһ�ı�����ʽ:������ģʽ
 * �����ṩ��һ������
 * Iterator iterator()
 * �÷������ȡһ�����Ա�����ǰ���ϵĵ�����ʵ����
 * 
 * ��Ҫע�����Iterator������һ���ӿڣ���ͬ�ļ���
 * �ṩ�˿��Ա�������ĵ�����ʵ���ࡣ���ǴӸ÷���
 * ���ǿ��Կ��������������ס������������֣�ֻ��Ҫ
 * ����������Iterator����
 * ʹ�õ���������������ѭ:��ȡɾ,����ɾ�����Ǳ����
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
		 * ����������ϣ��Ȼ�ȡ������
		 */
		//java.util.Iterator
		Iterator it = c.iterator();
		
		/*
		 * boolean hasNext()
		 * �ʵĹ���
		 * ѯ�ʵ������������Ƿ���Ԫ�ؿ��Ա���
		 */
		while(it.hasNext()){
			/*
			 * E next()
			 * ȡ�Ĺ���
			 * ͨ���������Ӽ�����ȡ��Ԫ��
			 */
			Object o = it.next();
			String str = (String)o;
//			System.out.println(str);
			if("#".equals(str)){
//				c.remove(str);
				/*
				 * ��Ҫע�⣬��ʹ�õ���������
				 * ���ϵĹ����У�����ʹ�ü���
				 * �ķ���ɾ��Ԫ�ء�
				 * ֻ��ʹ�õ������ṩ�ķ���ɾ����
				 * �÷�����ɾ��ͨ��next������ȡ
				 * ��Ԫ�ء�
				 */
				it.remove();				
			}
		}
		System.out.println(c);
	}	
}













