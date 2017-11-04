package day04;
/**
 * 新循环，也叫做增强for循环或for each
 * 自java5.0以后推出的一个新特性。
 * 
 * 新循环是用来遍历集合或数组的，不适用于传统循环
 * 的工作。
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










