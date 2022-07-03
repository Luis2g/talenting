package mx.edu.utez.talenting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.talenting.entity.CertificationOrCourse;
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
	
	public CertificationOrCourse saveOrUpdate(CertificationOrCourse cerficationOrCourse) {
		return certificationOrCourseRepo.save(cerficationOrCourse);
	}
	
	public void remove(long id) {
		certificationOrCourseRepo.deleteById(id);
	}
	
}
