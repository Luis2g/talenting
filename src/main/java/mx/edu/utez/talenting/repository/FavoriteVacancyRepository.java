package mx.edu.utez.talenting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.edu.utez.talenting.entity.FavoriteVacancy;
import mx.edu.utez.talenting.entity.Person;

@Repository
public interface FavoriteVacancyRepository extends JpaRepository<FavoriteVacancy, Long>{
	
	List<FavoriteVacancy> findByPerson(Person person);
	
}
