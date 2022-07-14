package mx.edu.utez.talenting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.talenting.entity.Benefit;
import mx.edu.utez.talenting.entity.Vacancy;
import mx.edu.utez.talenting.repository.BenefitRepository;

@Service
public class BenefitService {
	
	@Autowired
	private BenefitRepository benefitRepository;
	
	public List<Benefit> getByVacancy(Vacancy vacancy){
		return benefitRepository.findByVacancy(vacancy);
	}
	
	public Benefit getOne(long id) {
		return benefitRepository.findById(id).get();
	}
	
	public Benefit saveOrUpdate(Benefit benefit) {
		return benefitRepository.save(benefit);
	}
	
	public void removeBenefits (long idVacancy) {
		benefitRepository.removeBenefits(idVacancy);
	}
	
}
