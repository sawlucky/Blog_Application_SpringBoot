package com.blogapplication.blogapplicationapi.Payloads;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String postImage="default.png";

    @JsonProperty("post_date") 
    private Instant postDate;

    private CategoryDto category;

    private UserDto user;
}
