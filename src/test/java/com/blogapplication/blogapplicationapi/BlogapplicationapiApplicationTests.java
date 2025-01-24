package com.blogapplication.blogapplicationapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blogapplication.blogapplicationapi.Repositories.UserRepo;

@SpringBootTest
class BlogapplicationapiApplicationTests {

	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}

	@Test
	public void repoTest() {
		String getClass = userRepo.getClass().getName();
		System.out.println(getClass);

		String getPackage = userRepo.getClass().getPackageName();
		System.out.println(getPackage);
	}

}
