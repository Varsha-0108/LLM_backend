package edu.training.jwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long urid;
	private String uid;
	private String sid;
	private String fname;
	private String email;
	private String sname;
	private String lid;
}
