package mx.edu.utez.talenting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.edu.utez.talenting.entity.CertificationOrCourse;

@Repository
public interface CertificationOrCourseRepository extends JpaRepository<CertificationOrCourse, Long>{
	
	public List<CertificationOrCourse> findByResumeId(long id);

	@Modifying
	@Query("DELETE FROM CertificationOrCourse c WHERE c.resume = :resume")
	void deleteByResume(long resume);
	
}
