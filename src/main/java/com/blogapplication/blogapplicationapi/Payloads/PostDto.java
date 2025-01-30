package com.blogapplication.blogapplicationapi.Payloads;

import java.time.Instant;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
@Data
@NoArgsConstructor
public class PostDto {

	@JsonProperty("post_id")
	private int postId;

	@JsonProperty("post_title")
	private String postTitle;

	@JsonProperty("post_content")
	private String postContent;

	@JsonProperty("post_image")
	private MultipartFile postImage;

	@JsonProperty("post_date")
	private Instant postDate;

	private CategoryDto category;

	private UserDto user;
	
	private List<CommentsDto> comments= new ArrayList<>();
}
