package mx.edu.utez.talenting.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.talenting.entity.Resume;
import mx.edu.utez.talenting.entity.Skill;
import mx.edu.utez.talenting.repository.SkillRepository;

@Service
public class SkillService {

	@Autowired
	private SkillRepository skillRepo;
	
	public List<Skill> getAll(){
		return skillRepo.findAll();
	}
	
	public Skill getOne(long id) {
		return skillRepo.findById(id).get();
	}
	
	public boolean save(Skill obj) {
		boolean flag = false;
		Skill tmp = skillRepo.save(obj);
		if (tmp != null) {
			flag = true;
		}
		return flag;
	}
	
	public Skill saveOrUpdate(Skill cerficationOrCourse) {
		return skillRepo.save(cerficationOrCourse);
	}
	
	public void remove(long id) {
		skillRepo.deleteById(id);
	}
	
	public void deleteByResume(long id) {
		System.out.println("Id del resume: "+id);
		skillRepo.deleteByResume(id);
	}
	
	@Transactional
	public List<Skill> findAllByResumeId(long id) {
		return skillRepo.findByResumeId(id);
	}
	
}
