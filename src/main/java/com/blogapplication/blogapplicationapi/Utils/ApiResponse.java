package com.blogapplication.blogapplicationapi.Utils;



import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
	private int statusCode;
	private String msg;
	private T data;

}
