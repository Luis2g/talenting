package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.edu.utez.talenting.entity.FavoriteVacancy;

@Repository
public interface FavoriteVacancyRepository extends JpaRepository<FavoriteVacancy, Long>{

}
