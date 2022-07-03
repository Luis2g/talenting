package mx.edu.utez.talenting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.talenting.entity.ContactInformation;
import mx.edu.utez.talenting.repository.ContactInformationRepository;

@Service
public class ContactInformationService {

	@Autowired
	private ContactInformationRepository contactInformationRepo;
	
	public List<ContactInformation> getAll(){
		return contactInformationRepo.findAll();
	}
	
	public ContactInformation getOne(long id) {
		return contactInformationRepo.findById(id).get();
	}
	
	public ContactInformation saveOrUpdate(ContactInformation cerficationOrCourse) {
		return contactInformationRepo.save(cerficationOrCourse);
	}
	
	public void remove(long id) {
		contactInformationRepo.deleteById(id);
	}
	
}
