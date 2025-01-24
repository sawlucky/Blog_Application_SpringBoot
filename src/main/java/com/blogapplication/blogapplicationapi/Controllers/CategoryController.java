package com.blogapplication.blogapplicationapi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.blogapplicationapi.Payloads.CategoryDto;
import com.blogapplication.blogapplicationapi.Services.CategoryService;
import com.blogapplication.blogapplicationapi.Utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/category/create")
	public ResponseEntity<ApiResponse<CategoryDto>> createCategory(@Valid @RequestBody CategoryDto categorydto) {
		return categoryService.createCategory(categorydto);
	}

	@PutMapping("/category/update/{category_id}")
	public ResponseEntity<ApiResponse<CategoryDto>> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer category_id) {
		return categoryService.updateCategory(categoryDto, category_id);
	}

	@DeleteMapping("/category/delete/{category_id}")
	public ResponseEntity<ApiResponse<String>> deleteCategory(@PathVariable Integer category_id) {
		return categoryService.DeleteCategory(category_id);
	}

	@GetMapping("/category/{id}")
	public ResponseEntity<ApiResponse<CategoryDto>> getCategoryById(@PathVariable Integer id) {
		return categoryService.getCategoryById(id);
	}

	@GetMapping("/category/findall")
	public ResponseEntity<ApiResponse<List<CategoryDto>>> findAllCategory() {
		return categoryService.getAllCategory();
	}

}
