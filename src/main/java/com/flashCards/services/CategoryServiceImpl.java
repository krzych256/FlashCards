package com.flashCards.services;

import com.flashCards.domain.Category;
import com.flashCards.exceptions.ResourceNotFoundException;
import com.flashCards.mapper.CategoryMapper;
import com.flashCards.model.CategoryDTO;
import com.flashCards.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper,
                               CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::categoryToCategoryDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CategoryDTO createNewCategory(CategoryDTO categoryDTO) {

        Category category = categoryMapper.categoryDTOToCategory(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.categoryToCategoryDTO(savedCategory);
    }

    @Override
    public CategoryDTO updateOrCreateCategory(Long id, CategoryDTO categoryDTO) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findById(id)
                .map(category -> {
                    category.setName(categoryDTO.getName());
                    return categoryRepository.save(category);
                })
                .orElseGet(() -> {
                    categoryDTO.setId(id);
                    return categoryRepository.save(categoryMapper.categoryDTOToCategory(categoryDTO));
                })
        );
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
