package com.blogapplication.blogapplicationapi.Services.Impl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogapplication.blogapplicationapi.Exceptions.IdNotFoundException;
import com.blogapplication.blogapplicationapi.Model.Comments;
import com.blogapplication.blogapplicationapi.Model.Post;
import com.blogapplication.blogapplicationapi.Payloads.CommentsDto;
import com.blogapplication.blogapplicationapi.Repositories.CommentsRepo;
import com.blogapplication.blogapplicationapi.Repositories.PostRepo;
import com.blogapplication.blogapplicationapi.Services.CommentService;
import com.blogapplication.blogapplicationapi.Utils.ApiResponse;

@Service
public class CommentsServiceImpl implements CommentService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentsRepo commentsRepo;

	@Override
	public ResponseEntity<ApiResponse<CommentsDto>> createComments(CommentsDto commentsDto, int post_id) {
		Post post = postRepo.findById(post_id).orElseThrow(() -> new IdNotFoundException(post_id + "invalid id"));
		Comments comments = CommentsDtoTOComments(commentsDto);
		comments.setPost(post);

		Comments savedComments = commentsRepo.save(comments);

		ApiResponse<CommentsDto> api = new ApiResponse<CommentsDto>(HttpStatus.CREATED.value(),
				"comments created on the post Successfully", CommentsToCommentsDto(savedComments));
		return new ResponseEntity<ApiResponse<CommentsDto>>(api, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ApiResponse<CommentsDto>> updateComments(CommentsDto commentsDto, int comments_id) {
		Comments comments = commentsRepo.findById(comments_id)
				.orElseThrow(() -> new IdNotFoundException(comments_id + "invalid Id "));
		comments.setCommentContent(commentsDto.getCommentContent());
		comments.setCommentDate(Instant.now());
		Comments savedComments = commentsRepo.save(comments);
		ApiResponse<CommentsDto> api = new ApiResponse<CommentsDto>(HttpStatus.OK.value(),
				"comments updated on the post Successfully", CommentsToCommentsDto(savedComments));
		return new ResponseEntity<ApiResponse<CommentsDto>>(api, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<ApiResponse<CommentsDto>> deleteComments(int comments_id) {
		Optional<Comments> o = commentsRepo.findById(comments_id);
				if(!o.isPresent())
				{
					throw new IdNotFoundException(comments_id + "invalid Id ");
				}
		this.commentsRepo.delete(o.get());
		ApiResponse<CommentsDto> api = new ApiResponse<CommentsDto>(HttpStatus.OK.value(), "deleted the data succesffuly", CommentsToCommentsDto(o.get()));
		return new ResponseEntity<ApiResponse<CommentsDto>>(api, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<List<CommentsDto>>> getAllComments(int post_id) {
		Post post = postRepo.findById(post_id).orElseThrow(() -> new IdNotFoundException(post_id + "invalid id"));
		List<Comments> comments = commentsRepo.findByPost(post);
		List<CommentsDto> newList = comments.stream().map((items) -> CommentsToCommentsDto(items))
				.collect(Collectors.toList());
		ApiResponse<List<CommentsDto>> api = new ApiResponse<List<CommentsDto>>(HttpStatus.ACCEPTED.value(),
				"here is the list ", newList);
		return new ResponseEntity<ApiResponse<List<CommentsDto>>>(api, HttpStatus.ACCEPTED);
	}

	public CommentsDto CommentsToCommentsDto(Comments comments) {
		return modelMapper.map(comments, CommentsDto.class);
	}

	public Comments CommentsDtoTOComments(CommentsDto commentsDto) {
		return modelMapper.map(commentsDto, Comments.class);
	}

}
