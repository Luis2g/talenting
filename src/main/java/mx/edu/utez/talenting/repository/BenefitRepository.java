package mx.edu.utez.talenting.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.edu.utez.talenting.entity.Benefit;
import mx.edu.utez.talenting.entity.Vacancy;

@Repository
public interface BenefitRepository extends JpaRepository<Benefit, Long>{
	
	List<Benefit> findByVacancy(Vacancy vacancy);
	

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM benefits WHERE vacancy = :vacancyId", nativeQuery=true)
	void removeBenefits(long vacancyId);
	
}
