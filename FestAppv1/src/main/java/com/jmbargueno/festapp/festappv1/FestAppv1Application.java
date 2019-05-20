package com.jmbargueno.festapp.festappv1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
/**
 * @author jmbargueno
 *
 */
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jmbargueno.festapp.festappv1.model.UserFA;
import com.jmbargueno.festapp.festappv1.service.UserService;

@SpringBootApplication
public class FestAppv1Application {

	public static void main(String[] args) {
		SpringApplication.run(FestAppv1Application.class, args);
	}
	
//	@Bean
//	public CommandLineRunner init(UserService userService, BCryptPasswordEncoder passwordEncoder) {
//		return args -> {
//			
//			UserFA u = new UserFA();
//			u.setAdmin(false);
//			u.setName("User");
//			u.setSurname("User");
//			u.setEmail("user@email.com");
//			u.setPassword(passwordEncoder.encode("1234"));
//			
//			userService.save(u);
//			
//			
//			UserFA a = new UserFA();
//			a.setAdmin(true);
//			a.setName("Admin");
//			a.setSurname("Admin");
//			a.setEmail("admin@email.com");
//			a.setUsername("admin");
//			a.setPassword(passwordEncoder.encode("1234"));
//			
//			userService.save(a);
//			
//		};
//	}

}
