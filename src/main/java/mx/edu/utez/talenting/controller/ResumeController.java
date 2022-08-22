package mx.edu.utez.talenting.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

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
	
	private static final String typeApp = "application/x-pdf";
    private static final String attachment = "attachment; filename=cv.pdf";
	
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
	public Resume save(@RequestBody ResumeDTO resumeDTO) throws IOException {
		Resume resume = resumeSer.saveOrUpdate(resumeDTO.getResume());
		
		if(resumeDTO.getResume().getProfileImage() != null) {
			resume.setProfileImage(resumeDTO.getResume().getProfileImage());
		}
		
		if(resumeDTO.getResume().getPDFResume() != null) {
			resume.setPDFResume(resumeDTO.getResume().getPDFResume());
		}
		
		List <Skill>existSkill = new ArrayList<>();
		existSkill = skillSer.getAll();
		if(existSkill.size() == 0) {
			for (Skill x : resumeDTO.getSkill()) {
				x.setResume(resume);
				skillSer.save(x);
			}
		}else {
			skillSer.deleteByResume(resumeDTO.getResume().getId());
			for (Skill x : resumeDTO.getSkill()) {
				x.setResume(resume);
				skillSer.save(x);
			}
		}
		
		
		List <CertificationOrCourse> existCertificationOrCouse = new ArrayList<>();
		existCertificationOrCouse = certificationOrCourseSer.getAll();
		if(existCertificationOrCouse.size() == 0) {
			for (CertificationOrCourse x : resumeDTO.getCertificationOrCourse()) {
				x.setResume(resume);
				certificationOrCourseSer.save(x);
			}
		}else {
			certificationOrCourseSer.deleteByResume(resumeDTO.getResume().getId());
			for (CertificationOrCourse x : resumeDTO.getCertificationOrCourse()) {
				x.setResume(resume);
				certificationOrCourseSer.save(x);
			}
		}
		
		
		List <Language> existLanguage = new ArrayList<>();
		existLanguage = languageSer.getAll();
		if(existLanguage.size() == 0) {
			for (Language x : resumeDTO.getLanguage()) {
				x.setResume(resume);
				languageSer.save(x);
			}
		}else{
			languageSer.deleteByResume(resumeDTO.getResume().getId());
			for (Language x : resumeDTO.getLanguage()) {
				x.setResume(resume);
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
	
	@RequestMapping(value = "/resumes/CV/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void test(HttpServletResponse response, @PathVariable("id") int idResume)
            throws JRException, IOException, SQLException {
            JasperPrint jasperPrint = resumeSer.resumePDF(idResume);
            response.setContentType(typeApp);
            response.setHeader("Content-disposition", attachment);
            OutputStream outputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

    }
	
}
