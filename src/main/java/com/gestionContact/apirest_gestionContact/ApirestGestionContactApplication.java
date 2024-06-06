package com.gestionContact.apirest_gestionContact;

import com.gestionContact.apirest_gestionContact.model.ApplicationUser;
import com.gestionContact.apirest_gestionContact.model.Role;
import com.gestionContact.apirest_gestionContact.repository.RoleRepository;
import com.gestionContact.apirest_gestionContact.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class}) //pour enlever la security
public class ApirestGestionContactApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirestGestionContactApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository , UserRepository userRepository , PasswordEncoder passwordEncoder){
		return args -> {
			if (roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			ApplicationUser admin = new ApplicationUser(1,"admin", passwordEncoder.encode("123"), roles);
			userRepository.save(admin);
		};
	}

}
