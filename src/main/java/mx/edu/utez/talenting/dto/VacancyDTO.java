package mx.edu.utez.talenting.dto;

import java.io.Serializable;
import java.util.List;

import mx.edu.utez.talenting.entity.ApplierInVacancy;
import mx.edu.utez.talenting.entity.Benefit;
import mx.edu.utez.talenting.entity.Vacancy;

public class VacancyDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Vacancy vacancy;
	
	//for saving
	private List<String> benefits;
	//to retrieve the information
	private List<Benefit> retrievedBenefits;
	
	private ApplierInVacancy applierInVacancy;
	
	private long applied = 0;
	
	private long shared = 0;
	
	private long favorite = 0;
	
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
	public long getApplied() {
		return applied;
	}
	public void setApplied(long applied) {
		this.applied = applied;
	}
	public long getShared() {
		return shared;
	}
	public void setShared(long shared) {
		this.shared = shared;
	}
	public long getFavorite() {
		return favorite;
	}
	public void setFavorite(long favorite) {
		this.favorite = favorite;
	}
	public ApplierInVacancy getApplierInVacancy() {
		return applierInVacancy;
	}
	public void setApplierInVacancy(ApplierInVacancy applierInVacancy) {
		this.applierInVacancy = applierInVacancy;
	}

}
