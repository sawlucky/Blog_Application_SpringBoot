package com.blogapplication.blogapplicationapi.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.blogapplication.blogapplicationapi.Model.User;
import com.blogapplication.blogapplicationapi.Repositories.UserRepo;

@Service
public class CustomerDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// fetch the data of user from here
		User user = userRepo.findByEmail(username).orElseThrow(() -> new RuntimeException("username not found "));
		return user;
	}

}
