package mx.edu.utez.talenting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.edu.utez.talenting.entity.Benefit;

@Repository
public interface BenefitRepository extends JpaRepository<Benefit, Long>{

}
