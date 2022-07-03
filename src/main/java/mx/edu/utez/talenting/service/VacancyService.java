package mx.edu.utez.talenting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.talenting.entity.Vacancy;
import mx.edu.utez.talenting.repository.VacancyRepository;

@Service
public class VacancyService {

	@Autowired
	private VacancyRepository vacancyRepo;
	
	public List<Vacancy> getAll(){
		return vacancyRepo.findAll();
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
	
}
