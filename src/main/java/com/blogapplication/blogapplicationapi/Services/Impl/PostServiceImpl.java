package com.blogapplication.blogapplicationapi.Services.Impl;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogapplication.blogapplicationapi.Exceptions.IdNotFoundException;
import com.blogapplication.blogapplicationapi.Model.Category;
import com.blogapplication.blogapplicationapi.Model.Post;
import com.blogapplication.blogapplicationapi.Model.User;
import com.blogapplication.blogapplicationapi.Payloads.PostDto;
import com.blogapplication.blogapplicationapi.Repositories.CategoryRepo;
import com.blogapplication.blogapplicationapi.Repositories.PostRepo;
import com.blogapplication.blogapplicationapi.Repositories.UserRepo;
import com.blogapplication.blogapplicationapi.Services.CloudinaryService;
import com.blogapplication.blogapplicationapi.Services.PostService;
import com.blogapplication.blogapplicationapi.Utils.ApiResponse;
import com.blogapplication.blogapplicationapi.Utils.CreatePostResponse;
import com.blogapplication.blogapplicationapi.Utils.PostResponse;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	CloudinaryService cloudinaryService;

	@Override
	public ResponseEntity<CreatePostResponse<PostDto>> createPost(PostDto postDto, int userId, int categoryId) {
		// Validate user
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new IdNotFoundException("User ID " + userId + " not found"));

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new IdNotFoundException("Category ID " + categoryId + " not found"));

		Post post = PostDtoToPost(postDto);
		post.setUser(user);
		post.setCategory(category);
		Map imageResponse = null;
		if (postDto.getPostImage() != null && !postDto.getPostImage().isEmpty()) {
			try {
				imageResponse = cloudinaryService.upload(postDto.getPostImage());
				System.out.println("Type of public_id: " + imageResponse.get("public_id").getClass().getName());

				post.setPostImage((String) imageResponse.get("public_id"));

			} catch (Exception e) {
				throw new RuntimeException("Image upload failed: " + e.getMessage());
			}
		}

		Post newPost = postRepo.save(post);
//		PostDto newPostDto = PostToPostDto(newPost);

//		System.out.println(PostToPostDto(newPost).getPostImage());

		CreatePostResponse<PostDto> cpr = new CreatePostResponse<>(HttpStatus.CREATED.value(),
				"Post created successfully", imageResponse, PostToPostDto(newPost));

		return new ResponseEntity<>(cpr, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ApiResponse<PostDto>> updatePost(PostDto postDto, int postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new IdNotFoundException("Post ID " + postId + " not found"));

		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setPostImage("updated");
		post.setPostDate(Instant.now());
		Post updatedPost = postRepo.save(post);
		ApiResponse<PostDto> api = new ApiResponse<>(HttpStatus.OK.value(), "Post updated successfully",
				PostToPostDto(updatedPost));

		return new ResponseEntity<>(api, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<String>> deletePost(int id) {
		Post post = postRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Post ID " + id + " not found"));

		postRepo.delete(post);
		ApiResponse<String> api = new ApiResponse<>(HttpStatus.ACCEPTED.value(), "Post deleted successfully",
				"Post with ID " + id + " has been deleted");

		return new ResponseEntity<>(api, HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<PostResponse<List<PostDto>>> getAllPosts(int pageNumber, int pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
		Page<Post> pagePosts = postRepo.findAll(pageable);

		List<PostDto> postDtos = pagePosts.getContent().stream().map(this::PostToPostDto).collect(Collectors.toList());

		PostResponse<List<PostDto>> postResponse = new PostResponse<>(pagePosts.getTotalElements(),
				pagePosts.getNumber(), pagePosts.getSize(), pagePosts.getTotalPages(), postDtos, pagePosts.isLast());

		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<PostDto>> getPostById(int postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new IdNotFoundException("Post ID " + postId + " not found"));

		ApiResponse<PostDto> api = new ApiResponse<>(HttpStatus.OK.value(), "Post fetched successfully",
				PostToPostDto(post));

		return new ResponseEntity<>(api, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PostResponse<List<PostDto>>> getPostsByCategory(int categoryId, int pageNumber,
			int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new IdNotFoundException("Category ID " + categoryId + " not found"));

		Page<Post> pagePosts = postRepo.findByCategory(category, pageable);

		List<PostDto> postDtos = pagePosts.getContent().stream().map(this::PostToPostDto).collect(Collectors.toList());

		PostResponse<List<PostDto>> postResponse = new PostResponse<>(pagePosts.getTotalElements(),
				pagePosts.getNumber(), pagePosts.getSize(), pagePosts.getTotalPages(), postDtos, pagePosts.isLast());

		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PostResponse<List<PostDto>>> getPostsByUser(int userId, int pageNumber, int pageSize) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new IdNotFoundException("User ID " + userId + " not found"));

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePosts = postRepo.findByUser(user, pageable);

		List<PostDto> postDtos = pagePosts.getContent().stream().map(this::PostToPostDto).collect(Collectors.toList());

		PostResponse<List<PostDto>> postResponse = new PostResponse<>(pagePosts.getTotalElements(),
				pagePosts.getNumber(), pagePosts.getSize(), pagePosts.getTotalPages(), postDtos, pagePosts.isLast());

		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<String>> deleteAllPostsByUser(int userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new IdNotFoundException("User ID " + userId + " not found"));

		postRepo.deleteAll();
		ApiResponse<String> api = new ApiResponse<>(HttpStatus.ACCEPTED.value(), "All posts deleted successfully",
				"All posts by user " + user.getName() + " have been deleted");

		return new ResponseEntity<>(api, HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<ApiResponse<List<PostDto>>> searchPostByTitle(String keyword) {
		List<Post> list = postRepo.findBypostTitleContaining(keyword);
		List<PostDto> newList = list.stream().map((items) -> PostToPostDto(items)).collect(Collectors.toList());
		ApiResponse<List<PostDto>> api = new ApiResponse<List<PostDto>>(HttpStatus.ACCEPTED.value(), keyword, newList);
		return new ResponseEntity<ApiResponse<List<PostDto>>>(api, HttpStatus.ACCEPTED);
	}

	private Post PostDtoToPost(PostDto postDto) {
		return modelMapper.map(postDto, Post.class);
	}

	private PostDto PostToPostDto(Post post) {
		return modelMapper.map(post, PostDto.class);
	}
}
