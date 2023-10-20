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
public class License {
	@Id
	private String lid;
	private String sname;
	private String version;
	private String vendor;
	private String validity;
	private String acquisition;
	

}
