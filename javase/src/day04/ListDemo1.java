package day04;

import java.util.ArrayList;
import java.util.List;

/**
 * List�̳���Collection�ӿ�
 * List�ǿ��ظ�������������
 * List���ص�:���Ը����±����Ԫ�أ����������
 * ����ʵ����:ArrayList,LinkedList
 * @author Administrator
 *
 */
public class ListDemo1 {
	public static void main(String[] args) {
		//java.util.List
		List<String> list 
			= new ArrayList<String>();
		//�򼯺����Ԫ��
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		
		/*
		 * List�ṩ��һ��get,set����
		 * ���Ը����±����Ԫ��
		 * 
		 * E get(int index)
		 * ��ȡ��ǰ�����и����±��Ӧ��Ԫ��
		 */
		System.out.println(list);
		//��ȡ�ڶ���Ԫ��
		String str = list.get(1);
		System.out.println(str);
		
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		
		/*
		 * E set(int index,E e)
		 * ��������Ԫ�����õ�������λ���ϣ�����ֵ
		 * Ϊԭλ���ϵ�Ԫ�ء�
		 * �滻Ԫ�ز�����
		 */
		//[one,��,three,four]
		String old = list.set(1, "��");
		System.out.println(list);
		System.out.println("���滻����:"+old);
	}
}







