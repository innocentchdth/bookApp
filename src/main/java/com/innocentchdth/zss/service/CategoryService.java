package com.innocentchdth.zss.service;

import java.util.List;

import com.innocentchdth.zss.dto.CategoryDto;

public interface CategoryService {
	
	void createCategory(CategoryDto categoryDto);
	 List<CategoryDto> getAllCategories();

}
