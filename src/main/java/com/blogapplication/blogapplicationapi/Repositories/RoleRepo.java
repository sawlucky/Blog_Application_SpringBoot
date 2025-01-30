package com.blogapplication.blogapplicationapi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.blogapplicationapi.Model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {
	Role findByName(String name);
}
