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

import mx.edu.utez.talenting.entity.ApplierInVacancy;
import mx.edu.utez.talenting.service.ApplierInVacancyService;

@RestController
@RequestMapping("/talenting")
@CrossOrigin(origins = "http://127.0.0.1:8081")
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
	
	@PostMapping("/appliersInVacancies")
	public ApplierInVacancy save(@RequestBody ApplierInVacancy applierInVacancy) {		
		return applierInVacancySer.saveOrUpdate(applierInVacancy);
	}
	
	@DeleteMapping("/appliersInVacancies")
	public void delete(@RequestParam("vacancyId") long vacancyId) {
		applierInVacancySer.remove(vacancyId);
	}
	
	@GetMapping("/appliersList")
	public List <ApplierInVacancy> listAppliers(@RequestParam("vacancyId") long vacancyId){
		return applierInVacancySer.getAppliersByVacancy(vacancyId);
	}
	
	@PostMapping("/changeAppliersStatus")
	public int changeAppliersStatus(@RequestParam("status") String status, @RequestParam("id") long idApplier, @RequestParam("vacancy") long idVacancy) {
		if(status.equals("Contratado")) {
			applierInVacancySer.declineAppliers(idVacancy , idApplier);
			applierInVacancySer.changeStatusToVacancy(idVacancy);
			return applierInVacancySer.changeStatus(status, idApplier);
		}else {
			return applierInVacancySer.changeStatus(status, idApplier);	
		}		
	}

	
}
