package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
	private String code;
	private String name;
	private String lastname;
	private Sex sex;
	private Date date;
	private double height;
	private String nationality;
	private String dateString;
	
	public Person(String code ,String name, String lastname, Sex sex, Date date, double height, String nationality) {
		this.code = code;
		this.name = name;
		this.lastname = lastname;
		this.sex = sex;
		this.date = date;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		this.dateString = sdf.format(this.date);
		
		this.height = height;
		this.nationality = nationality;
	}
	public Person(String code, String name, String lastName, Sex sex) {
		this.name = name;
		this.lastname = lastName;
		this.sex = sex;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	@Override
	public String toString() {
		return name+" "+lastname+" "+sex+" "+" "+date+" "+height+" "+nationality;
	}
}
