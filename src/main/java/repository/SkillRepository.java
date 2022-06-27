package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.edu.utez.talenting.entity.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{

}
