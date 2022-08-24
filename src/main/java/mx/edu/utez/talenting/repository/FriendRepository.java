package mx.edu.utez.talenting.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import mx.edu.utez.talenting.entity.Friend;


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
	

	@Query(value="SELECT F.* FROM people P INNER JOIN friends F ON P.id = F.person OR P.id = F.friend WHERE P.id IN(SELECT friend FROM friends WHERE (person = 1 OR friend = 1)  AND status = 1 );", nativeQuery=true)
	List<Friend> getFriendsList(long personId);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM friends WHERE (person = :personId AND friend = :friendId) OR (person = :friendId AND friend = :personId)", nativeQuery = true)
	void deleteFriend(long personId, long friendId);
	
	
}
