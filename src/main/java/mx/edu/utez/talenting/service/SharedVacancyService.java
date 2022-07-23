package mx.edu.utez.talenting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.talenting.entity.Person;
import mx.edu.utez.talenting.entity.SharedVacancy;
import mx.edu.utez.talenting.repository.SharedVacancyRepository;

@Service
public class SharedVacancyService {

	@Autowired
	private SharedVacancyRepository sharedVacancyRepo;
	
	public List<SharedVacancy> getAll(){
		return sharedVacancyRepo.findAll();
	}
	
	public List<SharedVacancy> findByPerson(Person person){
		return sharedVacancyRepo.findByPerson(person);
	}
	
	public SharedVacancy getOne(long id) {
		return sharedVacancyRepo.findById(id).get();
	}
	
	public SharedVacancy saveOrUpdate(SharedVacancy cerficationOrCourse) {
		return sharedVacancyRepo.save(cerficationOrCourse);
	}
	
	public void remove(long id) {
		sharedVacancyRepo.deleteById(id);
	}
	
}
