package mx.edu.utez.talenting.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Friend implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//Foreign key for person one
	@ManyToOne
	@JoinColumn(name = "person", nullable = false)
	private Person person;
	
	//Foreign key for person two
	@ManyToOne
	@JoinColumn(name = "friend", nullable = false)
	private Person friend;
	
	
	
}
