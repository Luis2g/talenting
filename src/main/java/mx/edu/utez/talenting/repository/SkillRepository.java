package mx.edu.utez.talenting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.edu.utez.talenting.entity.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{

	@Query(value = "SELECT * FROM skills s WHERE s.id = :id", nativeQuery = true)
	public List<Skill> findByResumeId(@Param("id") long id);
	
}
