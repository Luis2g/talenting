package mx.edu.utez.talenting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.talenting.entity.Employeer;
import mx.edu.utez.talenting.repository.EmployeerRepository;

@Service
public class EmployeerService {

	@Autowired
	private EmployeerRepository employeerRepo;
	
	public List<Employeer> getAll(){
		return employeerRepo.findAll();
	}
	
	public Employeer getOne(long id) {
		return employeerRepo.findById(id).get();
	}
	
	public Employeer findByPerson(long id) {
		return employeerRepo.findByPerson_id(id);
	}
	
	public Employeer saveOrUpdate(Employeer cerficationOrCourse) {
		return employeerRepo.save(cerficationOrCourse);
	}
	
	public void remove(long id) {
		employeerRepo.deleteById(id);
	}
	
	public Employeer getOneByPersonId(long id) {
		return employeerRepo.findByPersonId(id);
	}
	
}
