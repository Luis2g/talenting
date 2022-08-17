package mx.edu.utez.talenting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.talenting.dto.ResumeDTO;
import mx.edu.utez.talenting.entity.Resume;
import mx.edu.utez.talenting.entity.User;
import mx.edu.utez.talenting.repository.CertificationOrCourseRepository;
import mx.edu.utez.talenting.repository.LanguageRepository;
import mx.edu.utez.talenting.repository.ResumeRepository;
import mx.edu.utez.talenting.repository.SkillRepository;

@Service
public class ResumeService {

	@Autowired
	private ResumeRepository resumeRepo;
	
	@Autowired
	private CertificationOrCourseRepository certificationOrCourseRepo;
	
	@Autowired
	private SkillRepository skillRepo;
	
	@Autowired
	private LanguageRepository languageRepo;
	
	public List<Resume> getAll(){
		return resumeRepo.findAll();
	}
	
	public Resume getOne(long id) {
		return resumeRepo.findById(id).get();
	}
	
	public Resume saveOrUpdate(Resume cerficationOrCourse) {
		return resumeRepo.save(cerficationOrCourse);
	}
	
	public Resume save(Resume resume) {
		return resumeRepo.save(resume);
	}
	
	public void remove(long id) {
		resumeRepo.deleteById(id);
	}
	
	public ResumeDTO findByPersonId(long id) {
		ResumeDTO resumeDTO = resumeRepo.findByPersonId(id);
		resumeDTO.setLanguage(languageRepo.findByResumeId(resumeDTO.getResume().getId()));
		resumeDTO.setSkill(skillRepo.findByResumeId(resumeDTO.getResume().getId()));
		resumeDTO.setCertificationOrCourse(certificationOrCourseRepo.findByResumeId(resumeDTO.getResume().getId()));
		return resumeDTO;
	}
	
}
