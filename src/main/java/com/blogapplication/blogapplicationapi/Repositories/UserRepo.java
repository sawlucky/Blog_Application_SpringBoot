package com.blogapplication.blogapplicationapi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogapplication.blogapplicationapi.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	boolean existsByEmail(String email);

}
