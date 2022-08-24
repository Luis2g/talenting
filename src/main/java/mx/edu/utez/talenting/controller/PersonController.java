package mx.edu.utez.talenting.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import mx.edu.utez.talenting.dto.FriendDTO;
import mx.edu.utez.talenting.dto.UserChangePasswordDTO;

import mx.edu.utez.talenting.dto.UserDTO;
import mx.edu.utez.talenting.entity.Employeer;
import mx.edu.utez.talenting.entity.Friend;
import mx.edu.utez.talenting.entity.Person;
import mx.edu.utez.talenting.entity.User;
import mx.edu.utez.talenting.helper.Encrypt;
import mx.edu.utez.talenting.service.EmployeerService;
import mx.edu.utez.talenting.service.FriendService;
import mx.edu.utez.talenting.service.MailService;
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
	@Autowired
	private FriendService friendSer;
	
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

		if(!(userDTO.getUser().getId() > 0)) {
			userDTO.getUser().setPassword(Encrypt.encrypt(userDTO.getUser().getPassword()));
		}
	
		User user = userService.saveOrUpdate(userDTO.getUser());
		if(userDTO.getEmployeer() != null) {
			
			Employeer employeer = userDTO.getEmployeer();
			
			employeer.setPerson(user.getPerson());
			employeerSer.saveOrUpdate(employeer);
		
		}
		
		return user.getPerson();
		
	}
	
	@PostMapping("/people/changePassword")
	public User changePassword(@RequestBody UserChangePasswordDTO userChangePasswordDTO){
		User user = new User();
		if(userChangePasswordDTO != null) {
			user = userService.getOne(userChangePasswordDTO.getIdUser());
		}
		
		if(user != null) {
			String encryptedPassword = Encrypt.encrypt(userChangePasswordDTO.getOldPassword());
			if(encryptedPassword.equals(user.getPassword())) {
				user.setPassword(Encrypt.encrypt(userChangePasswordDTO.getPassword()));
				userService.saveOrUpdate(user);
			}else {
				user = new User();
			}
		}
		return user;
	}
	
	@DeleteMapping("/people")
	public void delete(@RequestParam("id") long id) {
		personSer.remove(id);
	}
	
	@GetMapping("/getPeople")
	public List<FriendDTO> getPeopleToAddAsFriends(@RequestParam("personId") long personId){
				
		List<Person> people = personSer.getPeopleToAddAsFriends(personId);
		List<Friend> requesters = friendSer.findByFriend(personId);
		List<Friend> requestedOnes = friendSer.findByPerson(personId);
		
		List<FriendDTO> friendsDTO = new ArrayList<>();
		
		if(!people.isEmpty()) {
			
			for(Person person: people) {
				System.out.println(person);
				friendsDTO.add(new FriendDTO(person));
			}
			
			
			if(!requesters.isEmpty()) {
				for(Friend requester: requesters) {
					for(FriendDTO friendDTO: friendsDTO) {
						if( requester.getPerson().getId() == friendDTO.getPerson().getId() ) {
							friendDTO.setWhoSentIt("them");
							friendDTO.setId(requester.getId());
						}
					}									
				}
			}
			if(!requestedOnes.isEmpty()) {
				for(Friend requestedOne: requestedOnes) {
					for(FriendDTO friendDTO: friendsDTO) {
						if( requestedOne.getFriend().getId() == friendDTO.getPerson().getId() ) {
							friendDTO.setWhoSentIt("me");
							friendDTO.setId(requestedOne.getId());
						}
					}									
				}
			}
			
		}
		
		return friendsDTO;
	}

}
