package mx.edu.utez.talenting.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "resumes")
public class Resume implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(name = "professional_description", nullable = false)
	private String professionalDescription;
	
	@Column(name = "has_experience", nullable = false)
	private boolean hasExperience;
	
	@Column(nullable = false)
	private String expertise;
	
	@Column(name = "school_preparation", nullable = false)
	private String school_preparation;
	
	//Configuration for cetifications or courses
	@OneToMany(mappedBy = "resume")
	@JsonIgnore
	private List<CertificationOrCourse> certificationOrCourse;
	
	//Configuration for skills
	@OneToMany(mappedBy = "resume")
	@JsonIgnore
	private List<Skill> skills;
	
	//Configuration for languages
	@OneToMany(mappedBy = "resume")
	@JsonIgnore
	private List<Language> laguagues;
	
	//Foreign key for person
	@OneToOne
	@JoinColumn(name = "person")
	private Person person;
	
	
	
}
