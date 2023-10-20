package edu.training.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LlmApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(LlmApplication.class, args);
	}

}
