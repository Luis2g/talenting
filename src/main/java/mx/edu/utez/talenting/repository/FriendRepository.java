package mx.edu.utez.talenting.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import mx.edu.utez.talenting.entity.Friend;
import mx.edu.utez.talenting.entity.Person;


@Repository
public interface FriendRepository extends JpaRepository<Friend, Long>{
		
	List<Friend> findByFriendId(long id);
	
	List<Friend> findByPersonId(long id);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE friends SET status = 1 WHERE id = :idIn", nativeQuery=true)
	void confirmFriendshipRequest(long idIn);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM friends WHERE id = :idIn", nativeQuery=true)
	void rejectFriendshipRequest(long idIn);
	
	
	
}
