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
import mx.edu.utez.talenting.entity.Employeer;
import mx.edu.utez.talenting.entity.FavoriteVacancy;
import mx.edu.utez.talenting.entity.Person;
import mx.edu.utez.talenting.entity.SharedVacancy;
import mx.edu.utez.talenting.entity.Vacancy;
import mx.edu.utez.talenting.service.ApplierInVacancyService;
import mx.edu.utez.talenting.service.BenefitService;
import mx.edu.utez.talenting.service.FavoriteVacancyService;
import mx.edu.utez.talenting.service.SharedVacancyService;
import mx.edu.utez.talenting.service.VacancyService;

@RestController
@RequestMapping("/talenting")
@CrossOrigin(origins = "http://127.0.0.1:8081")
public class VacancyController {
	
	@Autowired
	private VacancyService vacancySer;
	@Autowired
	private BenefitService benefitSer;
	@Autowired
	private ApplierInVacancyService applierInVacancySer;
	@Autowired
	private SharedVacancyService sharedVacancySer;
	@Autowired
	private FavoriteVacancyService favoriteVacancySer;
	
	@GetMapping("/vacancies")
	public List<VacancyDTO> list(@RequestParam("params") long id){
		System.out.println("here's the params" + id);
		
		List<Vacancy> vacancies = vacancySer.getByEmployeer(new Employeer(id)); 
		List<VacancyDTO> vacanciesDTO = new ArrayList<>();
		
		for(Vacancy vacancy: vacancies){
			
			VacancyDTO vacancyDTOTemp = new VacancyDTO();
			List<Benefit> benefits = benefitSer.getByVacancy(new Vacancy(vacancy.getId()));
			vacancyDTOTemp.setVacancy(vacancy);
			vacancyDTOTemp.setRetrievedBenefits(benefits);
			vacanciesDTO.add(vacancyDTOTemp);
		}
		
		
		return vacanciesDTO;
	}
	
	@GetMapping("/vacanciesByApplier")
	public List<VacancyDTO> getVacanciesByApplier(@RequestParam("applierId") long id){
		
		List<ApplierInVacancy> appliersInVacancies = applierInVacancySer.findByPerson(new Person(id));
		
		List<VacancyDTO> vacanciesDTO = new ArrayList<>();
		
		for(ApplierInVacancy applierInVacancy: appliersInVacancies){
			
			VacancyDTO vacancyDTOTemp = new VacancyDTO();
			List<Benefit> benefits = benefitSer.getByVacancy(new Vacancy(applierInVacancy.getVacancy().getId()));
			vacancyDTOTemp.setApplierInVacancy(applierInVacancy);
			vacancyDTOTemp.setRetrievedBenefits(benefits);
			vacanciesDTO.add(vacancyDTOTemp);
		}
		
		
		return vacanciesDTO;
	}
	
	@GetMapping("/vacanciesAccordingToFilter")
	public List<VacancyDTO> listAccordingToFilter(@RequestParam("userId") long id, @RequestParam("state") String state){
		
		
		
		List<Vacancy> vacancies = vacancySer.getAccordingToFilter(state);
		 
		List<VacancyDTO> vacanciesDTO = new ArrayList<>();
		
		for(Vacancy vacancy: vacancies){
			
			VacancyDTO vacancyDTOTemp = new VacancyDTO();
			List<Benefit> benefits = benefitSer.getByVacancy(new Vacancy(vacancy.getId()));
			vacancyDTOTemp.setVacancy(vacancy);
			vacancyDTOTemp.setRetrievedBenefits(benefits);
			vacanciesDTO.add(vacancyDTOTemp);
		}
		
				
		if(id != 0) {
			
			Person person = new Person(id);
			
			List<ApplierInVacancy> appliersInVacancies = applierInVacancySer.findByPerson(person);
			List<SharedVacancy> sharedVacancies = sharedVacancySer.findByPerson(person);
			List<FavoriteVacancy> favoriteVacancies = favoriteVacancySer.findByPerson(person);
			
//			List<SharedVacancy> sharedVacancies = 
			if(!appliersInVacancies.isEmpty()) {
				for(ApplierInVacancy applierInVacancy: appliersInVacancies) {
					for(VacancyDTO vacancyDTO: vacanciesDTO) {
						if(vacancyDTO.getVacancy().getId() == applierInVacancy.getVacancy().getId()) {
							vacancyDTO.setApplied(applierInVacancy.getId());
							break;
						}
					}				
				}
			}
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
		}
		
		return vacanciesDTO;
	}
	
	
	
	@GetMapping("/vacancies/{id}")
	public Vacancy edit(@PathVariable("id") long id) {
		return vacancySer.getOne(id);
	}
	
	@PutMapping("/vacancies")
	public Vacancy update(@RequestBody VacancyDTO vacancyDTO) {
		
		benefitSer.removeBenefits(vacancyDTO.getVacancy().getId());
		
		if(!vacancyDTO.getBenefits().isEmpty()) {
			for(String benefit: vacancyDTO.getBenefits()) {
				
				Benefit benefitToSave = new Benefit(benefit, new Vacancy(vacancyDTO.getVacancy().getId()));
				
				benefitSer.saveOrUpdate(benefitToSave);
			}
		}
		
		return vacancySer.saveOrUpdate(vacancyDTO.getVacancy());
	}
	
	@PutMapping("/vacancies/changeStatus")
	public Vacancy changeStatus(@RequestBody Vacancy vacancy) {
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
