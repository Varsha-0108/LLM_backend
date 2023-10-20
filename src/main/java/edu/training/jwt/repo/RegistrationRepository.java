package edu.training.jwt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.training.jwt.entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, String> 
{
	List<Registration> findByUemail(String uemail);

	@Query("FROM Registration WHERE uemail=:username")
	Registration getRegister(String username);
}
