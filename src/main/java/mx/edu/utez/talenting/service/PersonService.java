package mx.edu.utez.talenting.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import mx.edu.utez.talenting.entity.Person;
import mx.edu.utez.talenting.entity.Vacancy;
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
	
	public List<Person> getFriends(long id){
		return personRepo.getFriends(id);
	}
	
	public List<Person> getPeopleWhoSharedIt(long vacancyId){
		return personRepo.getPeopleWhoSharedIt(vacancyId);
	}
	
	public List<Person> getPeopleToAddAsFriends(long id){
		return personRepo.getPeopleToAddAsFriends(id);
	}
	
}
