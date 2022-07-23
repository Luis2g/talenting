package mx.edu.utez.talenting.dto;

import mx.edu.utez.talenting.entity.Person;

public class FriendDTO {
	
	private long id;
	
	private Person person;
	
	private String whoSentIt;
	
	public FriendDTO() {
		
	}
	public FriendDTO(Person person) {
		this.person = person;
	}

	public String getWhoSentIt() {
		return whoSentIt;
	}

	public void setWhoSentIt(String whoSentIt) {
		this.whoSentIt = whoSentIt;
	}
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
	
}
