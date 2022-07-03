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

import mx.edu.utez.talenting.entity.Resume;
import mx.edu.utez.talenting.service.ResumeService;

@RestController
@RequestMapping("/talenting")
public class ResumeController {
	
	@Autowired
	private ResumeService resumeSer;
	
	@GetMapping("/resumes")
	public List<Resume> list(){
		return resumeSer.getAll();
	}
	
	@GetMapping("/resumes/{id}")
	public Resume edit(@PathVariable("id") long id) {
		return resumeSer.getOne(id);
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
