package com.healthcare.enrollment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.healthcare.enrollment.configuration.JpaConfiguration;

@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.healthcare.enrollment"}) 
public class UserEnrollmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserEnrollmentApplication.class, args);
	}
}
