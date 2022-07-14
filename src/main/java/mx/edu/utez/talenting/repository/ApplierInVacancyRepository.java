package mx.edu.utez.talenting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.edu.utez.talenting.entity.ApplierInVacancy;
import mx.edu.utez.talenting.entity.Person;

@Repository
public interface ApplierInVacancyRepository extends JpaRepository<ApplierInVacancy, Long> {

	List<ApplierInVacancy> findByPerson(Person person);
	
}
