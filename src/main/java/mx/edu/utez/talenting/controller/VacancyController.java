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

import mx.edu.utez.talenting.entity.Vacancy;
import mx.edu.utez.talenting.service.VacancyService;

@RestController
@RequestMapping("/talenting")
public class VacancyController {
	
	@Autowired
	private VacancyService vacancySer;
	
	@GetMapping("/vacancies")
	public List<Vacancy> list(){
		return vacancySer.getAll();
	}
	
	@GetMapping("/vacancies/{id}")
	public Vacancy edit(@PathVariable("id") long id) {
		return vacancySer.getOne(id);
	}
	
	@PutMapping("/vacancies")
	public Vacancy update(@RequestBody Vacancy vacancy) {
		return vacancySer.saveOrUpdate(vacancy);
	}
	
	@DeleteMapping("/vacancies")
	public void delete(@RequestParam("id") long id) {
		vacancySer.remove(id);
	}

	
}
