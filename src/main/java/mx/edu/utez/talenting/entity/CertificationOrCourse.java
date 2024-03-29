package mx.edu.utez.talenting.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "certifications_or_courses")
public class CertificationOrCourse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private boolean type;
	
	@Column(nullable = false)
	private String name;
	
	@Column
	private int hours;
	
	@Column(nullable = false)
	private String nameOfTheInstitudeThatIssuesIt;
	
	// These two attributes look way too similar but the aren't the same
	@Column(name = "expiration_date")
	private Date expirationDate;
	
	@Column(name = "expedition_date", nullable = false)
	private Date expeditionDate;
	
	//Foreign key for resume
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "resume", nullable = false)
	private Resume resume;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNameOfTheInstitudeThatIssuesIt() {
		return nameOfTheInstitudeThatIssuesIt;
	}

	public void setNameOfTheInstitudeThatIssuesIt(String nameOfTheInstitudeThatIssuesIt) {
		this.nameOfTheInstitudeThatIssuesIt = nameOfTheInstitudeThatIssuesIt;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getExpeditionDate() {
		return expeditionDate;
	}

	public void setExpeditionDate(Date expeditionDate) {
		this.expeditionDate = expeditionDate;
	}

	@Override
	public String toString() {
		return "CertificationOrCourse [id=" + id + ", type=" + type + ", name=" + name + ", hours=" + hours
				+ ", nameOfTheInstitudeThatIssuesIt=" + nameOfTheInstitudeThatIssuesIt + ", expirationDate="
				+ expirationDate + ", expeditionDate=" + expeditionDate + "]";
	}

	
	
	
}
