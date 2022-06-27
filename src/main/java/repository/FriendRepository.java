package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.edu.utez.talenting.entity.Friend;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long>{

}
