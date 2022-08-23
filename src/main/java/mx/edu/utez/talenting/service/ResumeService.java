package mx.edu.utez.talenting.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import mx.edu.utez.talenting.dto.ResumeDTO;
import mx.edu.utez.talenting.entity.Resume;
import mx.edu.utez.talenting.entity.User;
import mx.edu.utez.talenting.repository.CertificationOrCourseRepository;
import mx.edu.utez.talenting.repository.LanguageRepository;
import mx.edu.utez.talenting.repository.ResumeRepository;
import mx.edu.utez.talenting.repository.SkillRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

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
		ResumeDTO resumeDTO = new ResumeDTO();
		resumeDTO.setResume(resumeRepo.findByPersonId(id));
		resumeDTO.setLanguage(languageRepo.findByResumeId(resumeDTO.getResume().getId()));
		resumeDTO.setSkill(skillRepo.findByResumeId(resumeDTO.getResume().getId()));
		resumeDTO.setCertificationOrCourse(certificationOrCourseRepo.findByResumeId(resumeDTO.getResume().getId()));
		return resumeDTO;
	}
	
	@Autowired
	protected javax.sql.DataSource localDataSource;
	
	@Transactional(readOnly = true)
	public JasperPrint resumePDF(int idResume) throws SQLException, FileNotFoundException, JRException {
		try(java.sql.Connection con = localDataSource.getConnection()) {
			String report = "classpath:reports/resume.jrxml";
			JasperReport jasperReport = null;
			File file = ResourceUtils.getFile(report);
			jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			Map<String, Object> map = new HashMap();
			map.put("idResume", idResume);
			// IMPORTANTE
			//Esto se configura en cada caomputadora que se valla a utilizar
			map.put("SUBREPORT_DIR", "C:\\Repositorios\\Integradora 9\\talenting\\src\\main\\resources\\reports\\");
			return JasperFillManager.fillReport(jasperReport, map,con);
		}
	}
	
	
}
