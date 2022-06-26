package mx.edu.utez.talenting.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
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
	private double maximanSalary;
	
	@Column(name = "payment_frecuency", nullable = false)
	private String paymentFrecuency;
	
	@Column(name = "validity_date", nullable = false)
	private String validityDate;
	
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
	
	//Configuration for postedVacancies
	@OneToMany(mappedBy = "vacancy")
	@JsonIgnore
	private List<PostedVacancy> postedVacancy;
	
}
