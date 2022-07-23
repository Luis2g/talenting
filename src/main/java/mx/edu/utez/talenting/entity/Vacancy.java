package mx.edu.utez.talenting.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "vacancies")
public class Vacancy implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(name = "state_in_which_is_available", nullable = false)
	private String stateInWhichIsAvailable;
	
	@Column(nullable = false)
	private String modality;
	
	@Column(nullable = false)
	private String type;
	
	@Column(name = "start_date", nullable = false)
	private String startDate;
	
	@Column(name = "minimum_salary", nullable = false)
	private double minimumSalary;
	
	@Column(name = "maximum_salary", nullable = false)
	private double maximumSalary;
	
	@Column(name = "payment_frecuency", nullable = false)
	private String paymentFrecuency;
	
	@Column(name = "validity_date", nullable = false)
	private String validityDate;
	
	@Column(nullable = false)
	private boolean status;
	
	//Configuration for benefits
	@OneToMany(mappedBy = "vacancy")
	@JsonIgnore
	private List<Benefit>  benefit;
	
	//Configuration for applierInVacancy
	@OneToMany(mappedBy = "vacancy")
	@JsonIgnore
	private List<ApplierInVacancy>  applierInVacancy; 
	
	//Configuration for favoriteVacancies
	@OneToMany(mappedBy = "vacancy")
	@JsonIgnore
	private List<FavoriteVacancy> favoriteVacancy;
	
	//Foreign key for employeer
	@ManyToOne
	@JoinColumn(name = "employeer")
	private Employeer employeer;
	
	//Configuration for sharedVacancies
	@OneToMany(mappedBy = "vacancy")
	@JsonIgnore
	private List<SharedVacancy> sharedVacancies;
	
	public Vacancy() {}
	
	public Vacancy (long id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStateInWhichIsAvailable() {
		return stateInWhichIsAvailable;
	}

	public void setStateInWhichIsAvailable(String stateInWhichIsAvailable) {
		this.stateInWhichIsAvailable = stateInWhichIsAvailable;
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public double getMinimumSalary() {
		return minimumSalary;
	}

	public void setMinimumSalary(double minimumSalary) {
		this.minimumSalary = minimumSalary;
	}

	public double getMaximumSalary() {
		return maximumSalary;
	}

	public void setMaximumSalary(double maximumSalary) {
		this.maximumSalary = maximumSalary;
	}

	public String getPaymentFrecuency() {
		return paymentFrecuency;
	}

	public void setPaymentFrecuency(String paymentFrecuency) {
		this.paymentFrecuency = paymentFrecuency;
	}

	public String getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(String validityDate) {
		this.validityDate = validityDate;
	}

	public List<Benefit> getBenefit() {
		return benefit;
	}

	public void setBenefit(List<Benefit> benefit) {
		this.benefit = benefit;
	}

	public List<ApplierInVacancy> getApplierInVacancy() {
		return applierInVacancy;
	}

	public void setApplierInVacancy(List<ApplierInVacancy> applierInVacancy) {
		this.applierInVacancy = applierInVacancy;
	}

	public List<FavoriteVacancy> getFavoriteVacancy() {
		return favoriteVacancy;
	}

	public void setFavoriteVacancy(List<FavoriteVacancy> favoriteVacancy) {
		this.favoriteVacancy = favoriteVacancy;
	}

	public Employeer getEmployeer() {
		return employeer;
	}

	public void setEmployeer(Employeer employeer) {
		this.employeer = employeer;
	}

	public List<SharedVacancy> getSharedVacancies() {
		return sharedVacancies;
	}

	public void setSharedVacancies(List<SharedVacancy> sharedVacancies) {
		this.sharedVacancies = sharedVacancies;
	}
	
}
