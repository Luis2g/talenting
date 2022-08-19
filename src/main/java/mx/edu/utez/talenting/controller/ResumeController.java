package mx.edu.utez.talenting.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.multipart.MultipartFile;

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
	
	private String profileImageName;
	
	private String PDFResumeName;
	
	@GetMapping("/resumes")
	public List<Resume> list(){
		return resumeSer.getAll();
	}
	
//	@GetMapping("/resumes/{id}")
//	public Resume edit(@PathVariable("id") long id) {
//		return resumeSer.getOne(id);
//	}
	
	@GetMapping("/resumes/{id}")
	public ResumeDTO listResumeByPerson(@PathVariable("id") long id) {
		try {
			return resumeSer.findByPersonId(id);
		}catch (Exception e) {
			System.out.print(e);
		}
		return new ResumeDTO();
	}
	
	@PostMapping("/resumes")
	public Resume save(@RequestBody ResumeDTO resumeDTO) {
		System.out.println("Resume: "+ resumeDTO.getResume().getId());
		
		if(profileImageName != null) {
			resumeDTO.getResume().setProfileImage(profileImageName);
		}
		
		if(PDFResumeName != null) {
			resumeDTO.getResume().setPDFResume(PDFResumeName);
		}
		
		Resume resume = resumeSer.saveOrUpdate(resumeDTO.getResume());
		
		
		List <Skill>existSkill = new ArrayList<>();
		existSkill = skillSer.getAll();
		System.out.println(existSkill.size());
		if(existSkill.size() == 0) {
			System.out.println("Registro normal de skills");
			for (Skill x : existSkill) {
				System.out.println("Registrando skill...");
				x.setResume(resume);
				System.out.println(x);
				skillSer.save(x);
			}
		}else {
			System.out.println("Actualización de skills");
			System.out.println("Eliminando skill...");
			System.out.println("Id del resume: "+resumeDTO.getResume().getId());
			skillSer.deleteByResume(resumeDTO.getResume().getId());
			for (Skill x : resumeDTO.getSkill()) {
				System.out.println("Actualizando skills...");
				x.setResume(resume);
				System.out.println(x);
				skillSer.save(x);
			}
		}
		
		
		List <CertificationOrCourse> existCertificationOrCouse = new ArrayList<>();
		existCertificationOrCouse = certificationOrCourseSer.getAll();
		if(existCertificationOrCouse.size() == 0) {
			System.out.println("Registro normal de skills");
			for (CertificationOrCourse x : resumeDTO.getCertificationOrCourse()) {
				x.setResume(resume);
				System.out.println(x);
				certificationOrCourseSer.save(x);
			}
		}else {
			certificationOrCourseSer.deleteByResume(resumeDTO.getResume().getId());
			for (CertificationOrCourse x : resumeDTO.getCertificationOrCourse()) {
				x.setResume(resume);
				System.out.println(x);
				certificationOrCourseSer.save(x);
			}
		}
		
		
		List <Language> existLanguage = new ArrayList<>();
		existLanguage = languageSer.getAll();
		if(existLanguage.size() == 0) {
			for (Language x : resumeDTO.getLanguage()) {
				x.setResume(resume);
				System.out.println(x);
				languageSer.save(x);
			}
		}else{
			languageSer.deleteByResume(resumeDTO.getResume().getId());
			for (Language x : resumeDTO.getLanguage()) {
				x.setResume(resume);
				System.out.println(x);
				languageSer.save(x);
			}
		}
		
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
	
	
	@PostMapping("/upload/profileImage")
    public void uploadProfileImage(@RequestParam("file") MultipartFile file) {
        String separator = FileSystems.getDefault().getSeparator();
        String fileName = UUID.randomUUID().toString();
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            String userDirectory = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
            file.transferTo(new File(userDirectory + "\\src\\main\\resources\\static\\uploads\\" + separator + "profileImages" + separator + fileName + "." + ext));
            this.profileImageName = fileName + "." + ext;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	@PostMapping("/upload/PDFResume")
    public void uploadPDFResume(@RequestParam("file") MultipartFile file) {
        String separator = FileSystems.getDefault().getSeparator();
        String fileName = UUID.randomUUID().toString();
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            String userDirectory = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
            file.transferTo(new File(userDirectory + "\\src\\main\\resources\\static\\uploads\\" + separator + "PDFResume" + separator + fileName + "." + ext));
            this.PDFResumeName = fileName + "." + ext;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	
}
