package com.jda.wms.context;

import java.math.BigDecimal;
import java.util.List;

import com.jda.wms.pages.foods.RDTTask;

public class Staff {

	private String name;
	private int age;
	private String position;
	private double salary;
	private List<String> skills;
	
	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public double getsalary() {
		return salary;
	}

	public void setsalary(double salary) {
		this.salary = salary;
	}
	
	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
}

