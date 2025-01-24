package com.blogapplication.blogapplicationapi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.blogapplicationapi.Payloads.UserDto;
//import com.blogapplication.blogapplicationapi.Repositories.UserRepo;
import com.blogapplication.blogapplicationapi.Services.UserService;
//import com.blogapplication.blogapplicationapi.Services.Impl.UserServiceImpl;
import com.blogapplication.blogapplicationapi.Utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
public class UserControllers {
	@Autowired
	private UserService userService;
//	@Autowired
//	private UserRepo userRepo;
//	@Autowired
//	private UserServiceImpl serviceImpl;

	@PostMapping("api/create")
	public ResponseEntity<ApiResponse<UserDto>> CreateUser(@Valid @RequestBody UserDto user) {

		return userService.createUser(user);

	}

	@GetMapping("api/{id}")
	public ResponseEntity<ApiResponse<UserDto>> findUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}

	@PutMapping("api/update/{id}")
	public ResponseEntity<ApiResponse<UserDto>> UpdateUser(@Valid @RequestBody UserDto user, @PathVariable int id) {
		return userService.updateUser(user, id);

	}

	@DeleteMapping("api/delete/{id}")
	public ResponseEntity<ApiResponse<String>> DeleteUser(@PathVariable int id) {
		return userService.deleteUser(id);
	}

	@GetMapping("api/findall")
	public ResponseEntity<ApiResponse<List<UserDto>>> getAllUser() {
		return userService.getAllUsers();
	}

}
