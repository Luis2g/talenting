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

import mx.edu.utez.talenting.dto.VacancyDTO;
import mx.edu.utez.talenting.entity.Benefit;
import mx.edu.utez.talenting.entity.Employeer;
import mx.edu.utez.talenting.entity.Vacancy;
import mx.edu.utez.talenting.service.BenefitService;
import mx.edu.utez.talenting.service.VacancyService;

@RestController
@RequestMapping("/talenting")
@CrossOrigin(origins = "http://127.0.0.1:8081")
public class VacancyController {
	
	@Autowired
	private VacancyService vacancySer;
	@Autowired
	private BenefitService benefitSer;
	
	@GetMapping("/vacancies")
	public List<Vacancy> list(@RequestParam("params") long id){
		System.out.println("here's the params" + id);
		return vacancySer.getByEmployeer(new Employeer(id));
	}
	
	@GetMapping("/vacancies/{id}")
	public Vacancy edit(@PathVariable("id") long id) {
		return vacancySer.getOne(id);
	}
	
	@PutMapping("/vacancies")
	public Vacancy update(@RequestBody Vacancy vacancy) {
		return vacancySer.saveOrUpdate(vacancy);
	}
	
	@PostMapping("/vacancies")
	public Vacancy save(@RequestBody VacancyDTO vacancyDTO) {
		System.out.println("This is the vacancy " + vacancyDTO);
		
		Vacancy savedVacancy = vacancySer.saveOrUpdate(vacancyDTO.getVacancy());
		
		if(!vacancyDTO.getBenefits().isEmpty()) {
			for(String benefit: vacancyDTO.getBenefits()) {
				
				Benefit benefitToSave = new Benefit(benefit, new Vacancy(savedVacancy.getId()));
				
				benefitSer.saveOrUpdate(benefitToSave);
			}
		}
		
		return savedVacancy;
	}
	
	@DeleteMapping("/vacancies")
	public void delete(@RequestParam("id") long id) {
		vacancySer.remove(id);
	}

	
}
