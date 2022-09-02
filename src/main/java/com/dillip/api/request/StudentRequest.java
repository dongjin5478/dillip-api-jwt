package com.dillip.api.request;

public class StudentRequest {

	private String name;
	private int age;
	private long mobileNumber;
	private String gender;
	
	public StudentRequest(String name, int age,long mobileNumber, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
	}
	public StudentRequest() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "StudentRequest [name=" + name + ", age=" + age + ", mobileNumber=" + mobileNumber + ", gender=" + gender
				+ "]";
	}
}
