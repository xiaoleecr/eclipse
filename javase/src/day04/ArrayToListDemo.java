package day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ����ת��ΪList����
 * ����Set���ϲ��ܴ���ظ�Ԫ�أ����Կ��ܵ���
 * ����ת��ΪSet���Ϻ�ʧԪ�أ�����Set�Ͳ���
 * ��ȫ��ʾԭ�������ݣ��ʴ����鲻��ת��ΪSet����
 * @author Administrator
 *
 */
public class ArrayToListDemo {
	public static void main(String[] args) {
		String[] array = {"one","two","three"};
		
		/*
		 * Arrays.asList()
		 * ������������ת��ΪList����
		 */
		List<String> list 
				= Arrays.asList(array);
		System.out.println(list);
		//ת��ļ��ϲ��������Ԫ��
//		list.add("four");
//		System.out.println(list);
		/*
		 * �޸ļ�������Ԫ�أ�ԭ�����Ӧ��Ԫ��Ҳ
		 * ���ı��ˡ�
		 */
		list.set(0, "1");
		System.out.println(list);		
		for(String str : array){
			System.out.println(str);
		}
		
		/*
		 * �����򼯺��������Ԫ�ء�
		 * ֻ�ܴ���һ���¼���
		 * ���еļ��϶�֧��һ�����췽��������Ϊ
		 * һ�����ϡ�������췽����������:
		 * ������ǰ���ϣ����ҵ�ǰ�����л����
		 * ���������е�����Ԫ�ء�
		 */
		List<String> list1 
			= new ArrayList<String>(list);
		System.out.println(list1);
		list1.add("four");
		System.out.println(list1);
	}
}








