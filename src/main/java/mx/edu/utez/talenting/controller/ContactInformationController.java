package mx.edu.utez.talenting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.utez.talenting.entity.ContactInformation;
import mx.edu.utez.talenting.service.ContactInformationService;

@RestController
@RequestMapping("/talenting")
public class ContactInformationController {
	
	@Autowired
	private ContactInformationService contactInformationSer;
	
	@GetMapping("/contactInformations")
	public List<ContactInformation> list(){
		return contactInformationSer.getAll();
	}
	
	@GetMapping("/contactInformations/{id}")
	public ContactInformation edit(@PathVariable("id") long id) {
		return contactInformationSer.getOne(id);
	}
	
	@PutMapping("/contactInformations")
	public ContactInformation update(@RequestBody ContactInformation contactInformation) {
		return contactInformationSer.saveOrUpdate(contactInformation);
	}
	
	@DeleteMapping("/contactInformations")
	public void delete(@RequestParam("id") long id) {
		contactInformationSer.remove(id);
	}

	
}
