package mx.edu.utez.talenting.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "appliers_in_vacancies")
public class ApplierInVacancy implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String status;
	
	//Foreign key for vacancies
	@ManyToOne
	@JoinColumn(name = "vacancy", nullable = false)
	private Vacancy vacancy;
	
	//Foreign key for people
	@ManyToOne
	@JoinColumn(name = "person", nullable = false)
	private Person person;
	
	public ApplierInVacancy () {
		
	}
	
	public ApplierInVacancy (Vacancy vacancy, Person person) {
		this.vacancy = vacancy;
		this.person = person;
		this.status = "postulado";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Vacancy getVacancy() {
		return vacancy;
	}

	public void setVacancy(Vacancy vacancy) {
		this.vacancy = vacancy;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
		
	
}
