package mx.edu.utez.talenting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.utez.talenting.dto.UserDTO;
import mx.edu.utez.talenting.entity.Employeer;
import mx.edu.utez.talenting.entity.Person;
import mx.edu.utez.talenting.entity.User;
import mx.edu.utez.talenting.helper.Encrypt;
import mx.edu.utez.talenting.service.EmployeerService;
import mx.edu.utez.talenting.service.PersonService;
import mx.edu.utez.talenting.service.UserService;

@RestController
@RequestMapping("/talenting")
@CrossOrigin(origins = "http://127.0.0.1:8081")
public class PersonController {
	
	@Autowired
	private PersonService personSer;
	@Autowired
	private EmployeerService employeerSer;
	@Autowired
	private UserService userService;
	
	@GetMapping("/people")
	public List<Person> list(){
		return personSer.getAll();
	}
	
	@GetMapping("/people/{id}")
	public Person edit(@PathVariable("id") long id) {
		return personSer.getOne(id);
	}
	
	@PutMapping("/people")
	public Person update(@RequestBody Person person) {
		return personSer.saveOrUpdate(person);
	}
	
	@PostMapping("/people")
	public Person save(@RequestBody UserDTO userDTO) {
		
		
		userDTO.getUser().setPassword(Encrypt.encrypt(userDTO.getUser().getPassword()));
		
		User user = userService.saveOrUpdate(userDTO.getUser());
		if(userDTO.getEmployeer() != null) {
			
			Employeer employeer = userDTO.getEmployeer();
			
			employeer.setPerson(user.getPerson());
			employeerSer.saveOrUpdate(employeer);
		
		}
		
		return user.getPerson();
		
	}
	
	@DeleteMapping("/people")
	public void delete(@RequestParam("id") long id) {
		personSer.remove(id);
	}

	
}
