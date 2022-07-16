package mx.edu.utez.talenting.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.edu.utez.talenting.entity.Friend;
import mx.edu.utez.talenting.entity.Vacancy;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long>{
	
	
}
