package mx.edu.utez.talenting.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.edu.utez.talenting.entity.Skill;

@Repository
@Transactional
public interface SkillRepository extends JpaRepository<Skill, Long>{

	public List<Skill> findByResumeId(long id);
	
	@Modifying
	@Query(value= "DELETE FROM skills WHERE resume = :id", nativeQuery = true)
	void deleteByResume(long id);
}
