package mx.edu.utez.talenting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.talenting.entity.Person;
import mx.edu.utez.talenting.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepo;
	
	public List<Person> getAll(){
		return personRepo.findAll();
	}
	
	public Person getOne(long id) {
		return personRepo.findById(id).get();
	}
	
	public Person saveOrUpdate(Person cerficationOrCourse) {
		return personRepo.save(cerficationOrCourse);
	}
	
	public void remove(long id) {
		personRepo.deleteById(id);
	}
	
}
