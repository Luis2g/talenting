package mx.edu.utez.talenting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.talenting.entity.FavoriteVacancy;
import mx.edu.utez.talenting.repository.FavoriteVacancyRepository;

@Service
public class FavoriteVacancyService {

	@Autowired
	private FavoriteVacancyRepository favoriteVacancyRepo;
	
	public List<FavoriteVacancy> getAll(){
		return favoriteVacancyRepo.findAll();
	}
	
	public FavoriteVacancy getOne(long id) {
		return favoriteVacancyRepo.findById(id).get();
	}
	
	public FavoriteVacancy saveOrUpdate(FavoriteVacancy cerficationOrCourse) {
		return favoriteVacancyRepo.save(cerficationOrCourse);
	}
	
	public void remove(long id) {
		favoriteVacancyRepo.deleteById(id);
	}
	
}
