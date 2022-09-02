package com.dillip.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_student")
public class Student {
	
	@Column(name = "student_name")
	private String studentName;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)       
	@Column(name = "roll_no")
	private int rollNo;
	
	@Column(name = "mobile_number")
	private long mobileNumber;
	
	private int age;
	
	private String gender;

	public Student() {
		super();
	}

	public Student(String studentName, int rollNo, long mobileNumber, int age, String gender) {
		super();
		this.studentName = studentName;
		this.rollNo = rollNo;
		this.mobileNumber = mobileNumber;
		this.age = age;
		this.gender = gender;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Student [studentName=" + studentName + ", rollNo=" + rollNo + ", mobileNumber=" + mobileNumber
				+ ", age=" + age + ", gender=" + gender + "]";
	}
	
	
}
