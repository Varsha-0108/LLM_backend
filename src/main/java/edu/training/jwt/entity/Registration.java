package edu.training.jwt.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class Registration {
	
	@Id
	private String uemail;
	
private String ufname;
private String ulname;
private String uid;
private String uuid;
private String uusername;
private String password;
}
