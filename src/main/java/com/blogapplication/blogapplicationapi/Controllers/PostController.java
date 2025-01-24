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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.blogapplicationapi.Payloads.PostDto;
import com.blogapplication.blogapplicationapi.Services.PostService;
import com.blogapplication.blogapplicationapi.Utils.ApiResponse;
import com.blogapplication.blogapplicationapi.Utils.PostResponse;

import jakarta.validation.Valid;

@RestController
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("api/post/create")
	public ResponseEntity<ApiResponse<PostDto>> createPost(@RequestBody PostDto postDto, @RequestParam int userId,
			@RequestParam int category_id) {
		return postService.createPost(postDto, userId, category_id);
	}

	@PostMapping("api/user/{userId}/category/{category_id}/posts")
	public ResponseEntity<ApiResponse<PostDto>> createPosts(@RequestBody PostDto postDto, @PathVariable int userId,
			@PathVariable int category_id) {
		return postService.createPost(postDto, userId, category_id);
	}

	@GetMapping("api/post/{category_id}")
	public ResponseEntity<PostResponse<List<PostDto>>> getAllPostByCategory(@PathVariable int category_id,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "4", required = false) int pageSize) {
		return postService.getPostsByCategory(category_id, pageNumber, pageSize);
	}

	@GetMapping("api/post/category/{user_id}")
	public ResponseEntity<PostResponse<List<PostDto>>> getAllPostOfUser(@PathVariable int user_id,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize) {
		return postService.getPostsByUser(user_id, pageNumber, pageSize);
	}

	@GetMapping("api/post/getAll")
	public ResponseEntity<PostResponse<List<PostDto>>> getAllPost(
			@Valid @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "8", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy) {
		return postService.getAllPosts(pageNumber, pageSize, sortBy);
	}

	@GetMapping("api/post/byId/{id}")
	public ResponseEntity<ApiResponse<PostDto>> getPostById(@PathVariable int id) {
		return postService.getPostById(id);
	}

	@PutMapping("api/post/update/{post_id}")
	public ResponseEntity<ApiResponse<PostDto>> updatePost(@RequestBody PostDto postDto, @PathVariable int post_id) {
		return postService.updatePost(postDto, post_id);
	}

	@DeleteMapping("api/post/delete/{id}")
	public ResponseEntity<ApiResponse<String>> deletePost(@PathVariable int id) {
		return postService.deletePost(id);
	}

	@DeleteMapping("api/post/deleteAll/{id}")
	public ResponseEntity<ApiResponse<String>> deleteAllPostOfUser(@PathVariable int id) {
		return postService.deleteAllPostsByUser(id);
	}

	@GetMapping("api/post/searchTitle/{title}")
	public ResponseEntity<ApiResponse<List<PostDto>>> searchByTitle(@PathVariable String title) {
		return postService.searchPostByTitle(title);
	}
}
