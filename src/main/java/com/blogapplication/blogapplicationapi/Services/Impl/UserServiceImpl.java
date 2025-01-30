package com.blogapplication.blogapplicationapi.Services.Impl;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogapplication.blogapplicationapi.Exceptions.IdNotFoundException;
import com.blogapplication.blogapplicationapi.Model.Role;
import com.blogapplication.blogapplicationapi.Model.User;
import com.blogapplication.blogapplicationapi.Payloads.UserDto;
import com.blogapplication.blogapplicationapi.Repositories.RoleRepo;
import com.blogapplication.blogapplicationapi.Repositories.UserRepo;
import com.blogapplication.blogapplicationapi.Services.UserService;
import com.blogapplication.blogapplicationapi.Utils.ApiResponse;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ResponseEntity<ApiResponse<UserDto>> createUser(UserDto userdto) {
		User user = this.UserDtoToUser(userdto);
		if (userRepo.existsByEmail(user.getEmail())) {
			throw new IllegalArgumentException("Email is already in use: " + user.getEmail());
		}
		Role role = roleRepo.findByName("ADMIN");
		if (role == null) {
			throw new RuntimeException("ADMIN IS not present in the database");

		}
		user.getRoles().add(role);
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepo.save(user);
		User savedUser = userRepo.save(user);
//		System.out.println(user.getRoles());
//		UserDto dto = userToDto(savedUser);
//		dto.setRole(user.getRoles());
		ApiResponse<UserDto> api = new ApiResponse<UserDto>(HttpStatus.CREATED.value(), "user created Successfully",
				userToDto(savedUser));
		return new ResponseEntity<ApiResponse<UserDto>>(api, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ApiResponse<UserDto>> updateUser(UserDto userdto, int id) {

		Optional<User> o = userRepo.findById(id);
		if (!o.isPresent()) {

			throw new IdNotFoundException(id + " is invalid");
		}
		userdto.setId(id);
		User user = UserDtoToUser(userdto);

		User updateuser = userRepo.save(user);
		ApiResponse<UserDto> api = new ApiResponse<UserDto>(HttpStatus.OK.value(), "data updated succesfully",
				userToDto(updateuser));
		return new ResponseEntity<ApiResponse<UserDto>>(api, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<UserDto>> getUserById(int id) {

		Optional<User> o = userRepo.findById(id);
		if (!o.isPresent()) {
			throw new IdNotFoundException(id + " invalid id cannot process...");
		}

		ApiResponse<UserDto> api = new ApiResponse<UserDto>(HttpStatus.FOUND.value(), "id found", userToDto(o.get()));
		return new ResponseEntity<ApiResponse<UserDto>>(api, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {

		List<User> list = userRepo.findAll();
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponse<>(HttpStatus.OK.value(), "No users found", Collections.emptyList()));
		}

		List<UserDto> newList = new ArrayList<>();
		for (User user : list) {
			newList.add(userToDto(user));
		}

		ApiResponse<List<UserDto>> apiResponse = new ApiResponse<>(HttpStatus.OK.value(),
				"All users found successfully", newList);

		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@Override
	public ResponseEntity<ApiResponse<String>> deleteUser(int id) {
		Optional<User> o = userRepo.findById(id);
		if (!o.isPresent()) {
			throw new IdNotFoundException(id + " invalid id cannot process...");
		}

		userRepo.delete(o.get());
		ApiResponse<String> api = new ApiResponse<String>(HttpStatus.OK.value(), "deleted Successfully", null);
		return new ResponseEntity<ApiResponse<String>>(api, HttpStatus.OK);

	}

	public User UserDtoToUser(UserDto user) {
		User u = this.modelMapper.map(user, User.class);
//		User u = new User();
//		u.setId(user.getId());
//		u.setName(user.getName());
//		u.setEmail(user.getEmail());
//		u.setPassword(user.getPassword());
//		u.setAbout(user.getAbout());
		return u;
	}

	public UserDto userToDto(User user) {
		UserDto dto = this.modelMapper.map(user, UserDto.class);
//		UserDto dto = new UserDto();
//		dto.setId(user.getId());
//		dto.setName(user.getName());
//		dto.setEmail(user.getEmail());
//		dto.setPassword(user.getPassword());
//		dto.setAbout(user.getAbout());
		return dto;
	}
}
