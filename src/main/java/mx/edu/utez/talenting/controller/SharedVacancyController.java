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
import mx.edu.utez.talenting.service.PersonService;
import mx.edu.utez.talenting.service.SharedVacancyService;
import mx.edu.utez.talenting.service.VacancyService;

@RestController
@RequestMapping("/talenting")
@CrossOrigin(origins = "http://127.0.0.1:8081")
public class SharedVacancyController {
	
	@Autowired
	private SharedVacancyService sharedVacancySer;
	@Autowired
	private FavoriteVacancyService favoriteVacancySer;
	@Autowired
	private ApplierInVacancyService applierInVacancySer;
	@Autowired
	private BenefitService benefitSer;
	@Autowired
	private PersonService personSer;
	@Autowired
	private VacancyService vacancySer;
	
	@GetMapping("/socialMedia")
	public List<VacancyDTO> list(@RequestParam("personId") long id){
		
		
		
		List<Vacancy> sharedVacanciesWithNames =  vacancySer.getSharedVacancies(id);
		
		
		
	
		Person person = new Person(id);
		
		//first my shared ones are added
		List<SharedVacancy> sharedVacancies = sharedVacancySer.findByPerson(person);
		List<FavoriteVacancy> favoriteVacancies = favoriteVacancySer.findByPerson(person);
		List<ApplierInVacancy> appliersInVacancies = applierInVacancySer.findByPerson(person);
		
		List<VacancyDTO> vacanciesDTO = new ArrayList<>();

		
		if(!sharedVacanciesWithNames.isEmpty()) {
			//to obtain benefits
			for(Vacancy sharedVacancy: sharedVacanciesWithNames) {
				
				VacancyDTO vacancyDTO = new VacancyDTO();
				vacancyDTO.setVacancy(sharedVacancy);
				List<Benefit> benefits = benefitSer.getByVacancy(sharedVacancy);
				vacancyDTO.setRetrievedBenefits(benefits);
				vacanciesDTO.add(vacancyDTO);
				
			}
			//to obtain the share id's
			for(SharedVacancy vacancy: sharedVacancies) {
				for(VacancyDTO vacancyDTO: vacanciesDTO) {
					if( vacancy.getVacancy().getId() == vacancyDTO.getVacancy().getId() ) {
						vacancyDTO.setShared(vacancy.getId());
						break;
					}
				}
				
			}
			//to obtain the favorite id's
			if(!favoriteVacancies.isEmpty()) {
				for(FavoriteVacancy favoriteVacancy: favoriteVacancies) {
					for(VacancyDTO vacancyDTO: vacanciesDTO) {
						if(favoriteVacancy.getVacancy().getId() == vacancyDTO.getVacancy().getId()) {
							vacancyDTO.setFavorite(favoriteVacancy.getId());
							break;
						}
					}
				}
			}
			//to obtain the postulation id's
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
			for(VacancyDTO vacancyDTO: vacanciesDTO) {
				vacancyDTO.setPeopleWhoSharedIt(personSer.getPeopleWhoSharedIt(vacancyDTO.getVacancy().getId()));
			}
			
		}
		
		
		return vacanciesDTO;
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
