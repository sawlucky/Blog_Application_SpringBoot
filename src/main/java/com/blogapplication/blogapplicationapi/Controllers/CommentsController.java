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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.blogapplicationapi.Model.Comments;
import com.blogapplication.blogapplicationapi.Payloads.CommentsDto;
import com.blogapplication.blogapplicationapi.Services.CommentService;
import com.blogapplication.blogapplicationapi.Utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comments/")
public class CommentsController {

	@Autowired
	private CommentService commentService;

	
	@PostMapping("create/{post_id}")
	public ResponseEntity<ApiResponse<CommentsDto>> createComments(@Valid @RequestBody CommentsDto commentsDto,
			@PathVariable int post_id) {
		return commentService.createComments(commentsDto, post_id);
	}

	@PutMapping("update/{comment_id}")
	public ResponseEntity<ApiResponse<CommentsDto>> updateComments(@RequestBody CommentsDto commentsDto,
			@PathVariable int comment_id) {
		return commentService.updateComments(commentsDto, comment_id);
	}

	@DeleteMapping("delete/{comment_id}")
	public ResponseEntity<ApiResponse<CommentsDto>> updateComments(@PathVariable int comment_id) {
		return commentService.deleteComments(comment_id);
	}

	@GetMapping("getAll/{post_id}")
	public ResponseEntity<ApiResponse<List<CommentsDto>>> getAllCommentsOnPost(@PathVariable int post_id) {
		return commentService.getAllComments(post_id);
	}

}
