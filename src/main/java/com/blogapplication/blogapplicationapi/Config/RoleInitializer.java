package com.blogapplication.blogapplicationapi.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.blogapplication.blogapplicationapi.Model.Role;
import com.blogapplication.blogapplicationapi.Repositories.RoleRepo;

@Component
public class RoleInitializer implements CommandLineRunner {

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public void run(String... args) throws Exception {

		if (roleRepo.findByName("ADMIN") == null) {
			Role role = new Role();
			role.setName("ADMIN");
			roleRepo.save(role);
		}

		if (roleRepo.findByName("USER") == null) {
			Role role = new Role();
			role.setName("USER");
			roleRepo.save(role);
		}

	}

}
