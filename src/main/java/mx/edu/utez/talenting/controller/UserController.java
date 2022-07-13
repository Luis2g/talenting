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

import mx.edu.utez.talenting.entity.User;
import mx.edu.utez.talenting.helper.Encrypt;
import mx.edu.utez.talenting.service.UserService;

@RestController
@RequestMapping("/talenting")
@CrossOrigin(origins = "http://127.0.0.1:8081")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> list(){
		return userService.getAll();
	}
	
	@PostMapping("/login")
	public User login (@RequestParam("username") String username, @RequestParam("password") String password) {
		
		password = Encrypt.encrypt(password);
		User user = userService.login(username, password);
		if(user != null) {			
			user.setPassword("");
		}
		return user;
		
	}
	
	@GetMapping("/users/{id}")
	public User edit(@PathVariable("id") long id) {
		return userService.getOne(id);
	}
	
	@PutMapping("/users")
	public User update(@RequestBody User user) {
		return userService.saveOrUpdate(user);
	}
	
	@DeleteMapping("/users")
	public void delete(@RequestParam("id") long id) {
		userService.remove(id);
	}

}
