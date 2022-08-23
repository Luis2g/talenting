package mx.edu.utez.talenting.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.talenting.entity.CertificationOrCourse;
import mx.edu.utez.talenting.entity.Skill;
import mx.edu.utez.talenting.repository.CertificationOrCourseRepository;

@Service
public class CertificationOrCourseService {

	@Autowired
	private CertificationOrCourseRepository certificationOrCourseRepo;
	
	public List<CertificationOrCourse> getAll(){
		return certificationOrCourseRepo.findAll();
	}
	
	public CertificationOrCourse getOne(long id) {
		return certificationOrCourseRepo.findById(id).get();
	}
	
	public boolean save(CertificationOrCourse obj) {
		boolean flag = false;
		CertificationOrCourse tmp = certificationOrCourseRepo.save(obj);
		if (tmp != null) {
			flag = true;
		}
		return flag;
	}
	
	public CertificationOrCourse saveOrUpdate(CertificationOrCourse cerficationOrCourse) {
		return certificationOrCourseRepo.save(cerficationOrCourse);
	}
	
	public void remove(long id) {
		certificationOrCourseRepo.deleteById(id);
	}
	
	@Transactional
	public void deleteByResume(long id) {
		certificationOrCourseRepo.deleteByResume(id);
	}
	
}
