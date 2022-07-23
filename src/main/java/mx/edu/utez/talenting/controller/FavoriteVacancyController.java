package mx.edu.utez.talenting.controller;

import java.util.ArrayList;
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
import mx.edu.utez.talenting.entity.ApplierInVacancy;
import mx.edu.utez.talenting.entity.Benefit;
import mx.edu.utez.talenting.entity.FavoriteVacancy;
import mx.edu.utez.talenting.entity.Person;
import mx.edu.utez.talenting.entity.SharedVacancy;
import mx.edu.utez.talenting.entity.Vacancy;
import mx.edu.utez.talenting.service.ApplierInVacancyService;
import mx.edu.utez.talenting.service.BenefitService;
import mx.edu.utez.talenting.service.FavoriteVacancyService;
import mx.edu.utez.talenting.service.SharedVacancyService;

@RestController
@RequestMapping("/talenting")
@CrossOrigin(origins = "http://127.0.0.1:8081")
public class FavoriteVacancyController {
	
	@Autowired
	private FavoriteVacancyService favoriteVacancySer;
	@Autowired
	private BenefitService benefitSer;
	@Autowired
	private SharedVacancyService shareVacancySer;
	@Autowired
	private ApplierInVacancyService applierInVacancySer;
	
	@GetMapping("/favoriteVacancies")
	public List<FavoriteVacancy> list(){
		return favoriteVacancySer.getAll();
	}
	
	@GetMapping("/myfavoriteVacancies")
	public List<VacancyDTO> list(@RequestParam("personId") long id){
		
		Person person = new Person(id);
		
		List<FavoriteVacancy> favoriteVacancies = favoriteVacancySer.findByPerson(person);
		List<SharedVacancy> sharedVacancies = shareVacancySer.findByPerson(person);
		List<ApplierInVacancy> appliersInVacancies = applierInVacancySer.findByPerson(person);
		
		List<VacancyDTO> vacanciesDTO = new ArrayList<>();
		
		
		if(!favoriteVacancies.isEmpty()) {
			for(FavoriteVacancy favoriteVacancy: favoriteVacancies) {
				
				VacancyDTO vacancyDTO = new VacancyDTO();
				vacancyDTO.setVacancy(favoriteVacancy.getVacancy());
				List<Benefit> benefits = benefitSer.getByVacancy(new Vacancy(favoriteVacancy.getVacancy().getId()));
				vacancyDTO.setRetrievedBenefits(benefits);
				vacancyDTO.setFavorite(favoriteVacancy.getId());
				vacanciesDTO.add(vacancyDTO);
				
			}
		}
		
		if(!vacanciesDTO.isEmpty()) {
			
			if(!sharedVacancies.isEmpty()) {
				for(SharedVacancy sharedVacancy: sharedVacancies) {
					for(VacancyDTO vacancyDTO: vacanciesDTO) {
						if(sharedVacancy.getVacancy().getId() == vacancyDTO.getVacancy().getId()) {
							vacancyDTO.setShared(sharedVacancy.getId());
							break;
						}
					}
				}
			}
			
			if(!appliersInVacancies.isEmpty()) {
				for(ApplierInVacancy applierInVacancy: appliersInVacancies) {
					for(VacancyDTO vacancyDTO: vacanciesDTO) {
						if(applierInVacancy.getVacancy().getId() == vacancyDTO.getVacancy().getId()) {
							vacancyDTO.setApplied(applierInVacancy.getId());
							break;
						}
					}
				}
			}
			
		}
		
		
		return vacanciesDTO;
	}
	
	@GetMapping("/favoriteVacancies/{id}")
	public FavoriteVacancy edit(@PathVariable("id") long id) {
		return favoriteVacancySer.getOne(id);
	}
	
	@PutMapping("/favoriteVacancies")
	public FavoriteVacancy update(@RequestBody FavoriteVacancy favoriteVacancy) {
		return favoriteVacancySer.saveOrUpdate(favoriteVacancy);
	}
	
	@PostMapping("/favoriteVacancies")
	public FavoriteVacancy save(@RequestBody FavoriteVacancy favoriteVacancy) {
		return favoriteVacancySer.saveOrUpdate(favoriteVacancy);
	}
	
	@DeleteMapping("/favoriteVacancies")
	public void delete(@RequestParam("vacancyId") long id) {
		favoriteVacancySer.remove(id);
	}

	
}
