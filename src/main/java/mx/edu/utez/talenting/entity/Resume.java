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
	
	@Column(name = "profile_image", nullable = true)
	private String profileImage;
	
	@Column(name = "pdf_resume", nullable = true)
	private String PDFResume;
	
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProfessionalDescription() {
		return professionalDescription;
	}

	public void setProfessionalDescription(String professionalDescription) {
		this.professionalDescription = professionalDescription;
	}

	public boolean isHasExperience() {
		return hasExperience;
	}

	public void setHasExperience(boolean hasExperience) {
		this.hasExperience = hasExperience;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public String getSchool_preparation() {
		return school_preparation;
	}

	public void setSchool_preparation(String school_preparation) {
		this.school_preparation = school_preparation;
	}
	
	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getPDFResume() {
		return PDFResume;
	}

	public void setPDFResume(String pDFResume) {
		PDFResume = pDFResume;
	}

	public List<CertificationOrCourse> getCertificationOrCourse() {
		return certificationOrCourse;
	}

	public void setCertificationOrCourse(List<CertificationOrCourse> certificationOrCourse) {
		this.certificationOrCourse = certificationOrCourse;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<Language> getLaguagues() {
		return laguagues;
	}

	public void setLaguagues(List<Language> laguagues) {
		this.laguagues = laguagues;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Resume [id=" + id + ", title=" + title + ", professionalDescription=" + professionalDescription
				+ ", hasExperience=" + hasExperience + ", expertise=" + expertise + ", school_preparation="
				+ school_preparation + ", laguagues=" + laguagues + "]";
	}

	
	
	
	
}
