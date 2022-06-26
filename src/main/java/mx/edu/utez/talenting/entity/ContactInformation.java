package mx.edu.utez.talenting.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class ContactInformation implements Serializable{

	 private static final long serialVersionUID = 1L;
	 
	 @Id
	 private long id;
	 
	 @Column(nullable = false, name = "phone_number")
	 private String phoneNumber;
	 
	 @Column(nullable = false)
	 private String email;
	 
	 @Column(nullable = false)
	 private String state;
	 
	// Configuration for the person entity
	@OneToOne(mappedBy = "contactInformation")
	@JsonIgnore
	private Person person;		

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
