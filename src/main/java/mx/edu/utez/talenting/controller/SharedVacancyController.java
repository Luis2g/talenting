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

import mx.edu.utez.talenting.entity.SharedVacancy;
import mx.edu.utez.talenting.service.SharedVacancyService;

@RestController
@RequestMapping("/talenting")
@CrossOrigin(origins = "http://127.0.0.1:8081")
public class SharedVacancyController {
	
	@Autowired
	private SharedVacancyService sharedVacancySer;
	
	@GetMapping("/sharedVacancies")
	public List<SharedVacancy> list(){
		return sharedVacancySer.getAll();
	}
	
	@GetMapping("/sharedVacancies/{id}")
	public SharedVacancy edit(@PathVariable("id") long id) {
		return sharedVacancySer.getOne(id);
	}
	
	@PutMapping("/sharedVacancies")
	public SharedVacancy update(@RequestBody SharedVacancy sharedVacancy) {
		return sharedVacancySer.saveOrUpdate(sharedVacancy);
	}
	
	@PostMapping("/sharedVacancies")
	public SharedVacancy save(@RequestBody SharedVacancy sharedVacancy) {
		return sharedVacancySer.saveOrUpdate(sharedVacancy);
	}
	
	@DeleteMapping("/sharedVacancies")
	public void delete(@RequestParam("vacancyId") long id) {
		sharedVacancySer.remove(id);
	}

	
}
