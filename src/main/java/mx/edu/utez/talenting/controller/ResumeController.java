package mx.edu.utez.talenting.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
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
		System.out.println("Si lo hace :D");
		return resumeSer.findByPersonId(id);
	}
	
	@PostMapping("/resumes")
	public Resume save(@RequestBody ResumeDTO resumeDTO) {
		System.out.println("Resume: "+ resumeDTO);
		
		if(profileImageName != null) {
			resumeDTO.getResume().setProfileImage(profileImageName);
		}
		
		if(PDFResumeName != null) {
			resumeDTO.getResume().setPDFResume(PDFResumeName);
		}
		
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
