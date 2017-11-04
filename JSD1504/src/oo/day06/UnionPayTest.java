package oo.day06;

public class UnionPayTest {
	public static void main(String[] args) {
		ABCATM atm = new ABCATM();
		UnionPay card = new ABCImpl();
		atm.insertCard(card);
		atm.payTelBill();
	}

}

class ABCATM {
	private UnionPay card;

	public void insertCard(UnionPay card) {
		this.card = card;
	}

	public void payTelBill() {
		if (card instanceof ABC) {// 是否是农行卡
			ABC abcCard = (ABC) card;
			abcCard.payTelBill("2345678", 2);
		} else {
			System.out.println("非农行卡无法支付");
		}
	}

	public void quqian() {
	}

	public void cunqian() {
	}

	public void zhuanzhang() {
	}

}

class ABCImpl implements ABC {
	public double getBalance() {
		return 0.0;
	}

	public boolean drawMoney(double number) {
		return true;
	}

	public boolean checkPwd(String input) {
		return true;
	}

	public boolean payTelBill(String phoneNum, double sum) {
		System.out.println("支付电话费成功");
		return true;
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

	public double getBalance() {
		return money;
	}

	public boolean checkPwd(String input) {
		return true;
	}

	public void PayOnline(double number) {

	}

	public boolean drawMoney(double number) {
		return true;
	}

}
