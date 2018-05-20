package cn.lcr.entity;

public class Student {
	private int sid;
	private String sname;
	private int score;
	
	
	public Student() {
	}
	
	public Student(int sno, String sname) {
		this.sid = sno;
		this.sname = sname;
	}

	public int getSno() {
		return sid;
	}
	public void setSno(int sno) {
		this.sid = sno;
	}
	public String getSname() {
		return sname;
	}
	
	public void setSname(String sname) {
		this.sname = sname;
	}

	public double getSscore() {
		return score;
	}

	public void setSscore(int score) {
		this.score = score;
	}
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", sscore=" + score + "]";
	}
}
