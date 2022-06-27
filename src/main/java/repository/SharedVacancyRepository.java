package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.edu.utez.talenting.entity.SharedVacancy;

@Repository
public interface SharedVacancyRepository extends JpaRepository<SharedVacancy, Long>{

}
