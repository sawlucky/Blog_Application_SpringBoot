package com.blogapplication.blogapplicationapi.Services;

import com.blogapplication.blogapplicationapi.Model.Comments;
import com.blogapplication.blogapplicationapi.Payloads.CommentsDto;
import com.blogapplication.blogapplicationapi.Utils.ApiResponse;

import java.util.*;

import org.springframework.http.ResponseEntity;
public interface CommentService {
	
	ResponseEntity<ApiResponse<CommentsDto>> createComments(CommentsDto commentsDto , int post_id);
	ResponseEntity<ApiResponse<CommentsDto>> updateComments(CommentsDto commentsDto, int comments_id);
	ResponseEntity<ApiResponse<CommentsDto>> deleteComments(int comments_id);
	ResponseEntity<ApiResponse<List<CommentsDto>>> getAllComments(int post_id);

}
