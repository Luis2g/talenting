package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.edu.utez.talenting.entity.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long>{

}
