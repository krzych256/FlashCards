package com.flashCards.services;

import com.flashCards.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Long id);

    CategoryDTO createNewCategory(CategoryDTO categoryDTO);

    CategoryDTO updateOrCreateCategory(Long id, CategoryDTO categoryDTO);

    void deleteById(Long id);
}
