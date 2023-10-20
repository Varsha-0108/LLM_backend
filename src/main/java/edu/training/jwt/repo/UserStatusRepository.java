package edu.training.jwt.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import edu.training.jwt.entity.UserStatus;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Long>
{
	@Query("SELECT us FROM UserStatus us WHERE us.lid = :lid AND us.email = :email")
	UserStatus findByLidAndEmail(String lid, String email);

	List<UserStatus> findByEmail(String email);

	@Query("FROM UserStatus WHERE lid=:lid AND uid=:uid")
	UserStatus findByLidandUid(String lid, String uid);
	
	@Query("SELECT sname, COUNT(u) FROM UserStatus u GROUP BY sname")
    List<Object[]> countUsersBySname();
    
    List<UserStatus> findByExpdateBetween(LocalDate insdate, LocalDate expdate);
}
