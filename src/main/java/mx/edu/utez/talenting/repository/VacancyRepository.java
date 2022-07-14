package mx.edu.utez.talenting.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.edu.utez.talenting.entity.Employeer;
import mx.edu.utez.talenting.entity.Vacancy;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
	
	List<Vacancy> findByEmployeer (Employeer employeer);
	
	List<Vacancy> findByStateInWhichIsAvailableAndStatus(String state, boolean value);
	
	@Modifying
	@Transactional
	@Query(value="SELECT * FROM vacancies V JOIN appliers_in_vacancies AIV ON V.id = AIV.vacancy WHERE AIV.person = :personId", nativeQuery=true)
	List<Vacancy> getVacanciesByApplier(long personId);
	
}
