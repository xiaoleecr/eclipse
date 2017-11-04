package org.tarena.note.entity;

import java.io.Serializable;

public class Emp implements Serializable{
	private Integer id ;
	private String name;
	private Double salary;
	private Integer age;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "Emp [age=" + age + ", id=" + id + ", name=" + name
				+ ", salary=" + salary + "]";
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	

}
