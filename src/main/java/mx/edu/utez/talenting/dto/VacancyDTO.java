package mx.edu.utez.talenting.dto;

import java.io.Serializable;
import java.util.List;

import mx.edu.utez.talenting.entity.Benefit;
import mx.edu.utez.talenting.entity.Vacancy;

public class VacancyDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Vacancy vacancy;
	
	//for saving
	private List<String> benefits;
	//to retrieve the information
	private List<Benefit> retrievedBenefits;
	
	public Vacancy getVacancy() {
		return vacancy;
	}
	public void setVacancy(Vacancy vacancy) {
		this.vacancy = vacancy;
	}
	public List<String> getBenefits() {
		return benefits;
	}
	public void setBenefits(List<String> benefits) {
		this.benefits = benefits;
	}
	public List<Benefit> getRetrievedBenefits() {
		return retrievedBenefits;
	}
	public void setRetrievedBenefits(List<Benefit> retrievedBenefits) {
		this.retrievedBenefits = retrievedBenefits;
	}	
	
}
