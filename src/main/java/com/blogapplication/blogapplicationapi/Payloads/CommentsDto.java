package com.blogapplication.blogapplicationapi.Payloads;

import java.time.Instant;

//import com.blogapplication.blogapplicationapi.Model.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentsDto {
	private int commentId;
	private String commentContent;
	private Instant commentDate;
	

}
