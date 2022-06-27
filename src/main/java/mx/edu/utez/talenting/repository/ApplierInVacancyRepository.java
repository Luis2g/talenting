package mx.edu.utez.talenting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.edu.utez.talenting.entity.ApplierInVacancy;

@Repository
public interface ApplierInVacancyRepository extends JpaRepository<ApplierInVacancy, Long> {

}
