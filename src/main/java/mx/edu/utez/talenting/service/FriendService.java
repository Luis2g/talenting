package mx.edu.utez.talenting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.talenting.entity.Friend;
import mx.edu.utez.talenting.entity.Person;
import mx.edu.utez.talenting.repository.FriendRepository;
import mx.edu.utez.talenting.repository.PersonRepository;

@Service
public class FriendService {

	@Autowired
	private FriendRepository friendRepo;
	
	@Autowired
	private PersonRepository personRepo;
	
	public List<Friend> getAll(){
		return friendRepo.findAll();
	}
	
	public Friend getOne(long id) {
		return friendRepo.findById(id).get();
	}
	
	public Friend saveOrUpdate(Friend cerficationOrCourse) {
		return friendRepo.save(cerficationOrCourse);
	}
	
	public void remove(long id) {
		friendRepo.deleteById(id);
	}
	
	public List<Friend> findByFriend(long id){
		return friendRepo.findByFriendId(id);
	}
	
	public List<Friend> findByPerson(long id){
		return friendRepo.findByPersonId(id);
	}
	
	public void confirmFriendshipRequest(long id) {
		friendRepo.confirmFriendshipRequest(id);
	}
	public void rejectFriendshipRequest(long id) {
		friendRepo.rejectFriendshipRequest(id);
	}
	
	public List<Person> getFriendsList(long personId){
		return personRepo.getFriendsList(personId);
	}
	
	public void deleteFriend(long personId, long friendId) {
		friendRepo.deleteFriend(personId, friendId);
	}
	
}
