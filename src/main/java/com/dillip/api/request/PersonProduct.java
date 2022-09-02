package com.dillip.api.request;

import com.dillip.api.entity.Person;

public class PersonProduct {
	private Person person;

	public PersonProduct() {
		super();
	}

	public PersonProduct(Person person) {
		super();
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
