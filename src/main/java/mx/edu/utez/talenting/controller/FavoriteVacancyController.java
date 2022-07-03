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

import mx.edu.utez.talenting.entity.FavoriteVacancy;
import mx.edu.utez.talenting.service.FavoriteVacancyService;

@RestController
@RequestMapping("/talenting")
public class FavoriteVacancyController {
	
	@Autowired
	private FavoriteVacancyService favoriteVacancySer;
	
	@GetMapping("/favoriteVacancies")
	public List<FavoriteVacancy> list(){
		return favoriteVacancySer.getAll();
	}
	
	@GetMapping("/favoriteVacancies/{id}")
	public FavoriteVacancy edit(@PathVariable("id") long id) {
		return favoriteVacancySer.getOne(id);
	}
	
	@PutMapping("/favoriteVacancies")
	public FavoriteVacancy update(@RequestBody FavoriteVacancy favoriteVacancy) {
		return favoriteVacancySer.saveOrUpdate(favoriteVacancy);
	}
	
	@DeleteMapping("/favoriteVacancies")
	public void delete(@RequestParam("id") long id) {
		favoriteVacancySer.remove(id);
	}

	
}
