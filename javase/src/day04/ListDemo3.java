package day04;

import java.util.ArrayList;
import java.util.List;

/**
 * List���еķ�������3
 * @author Administrator
 *
 */
public class ListDemo3 {
	public static void main(String[] args) {
		List<Integer> list
			= new ArrayList<Integer>();
		//�򼯺����������0-9
		for(int i=0;i<10;i++){
			list.add(i);
		}
		System.out.println(list);
		
		/*
		 * ȡ��ǰlist���ϵ��Ӽ�
		 * 3-7
		 * List subList(int start,int end)
		 * ��ȡ��ǰ������ָ����Χ�ڵ�Ԫ�ز����ء�
		 */
		List<Integer> subList
			= list.subList(3, 8);
		System.out.println(subList);
		/*
		 * ���Ӽ���ÿ��Ԫ������10��
		 */
		for(int i=0;i<subList.size();i++){
			int num = subList.get(i);
			num = num * 10;
			subList.set(i, num);
//			�±�һ�䶥�ϱ�����
//			subList.set(i, subList.get(i) * 10);
		}
		System.out.println(subList);
		/*
		 * �޸��Ӽ��е�Ԫ�ؾ����޸�ԭ���ϵ�Ԫ�ء�
		 */
		System.out.println(list);
		
	
		/*
		 * ɾ��������2-8
		 */
		list.subList(2, 9).clear();
		System.out.println(list);
	}
}








