package day04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * ���ϵ���������
 * boolean addAll(Collection c)
 * �������ļ����е�����Ԫ����ӵ���ǰ�����С�
 * �����ø÷���������ǰ���ϵ�Ԫ�ز����˱仯�ͷ���
 * true��
 * 
 * boolean containsAll(Collection c)
 * �жϵ�ǰ�����Ƿ�������������е�����Ԫ�ء�
 * ȫ�������ͷ���true������Ҳ������Ԫ��equals����
 * �ȽϵĽ����
 * 
 * @author Administrator
 *
 */
public class CollectionDemo1 {
	public static void main(String[] args) {
		Collection c1 = new HashSet();
		c1.add("java");
		c1.add("php");
		c1.add(".net");
		
		Collection c2 = new ArrayList();
		c2.add("objective-c");
		c2.add("c");
		c2.add("vb");
		c2.add("java");
		/*
		 * ��c2�е�����Ԫ����ӵ�c1������
		 */
		c1.addAll(c2);
		System.out.println(c1);
		
		Collection c3 = new ArrayList();
		c3.add(".net");
		c3.add("php");
		c3.add("java");
		/*
		 * �鿴c1���Ƿ����c3�е�����Ԫ��
		 */
		boolean contains = c1.containsAll(c3);
		System.out.println("ȫ����:"+contains);
		
		
	}
}














