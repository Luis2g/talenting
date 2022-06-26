package mx.edu.utez.talenting.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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
@Entity
public class Employeer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String companyName;
	private String stateTheCompanyIsIn;
	private String rolInCompany;
	
	// foreign key for person
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id", nullable = false, unique = true)
	private Person person;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getStateTheCompanyIsIn() {
		return stateTheCompanyIsIn;
	}
	public void setStateTheCompanyIsIn(String stateTheCompanyIsIn) {
		this.stateTheCompanyIsIn = stateTheCompanyIsIn;
	}
	public String getRol_in_company() {
		return rolInCompany;
	}
	public void setRol_in_company(String rolInCompany) {
		this.rolInCompany = rolInCompany;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRolInCompany() {
		return rolInCompany;
	}
	public void setRolInCompany(String rolInCompany) {
		this.rolInCompany = rolInCompany;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public List<PostedVacancy> getPostedVacancies() {
		return postedVacancies;
	}
	public void setPostedVacancies(List<PostedVacancy> postedVacancies) {
		this.postedVacancies = postedVacancies;
	}



	//Configuration for posted_vacancies
	@OneToMany(mappedBy = "employeer")
	@JsonIgnore
	private List<PostedVacancy> postedVacancies;
	
	
}
