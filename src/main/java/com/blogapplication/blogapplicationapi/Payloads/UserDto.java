package com.blogapplication.blogapplicationapi.Payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	private int id;
	@NotEmpty
	@Size(min = 4, message = "minimum 4 characters")
	private String name;
	@Email(message = "Email is Not Valid")
	@Column(unique = true)
	private String email;
	@NotEmpty
	@Size(min = 4, max = 15, message = "Length in between [4-15]")
	private String password;
	@NotEmpty
	private String about;
}
