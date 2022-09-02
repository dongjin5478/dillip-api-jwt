package com.dillip.api.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_person")
public class Person {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private int age;
	private String gender;
	
	@OneToMany(targetEntity = Product.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "perpro_fk",referencedColumnName = "id")
	private List<Product> product;
	
	
	public Person() {
		super();
	}
	
	

	public Person(int id, String name, int age, String gender, List<Product> product) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.product = product;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}
	
	

	public List<Product> getProduct() {
		return product;
	}



	public void setProduct(List<Product> product) {
		this.product = product;
	}



	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}
}
