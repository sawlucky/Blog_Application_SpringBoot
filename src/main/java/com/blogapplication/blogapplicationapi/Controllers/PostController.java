package com.blogapplication.blogapplicationapi.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogapplication.blogapplicationapi.Payloads.PostDto;
import com.blogapplication.blogapplicationapi.Services.CloudinaryService;
import com.blogapplication.blogapplicationapi.Services.PostService;
import com.blogapplication.blogapplicationapi.Utils.ApiResponse;
import com.blogapplication.blogapplicationapi.Utils.CreatePostResponse;
import com.blogapplication.blogapplicationapi.Utils.PostResponse;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:3000")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	CloudinaryService cloudinaryService;

	@PostMapping("api/post/create")
	public ResponseEntity<CreatePostResponse<PostDto>> createPost(@RequestPart("post_title") String postTitle,
			@RequestPart("post_content") String postContent,
			@RequestPart(value = "postImage", required = false) MultipartFile postImage,
			@RequestParam("userId") int userId, @RequestParam("category_id") int categoryId) {

		PostDto postDto = new PostDto();
		postDto.setPostContent(postContent);

		postDto.setPostTitle(postTitle);
		if (postImage != null) {
			postDto.setPostImage(postImage);
		}
		return postService.createPost(postDto, userId, categoryId);

//		return ResponseEntity.ok("Post created successfully");
	}

//	@PostMapping("api/post/create")
//	public ResponseEntity<CreatePostResponse<PostDto>> createPost(
//	        @RequestPart("post_title") String postTitle,
//	        @RequestPart("post_content") String postContent,
//	        @RequestPart("postImage") MultipartFile postImage,
//	        @RequestParam int userId,
//	        @RequestParam int category_id) {
//
//	    PostDto postDto = new PostDto();
//	    postDto.setPostTitle(postTitle);
//	    postDto.setPostContent(postContent);
//	    if (postImage != null && !postImage.isEmpty()) {
//	        // Handle the file (e.g., save it to a database or cloud storage)
//	        postDto.setPostImage(postImage);
//	    }
//
//	    return postService.createPost(postDto, userId, category_id);
//	}

//	@PostMapping("api/user/{userId}/category/{category_id}/posts")
//	public ResponseEntity<CreatePostResponse<PostDto>> createPosts(@RequestBody PostDto postDto, @PathVariable int userId,
//			@PathVariable int category_id) {
//		return postService.createPost(postDto, userId, category_id);
//	}

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
			@RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize,
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
