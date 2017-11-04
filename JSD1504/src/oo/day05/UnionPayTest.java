package oo.day05;

public class UnionPayTest {
	public static void main(String[] args) {

	}

}

interface UnionPay {// 银联接口
	public double getBalance();

	public boolean drawMoney(double number);

	public boolean checkPwd(String input);
}

interface ICBC extends UnionPay {
	public void PayOnline(double number);

}

interface ABC extends UnionPay {

	public boolean payTelBill(String phoneNumber, double sum);
}

class ICBCImpl implements ICBC {
	private double money;
	
	private String Pwd;

	public ICBCImpl(double money, String Pwd) {
		this.money = money;
		this.Pwd = Pwd;
	}
	
	public double getBalance(){
		return money;
	}
	
	public boolean checkPwd(String input) {
		return 0;
	}

}

class ABCImpl implements ABC {

}