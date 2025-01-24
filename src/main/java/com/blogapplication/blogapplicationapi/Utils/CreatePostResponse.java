package com.blogapplication.blogapplicationapi.Utils;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class CreatePostResponse<T> {
	private int statusCode;
	private String msg;
	private Map imageResponse;
	private T data;

}
