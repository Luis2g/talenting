package mx.edu.utez.talenting.dto;

import java.io.Serializable;

import mx.edu.utez.talenting.entity.CertificationOrCourse;
import mx.edu.utez.talenting.entity.Language;
import mx.edu.utez.talenting.entity.Person;
import mx.edu.utez.talenting.entity.Resume;
import mx.edu.utez.talenting.entity.Skill;


public class ResumeDTO implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private Resume resume;
	
	private CertificationOrCourse certificationOrCourse;
	
	private Skill skill;
	
	private Language language;
	
	private Person person;

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public CertificationOrCourse getCertificationOrCourse() {
		return certificationOrCourse;
	}

	public void setCertificationOrCourse(CertificationOrCourse certificationOrCourse) {
		this.certificationOrCourse = certificationOrCourse;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
