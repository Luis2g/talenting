package mx.edu.utez.talenting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public Skill saveOrUpdate(Skill cerficationOrCourse) {
		return skillRepo.save(cerficationOrCourse);
	}
	
	public void remove(long id) {
		skillRepo.deleteById(id);
	}
	
}
