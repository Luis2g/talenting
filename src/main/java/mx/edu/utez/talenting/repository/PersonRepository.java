package mx.edu.utez.talenting.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.edu.utez.talenting.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
	@Modifying
	@Transactional
	@Query(value="SELECT * FROM people WHERE id IN (( SELECT friend FROM ( SELECT friend FROM friends WHERE person = :personId AND status = 1 ) AS tableOne )) \n"
			+ "OR id IN (( SELECT person FROM ( SELECT person FROM friends WHERE friend = :personId AND status = 1 ) AS tableTwo ))", nativeQuery=true)
	List<Person> getFriends(long personId);
	
	@Modifying
	@Transactional
	@Query(value="SELECT * FROM people P JOIN shared_vacancies SV ON P.id = SV.person JOIN vacancies V ON SV.vacancy = V.id WHERE V.id = :vacancyId", nativeQuery=true)
	List<Person> getPeopleWhoSharedIt(long vacancyId);
	


}