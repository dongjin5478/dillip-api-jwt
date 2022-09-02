package com.dillip.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbl_contact_details")
public class ContactDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int contactId;
	private String name;
	private String email;
	private String subject;
	private String message;
	public ContactDetails() {
		super();
	}
	public ContactDetails(int contactId, String name, String email, String subject, String message) {
		super();
		this.contactId = contactId;
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.message = message;
	}
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ContactDetails [name=" + name + ", email=" + email + ", subject=" + subject + ", message=" + message
				+ "]";
	}
}
