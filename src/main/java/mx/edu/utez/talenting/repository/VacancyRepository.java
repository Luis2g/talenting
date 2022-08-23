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
	
	List<Vacancy> findByStatus(boolean value);
	
	@Modifying
	@Transactional
	@Query(value="SELECT * FROM vacancies V JOIN appliers_in_vacancies AIV ON V.id = AIV.vacancy WHERE AIV.person = :personId", nativeQuery=true)
	List<Vacancy> getVacanciesByApplier(long personId);
	
	
	@Modifying
	@Transactional
	@Query(value="SELECT V.* FROM vacancies V JOIN shared_Vacancies SH ON V.id = SH.vacancy\n"
			+ "			JOIN people P ON SH.person = P.id WHERE P.id IN \n"
			+ "			(( SELECT friend FROM ( SELECT friend FROM friends WHERE person = :idIn AND status = 1 ) AS tableOne )) \n"
			+ "			OR P.id IN (( SELECT person FROM ( SELECT person FROM friends WHERE friend = :idIn AND status = 1 ) AS tableTwo ))  \n"
			+ "			OR P.id = :idIn GROUP BY V.id;", nativeQuery=true)
	List<Vacancy> getSharedVacancies(long idIn);
	
	
}
