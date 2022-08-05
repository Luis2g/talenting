package mx.edu.utez.talenting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.utez.talenting.dto.ResumeDTO;
import mx.edu.utez.talenting.entity.CertificationOrCourse;
import mx.edu.utez.talenting.entity.Language;
import mx.edu.utez.talenting.entity.Resume;
import mx.edu.utez.talenting.entity.Skill;
import mx.edu.utez.talenting.helper.Encrypt;
import mx.edu.utez.talenting.service.CertificationOrCourseService;
import mx.edu.utez.talenting.service.LanguageService;
import mx.edu.utez.talenting.service.ResumeService;
import mx.edu.utez.talenting.service.SkillService;

@RestController
@RequestMapping("/talenting")
@CrossOrigin(origins = "http://127.0.0.1:8081")
public class ResumeController {
	
	@Autowired
	private ResumeService resumeSer;
	
	@Autowired
	private CertificationOrCourseService certificationOrCourseSer;
	
	@Autowired
	private SkillService skillSer;
	
	@Autowired
	private LanguageService languageSer;
	
	@GetMapping("/resumes")
	public List<Resume> list(){
		return resumeSer.getAll();
	}
	
	@GetMapping("/resumes/{id}")
	public Resume edit(@PathVariable("id") long id) {
		return resumeSer.getOne(id);
	}
	
	@PostMapping("/resumes")
	public Resume save(@RequestBody ResumeDTO resumeDTO) {
		System.out.println("Resume: "+ resumeDTO);
		
		Resume resume = resumeSer.save(resumeDTO.getResume());
		for (Skill x : resumeDTO.getSkill()) {
			x.setResume(resume);
			System.out.println(x);
			skillSer.saveOrUpdate(x);
		}
		for (CertificationOrCourse x : resumeDTO.getCertificationOrCourse()) {
			x.setResume(resume);
			System.out.println(x);
			certificationOrCourseSer.saveOrUpdate(x);
		}
		for (Language x : resumeDTO.getLanguage()) {
			x.setResume(resume);
			System.out.println(x);
			languageSer.saveOrUpdate(x);
		}
//		
//		if(resumeDTO.getCertificationOrCourse() != null) {
//			
//			CertificationOrCourse certificationOrCourse = resumeDTO.getCertificationOrCourse();
//			
//			certificationOrCourse.setResume(resume);
//			certificationOrCourseSer.saveOrUpdate(certificationOrCourse);
//		}
//		
//		Skill skill = resumeDTO.getSkill();
//		skill.setResume(resume);
//		skillSer.saveOrUpdate(skill);
//		
//		Language language = resumeDTO.getLanguage();
//		language.setResume(resume);
//		languageSer.saveOrUpdate(language);
		
		return resume;
		
	}
	
	@PutMapping("/resumes")
	public Resume update(@RequestBody Resume resume) {
		return resumeSer.saveOrUpdate(resume);
	}
	
	@DeleteMapping("/resumes")
	public void delete(@RequestParam("id") long id) {
		resumeSer.remove(id);
	}

	
}
