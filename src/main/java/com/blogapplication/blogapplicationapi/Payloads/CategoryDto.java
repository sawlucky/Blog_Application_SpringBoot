package com.blogapplication.blogapplicationapi.Payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
    
	private Integer category_id;
	@NotEmpty
	private String category_title;
	@NotEmpty
	private String category_description;

}
