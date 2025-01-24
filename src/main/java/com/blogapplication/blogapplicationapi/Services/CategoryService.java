package com.blogapplication.blogapplicationapi.Services;

import com.blogapplication.blogapplicationapi.Payloads.CategoryDto;
import com.blogapplication.blogapplicationapi.Utils.ApiResponse;

import java.util.*;

import org.springframework.http.ResponseEntity;

public interface CategoryService {

	ResponseEntity<ApiResponse<CategoryDto>> createCategory(CategoryDto categoryDto);

	ResponseEntity<ApiResponse<CategoryDto>> updateCategory(CategoryDto categoryDto, Integer category_id);

	ResponseEntity<ApiResponse<String>> DeleteCategory(Integer category_id);

	ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategory();

	ResponseEntity<ApiResponse<CategoryDto>> getCategoryById(Integer category_id);

//	ResponseEntity<ApiResponse<CategoryDto>> updateCategory(CategoryDto categoryDto, Integer category_id);

}
