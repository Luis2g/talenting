package mx.edu.utez.talenting.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table
@Entity(name = "people")
public class Person implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String surname;
	@Column
	private String secondSurname;
	
	@Column(nullable = false)
	private String dateOfBirth;
	
	public Person() {
		
	}
	public Person(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getSecondSurname() {
		return secondSurname;
	}
	public void setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	//Foreign key for contact_information
	@OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name = "contactInformation", unique = true)
	private ContactInformation contactInformation;
	
	//Configuration for employeer
	@OneToOne(mappedBy = "person")
	@JsonIgnore
	private Employeer employeer;
	
	//Configuration for person in friend entity
	@OneToMany(mappedBy = "person")
	@JsonIgnore
	private List<Friend> friends;
	
	//Configuration for friend in friend entity
	@OneToMany(mappedBy = "friend")
	@JsonIgnore
	private List<Friend> persons;
	
	//Configuration for favorite_vacancies
	@OneToMany(mappedBy = "person")
	@JsonIgnore
	private List<FavoriteVacancy> favoritesVacancies;
	
	//Configuration for resume
	@OneToOne(mappedBy = "person")
	@JsonIgnore
	private Resume resume;
	
	//Configuration for sharedVacancies
	@OneToMany(mappedBy = "person")
	@JsonIgnore
	private List<SharedVacancy> sharedVacancies;

	public ContactInformation getContactInformation() {
		return contactInformation;
	}
	
	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}
	
	//Configuration for User
	@OneToOne(mappedBy = "person")
	@JsonIgnore
	private User user;
	
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", surname=" + surname + ", secondSurname=" + secondSurname
				+ ", dateOfBirth=" + dateOfBirth + ", contactInformation=" + contactInformation + ", employeer="
				+ employeer + ", friends=" + friends + ", persons=" + persons + ", favoritesVacancies="
				+ favoritesVacancies + ", resume=" + resume + ", sharedVacancies=" + sharedVacancies + "]";
	}
	
	
}
