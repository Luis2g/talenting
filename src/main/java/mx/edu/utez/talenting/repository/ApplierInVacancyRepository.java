package mx.edu.utez.talenting.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.edu.utez.talenting.entity.ApplierInVacancy;
import mx.edu.utez.talenting.entity.Person;

@Repository
public interface ApplierInVacancyRepository extends JpaRepository<ApplierInVacancy, Long> {

	List<ApplierInVacancy> findByPerson(Person person);
	
	@Modifying
	@Transactional
	@Query(value="SELECT * FROM appliers_in_vacancies av JOIN vacancies v on av.vacancy = v.id"
			+ " WHERE v.id = :vacancyId ORDER BY status ASC", nativeQuery=true)
	List<ApplierInVacancy> getAppliersByVacancy(long vacancyId);

	@Modifying
	@Transactional
	@Query(value="UPDATE appliers_in_vacancies SET status = :status WHERE id = :id", nativeQuery=true)
	int changeStatus(String status, long id);

	
}
