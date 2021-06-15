package com.innocentchdth.zss.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.innocentchdth.zss.dto.CategoryDto;
import com.innocentchdth.zss.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "Category Controller")
public class CategoryController {
	
	private final CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@ApiOperation(value = "Create Category")
	@PostMapping("/categories")
	@ResponseStatus(HttpStatus.CREATED)
	public void createBook(@Valid @RequestBody CategoryDto categoryDto) {
		categoryService.createCategory(categoryDto);
		
	}
	 @ApiOperation(value = "Get All Categories")
	    @GetMapping("/categories")
	    public List<CategoryDto> getAllCategories() {
	        return categoryService.getAllCategories();
	    }
	    
}
