package mx.edu.utez.talenting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.edu.utez.talenting.entity.ApplierInVacancy;
import mx.edu.utez.talenting.entity.Person;
import mx.edu.utez.talenting.entity.SharedVacancy;

@Repository
public interface SharedVacancyRepository extends JpaRepository<SharedVacancy, Long>{

	List<SharedVacancy> findByPerson(Person person);
	
}
