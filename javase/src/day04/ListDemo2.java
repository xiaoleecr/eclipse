package day04;

import java.util.LinkedList;
import java.util.List;

/**
 * List���з�������2
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
		 * ��������Ԫ����ӵ�������λ���ϣ�ԭλ��
		 * �ϼ�����Ԫ�ض�˳������ƶ�
		 * �������
		 */
		//[one,two,three]
		System.out.println(list);
		//[one,2,two,three]
		list.add(1,"2");
		System.out.println(list);
		
		/*
		 * E remove(int index)
		 * ɾ����ǰ�����и���λ�ö�Ӧ��Ԫ�أ�����ֵ
		 * Ϊ��ɾ����Ԫ��
		 */
		//[one,2,three]
		String old = list.remove(2);
		System.out.println(list);
		System.out.println("��ɾ������:"+old);
	}
}









