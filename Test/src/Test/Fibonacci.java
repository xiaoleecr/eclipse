package Test;
public class Fibonacci {
	
	public static void main(String[] args) {
			System.out.println(series(40));
	}
	public static double series(int n){
		if(n==0){
			return 0;
		}
		else if(n==1){
			return 1;
		}else{
			return series(n-1)+series(n-2);
		}
		
	
	}
		

}
