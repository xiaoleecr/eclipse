package day04;
/**
 * ��ѭ����Ҳ������ǿforѭ����for each
 * ��java5.0�Ժ��Ƴ���һ�������ԡ�
 * 
 * ��ѭ���������������ϻ�����ģ��������ڴ�ͳѭ��
 * �Ĺ�����
 * @author Administrator
 *
 */
public class NewForDemo {
	public static void main(String[] args) {
		String[] array = {"a","b","c","d"};		
		for(int i=0;i<array.length;i++){
			System.out.println(array[i]);
		}
		
		for(String str:array){
			System.out.println(str);
		}
	}
}










