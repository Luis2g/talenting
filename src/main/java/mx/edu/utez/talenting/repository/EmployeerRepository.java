package mx.edu.utez.talenting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.edu.utez.talenting.entity.Employeer;

@Repository
public interface EmployeerRepository extends JpaRepository<Employeer, Long>{

	Employeer findByPerson_id(long id);
	
	Employeer findByPersonId(long id);
}
