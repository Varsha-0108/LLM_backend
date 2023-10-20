package edu.training.jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.training.jwt.entity.UserRequest;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequest, Long> 
{
	@Query("SELECT ur FROM UserRequest ur WHERE ur.lid = :lid AND ur.email = :email")
	UserRequest findByLidAndEmail(String lid, String email);
}

