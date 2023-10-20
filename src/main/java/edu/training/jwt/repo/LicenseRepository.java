package edu.training.jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.training.jwt.entity.License;

@Repository
public interface LicenseRepository extends JpaRepository<License, String> 
{
	

}
