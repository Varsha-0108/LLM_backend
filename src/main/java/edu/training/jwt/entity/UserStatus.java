package edu.training.jwt.entity;

import java.time.LocalDate;

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
public class UserStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long usid;
	private String uid;
	private String sid;
	private String email;
	private String sname;
	private String lid;
	private LocalDate insdate;
	private LocalDate expdate;
}
