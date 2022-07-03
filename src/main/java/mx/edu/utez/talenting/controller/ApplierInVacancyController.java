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

import mx.edu.utez.talenting.entity.ApplierInVacancy;
import mx.edu.utez.talenting.service.ApplierInVacancyService;

@RestController
@RequestMapping("/talenting")
public class ApplierInVacancyController {
	
	@Autowired
	private ApplierInVacancyService applierInVacancySer;
	
	@GetMapping("/appliersInVacancies")
	public List<ApplierInVacancy> list(){
		return applierInVacancySer.getAll();
	}
	
	@GetMapping("/appliersInVacancies/{id}")
	public ApplierInVacancy edit(@PathVariable("id") long id) {
		return applierInVacancySer.getOne(id);
	}
	
	@PutMapping("/appliersInVacancies")
	public ApplierInVacancy update(@RequestBody ApplierInVacancy applierInVacancy) {
		return applierInVacancySer.saveOrUpdate(applierInVacancy);
	}
	
	@DeleteMapping("/appliersInVacancies")
	public void delete(@RequestParam("id") long id) {
		applierInVacancySer.remove(id);
	}

	
}
