package Test;

public class TestExtends {
	public static void main(String[] args){
		Student stu1=new goodStudent();
		Student stu2=new badStudent();
		stu1.haveClass();
		if(stu2 instanceof Student){
			System.out.println(((badStudent)stu2).badStu());
		}
	}
}
class Student{
	public void haveClass(){
		System.out.println("上课");
	}
}

class badStudent extends Student{
	public Object badStu(){
		return"我是坏学生";
	}
}

class goodStudent extends Student{
	public void goodStu(){
		System.out.println("我是坏学生");
	}
}