package com.pranav.blog;

import com.pranav.blog.entities.Role;
import com.pranav.blog.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogApisApplication implements CommandLineRunner {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RoleRepo roleRepo;
	public static void main(String[] args) {
		SpringApplication.run(BlogApisApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(passwordEncoder.encode("12345678"));
		try{
			Role role1 = new Role();
			role1.setId(1);
			role1.setName("ROLE_ADMIN");

			Role role2 = new Role();
			role2.setId(2);
			role2.setName("ROLE_NORMAL");

			List<Role> roles = List.of(role1,role2);
			List<Role> result = this.roleRepo.saveAll(roles);
			result.forEach(r -> {System.out.println(r.getName());});

		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
