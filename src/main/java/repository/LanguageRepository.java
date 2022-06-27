package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.edu.utez.talenting.entity.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long>{

}
