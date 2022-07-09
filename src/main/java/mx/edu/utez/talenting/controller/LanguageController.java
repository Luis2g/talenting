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

import mx.edu.utez.talenting.entity.Language;
import mx.edu.utez.talenting.service.LanguageService;

@RestController
@RequestMapping("/talenting")
public class LanguageController {
	
	@Autowired
	private LanguageService languageSer;
	
	@GetMapping("/languages")
	public List<Language> list(){
		return languageSer.getAll();
	}
	
	@GetMapping("/languages/{id}")
	public Language edit(@PathVariable("id") long id) {
		return languageSer.getOne(id);
	}
	
	@PutMapping("/languages")
	public Language update(@RequestBody Language language) {
		return languageSer.saveOrUpdate(language);
	}
	
	@DeleteMapping("/languages")
	public void delete(@RequestParam("id") long id) {
		languageSer.remove(id);
	}

	
}
