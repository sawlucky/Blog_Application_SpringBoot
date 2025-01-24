package com.blogapplication.blogapplicationapi.Services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogapplication.blogapplicationapi.Payloads.UserDto;
import com.blogapplication.blogapplicationapi.Utils.ApiResponse;

@Service
public interface UserService {

	ResponseEntity<ApiResponse<UserDto>> createUser(UserDto user);

	ResponseEntity<ApiResponse<UserDto>> updateUser(UserDto user, int id);

	ResponseEntity<ApiResponse<UserDto>> getUserById(int id);

	ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers();

	ResponseEntity<ApiResponse<String>> deleteUser(int id);

}
