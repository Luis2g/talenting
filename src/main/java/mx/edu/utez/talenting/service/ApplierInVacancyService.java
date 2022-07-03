package mx.edu.utez.talenting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.talenting.entity.ApplierInVacancy;
import mx.edu.utez.talenting.repository.ApplierInVacancyRepository;

@Service
public class ApplierInVacancyService {
	
	@Autowired
	private ApplierInVacancyRepository applierInVacancyRepository;
	
	public List<ApplierInVacancy> getAll() {
		return applierInVacancyRepository.findAll();
	}
	
	public ApplierInVacancy getOne(long id) {
		return applierInVacancyRepository.findById(id).get();
	}

	public ApplierInVacancy saveOrUpdate(ApplierInVacancy applierInVacancy) {
		return applierInVacancyRepository.save(applierInVacancy);
	}
	
	public void remove(long id) {
		applierInVacancyRepository.deleteById(id);
	}
	
}
