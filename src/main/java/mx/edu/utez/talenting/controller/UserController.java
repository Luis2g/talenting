package mx.edu.utez.talenting.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

import mx.edu.utez.talenting.dto.AuthResponse;
import mx.edu.utez.talenting.entity.User;
import mx.edu.utez.talenting.security.JwtTokenUtil;
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
	
	@Autowired AuthenticationManager authManager;
	@Autowired JwtTokenUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<?> login (@RequestParam("username") String username, @RequestParam("password") String password) {

		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							username, password)
			);
			
			User user = (User) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(user);
			AuthResponse response = new AuthResponse(user.getUsername(), accessToken);
			response.setUser(userService.login(username));
			return ResponseEntity.ok().body(response);
			
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	@RolesAllowed({"employeer", "employee"})
	@GetMapping("/users/{id}")
	public User edit(@PathVariable("id") long id) {
		return userService.getOne(id);
	}
	
	@RolesAllowed({"employeer", "employee"})
	@PutMapping("/users")
	public User update(@RequestBody User user) {
		return userService.saveOrUpdate(user);
	}
	
	@RolesAllowed({"employeer", "employee"})
	@DeleteMapping("/users")
	public void delete(@RequestParam("id") long id) {
		userService.remove(id);
	}

	
}
