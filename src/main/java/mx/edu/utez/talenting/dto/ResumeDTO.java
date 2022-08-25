package mx.edu.utez.talenting.dto;

import java.io.Serializable;
import java.util.List;

import mx.edu.utez.talenting.entity.CertificationOrCourse;
import mx.edu.utez.talenting.entity.Language;
import mx.edu.utez.talenting.entity.Resume;
import mx.edu.utez.talenting.entity.Skill;


public class ResumeDTO implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private Resume resume;
	
	private  List<CertificationOrCourse> certificationOrCourse;
	
	private List<Skill> skill;
	
	private List<Language> language;
	
	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public List<CertificationOrCourse> getCertificationOrCourse() {
		return certificationOrCourse;
	}

	public void setCertificationOrCourse(List<CertificationOrCourse> certificationOrCourse) {
		this.certificationOrCourse = certificationOrCourse;
	}

	public List<Skill> getSkill() {
		return skill;
	}

	public void setSkill(List<Skill> skill) {
		this.skill = skill;
	}

	public List<Language> getLanguage() {
		return language; 		
	}

	public void setLanguage(List<Language> language) {
		this.language = language;
	}

}
