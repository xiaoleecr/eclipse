package Test;

public class Test implements Runnable{
	A a1=new A();
	A a2=new A();
	public static void main(String[] args) {
		Test t=new Test();
		
	}
	public void run() {
		a1.prt();
		a2.prt();
	}
}
class A{
	private static int i;
	public void prt(){
		try {
			Thread.sleep(1);
			System.out.println( "第"+(i++)+"个调用者");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
	}
