package com.blogapplication.blogapplicationapi.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IdNotFoundException extends RuntimeException {

	private String msg;

	public IdNotFoundException() {

	}
}
