package mx.edu.utez.talenting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.talenting.entity.Employeer;
import mx.edu.utez.talenting.entity.Vacancy;
import mx.edu.utez.talenting.repository.VacancyRepository;

@Service
public class VacancyService {

	@Autowired
	private VacancyRepository vacancyRepo;

	public List<Vacancy> getAccordingToFilter(String state){
		return vacancyRepo.findByStateInWhichIsAvailableAndStatus(state, true);
	}
	
	public List<Vacancy> getAccordingToTitle(String title){
		return vacancyRepo.getFilterVacancies(title, true);
	}
	
	public List<Vacancy> getAccordingToTitleAndState(String title, String state){
		return vacancyRepo.getFilterVacancies(title, state , true);
	}
	
	public List<Vacancy> getByEmployeer(Employeer employeer){
		return vacancyRepo.findByEmployeer(employeer);
	}
	
	public List<Vacancy> getByEmployeerAndState(Employeer employeer, String state){
		return vacancyRepo.findByEmployeerAndStateInWhichIsAvailable(employeer,state);
	}
	
	public List<Vacancy> getByEmployeerAndTitle(Employeer employeer, String title){
		return vacancyRepo.findByEmployeerAndTitleContaining(employeer,title);
	}
	
	public List<Vacancy> getByEmployeerAndStateAndTitle(Employeer employeer,String state, String title){
		return vacancyRepo.findByEmployeerAndStateInWhichIsAvailableAndTitleContaining(employeer, state, title);
	}
	
	public Vacancy getOne(long id) {
		return vacancyRepo.findById(id).get();
	}
	
	public Vacancy saveOrUpdate(Vacancy cerficationOrCourse) {
		return vacancyRepo.save(cerficationOrCourse);
	}
	
	public void remove(long id) {
		vacancyRepo.deleteById(id);
	}
	
	public List<Vacancy> getVacanciesByApplier(long id){
		return vacancyRepo.getVacanciesByApplier(id);
	}
	
	public List<Vacancy> getOnlyTheActiveOnes(){
		return vacancyRepo.findByStatus(true);
	}
	
	public List<Vacancy> getSharedVacancies(long id){
		return vacancyRepo.getSharedVacancies(id);
	}
	
}
