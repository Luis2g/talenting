package mx.edu.utez.talenting.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.talenting.entity.Language;
import mx.edu.utez.talenting.entity.Skill;
import mx.edu.utez.talenting.repository.LanguageRepository;

@Service
public class LanguageService {

	@Autowired
	private LanguageRepository languageRepo;
	
	public List<Language> getAll(){
		return languageRepo.findAll();
	}
	
	public Language getOne(long id) {
		return languageRepo.findById(id).get();
	}
	
	public boolean save(Language obj) {
		boolean flag = false;
		Language tmp = languageRepo.save(obj);
		if (tmp != null) {
			flag = true;
		}
		return flag;
	}
	
	public Language saveOrUpdate(Language cerficationOrCourse) {
		return languageRepo.save(cerficationOrCourse);
	}
	
	public void remove(long id) {
		languageRepo.deleteById(id);
	}
	
	@Transactional
	public void deleteByResume(long id) {
		languageRepo.deleteByResume(id);
	}
	
}
