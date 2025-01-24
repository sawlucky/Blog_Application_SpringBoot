package com.blogapplication.blogapplicationapi.Services.Impl;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogapplication.blogapplicationapi.Exceptions.IdNotFoundException;
import com.blogapplication.blogapplicationapi.Model.Category;
import com.blogapplication.blogapplicationapi.Payloads.CategoryDto;
import com.blogapplication.blogapplicationapi.Repositories.CategoryRepo;
import com.blogapplication.blogapplicationapi.Services.CategoryService;
import com.blogapplication.blogapplicationapi.Utils.ApiResponse;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public ResponseEntity<ApiResponse<CategoryDto>> createCategory(CategoryDto categoryDto) {
		Category category = categoryDtoToCategory(categoryDto);
		Category savedCategory = categoryRepo.save(category);
		ApiResponse<CategoryDto> api = new ApiResponse<CategoryDto>(HttpStatus.CREATED.value(),
				"category created succesfully", categoryToCategoryDto(savedCategory));
		return new ResponseEntity<ApiResponse<CategoryDto>>(api, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ApiResponse<CategoryDto>> updateCategory(CategoryDto categoryDto, Integer category_id) {
		Optional<Category> o = categoryRepo.findById(category_id);
		if (!o.isPresent()) {
			throw new IdNotFoundException(category_id + " invalid Id can't process");
		}
		Category category = categoryDtoToCategory(categoryDto);
		category.setCategory_id(category_id);
		Category updateCategory = categoryRepo.save(category);
		ApiResponse<CategoryDto> api = new ApiResponse<CategoryDto>(HttpStatus.OK.value(),
				"category_data updated Successfully", categoryToCategoryDto(updateCategory));
		return new ResponseEntity<ApiResponse<CategoryDto>>(api, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<String>> DeleteCategory(Integer category_id) {
		Optional<Category> o = categoryRepo.findById(category_id);
		if (!o.isPresent()) {
			throw new IdNotFoundException(category_id + " invalid Id can't process");
		}
		categoryRepo.delete(o.get());
		ApiResponse<String> api = new ApiResponse<String>(HttpStatus.OK.value(), "deleted Successfully", "succes");
		return new ResponseEntity<ApiResponse<String>>(api, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategory() {
		List<Category> list = categoryRepo.findAll();
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponse<>(HttpStatus.OK.value(), "No users found", Collections.emptyList()));
		}
		List<CategoryDto> newList = list.stream().map(category -> categoryToCategoryDto(category))
				.collect(Collectors.toList());

		ApiResponse<List<CategoryDto>> api = new ApiResponse<List<CategoryDto>>(HttpStatus.OK.value(),
				"All data are listed here", newList);
		return new ResponseEntity<ApiResponse<List<CategoryDto>>>(api, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<CategoryDto>> getCategoryById(Integer category_id) {
		Optional<Category> o = categoryRepo.findById(category_id);
		if (!o.isPresent()) {
			throw new IdNotFoundException(category_id + " invalid Id can't process");
		}
		ApiResponse<CategoryDto> api = new ApiResponse<CategoryDto>(HttpStatus.FOUND.value(), "data found",
				categoryToCategoryDto(o.get()));
		return new ResponseEntity<ApiResponse<CategoryDto>>(api, HttpStatus.FOUND);
	}

	public Category categoryDtoToCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		return category;
	}

	public CategoryDto categoryToCategoryDto(Category category) {
		CategoryDto dto = modelMapper.map(category, CategoryDto.class);
		return dto;
	}

}
