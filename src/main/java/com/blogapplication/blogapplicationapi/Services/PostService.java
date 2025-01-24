package com.blogapplication.blogapplicationapi.Services;

import java.util.List;

import org.springframework.http.ResponseEntity;

//import com.blogapplication.blogapplicationapi.Model.User;
import com.blogapplication.blogapplicationapi.Payloads.PostDto;
import com.blogapplication.blogapplicationapi.Utils.ApiResponse;
import com.blogapplication.blogapplicationapi.Utils.PostResponse;

public interface PostService {

	ResponseEntity<ApiResponse<PostDto>> createPost(PostDto postDto, int userId, int category_id);

	ResponseEntity<ApiResponse<PostDto>> updatePost(PostDto postDto, int postId);

	ResponseEntity<PostResponse<List<PostDto>>> getAllPosts(int pgaeNumber, int pageSize, String sortBy);

	ResponseEntity<ApiResponse<PostDto>> getPostById(int postId);

	ResponseEntity<PostResponse<List<PostDto>>> getPostsByCategory(int category_id, int pageNumber, int pageSize);

	ResponseEntity<PostResponse<List<PostDto>>> getPostsByUser(int user_id, int pgaeNumber, int pageSize);

	ResponseEntity<ApiResponse<String>> deleteAllPostsByUser(int user_id);

	ResponseEntity<ApiResponse<String>> deletePost(int id);

	ResponseEntity<ApiResponse<List<PostDto>>> searchPostByTitle(String keyword);

}
