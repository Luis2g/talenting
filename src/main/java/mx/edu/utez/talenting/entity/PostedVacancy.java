package mx.edu.utez.talenting.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class PostedVacancy implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private boolean taken;
	
	//Foreign key for vacancy
	@ManyToOne
	@JoinColumn(name = "vacancy", nullable = false)
	private Vacancy vacancy;
	
	//Foreign key for employeers
	@ManyToOne
	@JoinColumn(name = "employeer", nullable = false)
	private Employeer employeer;
	
}
 