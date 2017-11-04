package oo.day01;

public class Student {
	String name;
	int age;
	String address;
	void study(){
		System.out.println(name+" is studying");
	}
	void sayHi(){
		System.out.println("Hello my name is "+name+" I'm "+age+" years old,I live in "+address+".");
	}
}
