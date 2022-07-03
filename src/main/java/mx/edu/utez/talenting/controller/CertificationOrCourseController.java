package mx.edu.utez.talenting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.utez.talenting.entity.CertificationOrCourse;
import mx.edu.utez.talenting.service.CertificationOrCourseService;

@RestController
@RequestMapping("/talenting")
public class CertificationOrCourseController {
	
	@Autowired
	private CertificationOrCourseService certificationOrCourseSer;
	
	@GetMapping("/certificationsOurCourses")
	public List<CertificationOrCourse> list(){
		return certificationOrCourseSer.getAll();
	}
	
	@GetMapping("/certificationsOurCourses/{id}")
	public CertificationOrCourse edit(@PathVariable("id") long id) {
		return certificationOrCourseSer.getOne(id);
	}
	
	@PutMapping("/certificationsOurCourses")
	public CertificationOrCourse update(@RequestBody CertificationOrCourse certificationOrCourse) {
		return certificationOrCourseSer.saveOrUpdate(certificationOrCourse);
	}
	
	@DeleteMapping("/certificationsOurCourses")
	public void delete(@RequestParam("id") long id) {
		certificationOrCourseSer.remove(id);
	}

	
}
