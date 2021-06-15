package com.innocentchdth.zss.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.innocentchdth.zss.dto.CategoryDto;
import com.innocentchdth.zss.exceptions.DuplicateResourceException;
import com.innocentchdth.zss.model.Category;
import com.innocentchdth.zss.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);
	private final CategoryRepository categoryRepository;

	private final ModelMapper modelMapper;

	public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void createCategory(CategoryDto categoryDto) {
		Optional<Category> categoryById = categoryRepository.findById(categoryDto.getId());
		categoryById.ifPresent(book -> {
			throw new DuplicateResourceException(
					"Category with same id present. " + "Either use update API to update the category ");
		});
		if (!categoryById.isPresent()) {
			LOGGER.info("No Duplicates found.");
			Category category = modelMapper.map(categoryDto, Category.class);
			// Set the status to available
			LOGGER.info("The data are mapped and ready to save.");

			// Save to category
			categoryRepository.save(category);
		}

	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		return mapCategoryListToCategoryDtoList(categories);
	}

	private List<CategoryDto> mapCategoryListToCategoryDtoList(List<Category> categories) {
		return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
	}

}
