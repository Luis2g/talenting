package mx.edu.utez.talenting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.talenting.entity.Resume;
import mx.edu.utez.talenting.repository.ResumeRepository;

@Service
public class ResumeService {

	@Autowired
	private ResumeRepository resumeRepo;
	
	public List<Resume> getAll(){
		return resumeRepo.findAll();
	}
	
	public Resume getOne(long id) {
		return resumeRepo.findById(id).get();
	}
	
	public Resume saveOrUpdate(Resume cerficationOrCourse) {
		return resumeRepo.save(cerficationOrCourse);
	}
	
	public void remove(long id) {
		resumeRepo.deleteById(id);
	}
	
}
