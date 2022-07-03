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

import mx.edu.utez.talenting.entity.Friend;
import mx.edu.utez.talenting.service.FriendService;

@RestController
@RequestMapping("/talenting")
public class FriendController {
	
	@Autowired
	private FriendService friendSer;
	
	@GetMapping("/friends")
	public List<Friend> list(){
		return friendSer.getAll();
	}
	
	@GetMapping("/friends/{id}")
	public Friend edit(@PathVariable("id") long id) {
		return friendSer.getOne(id);
	}
	
	@PutMapping("/friends")
	public Friend update(@RequestBody Friend friend) {
		return friendSer.saveOrUpdate(friend);
	}
	
	@DeleteMapping("/friends")
	public void delete(@RequestParam("id") long id) {
		friendSer.remove(id);
	}

	
}
