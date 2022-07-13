package mx.edu.utez.talenting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.edu.utez.talenting.entity.User;
import mx.edu.utez.talenting.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public List<User> getAll(){
		return userRepo.findAll();
	}
	
	public User getOne(long id) {
		return userRepo.findById(id).get();
	}
	
	public User saveOrUpdate(User user) {
		return userRepo.save(user);
	}
	
	public void remove(long id) {
		userRepo.deleteById(id);
	}
	
}
