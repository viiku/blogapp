package com.viiku.blog.service.impl;

import com.viiku.blog.domain.dto.CategoryDto;
import com.viiku.blog.domain.mapper.CategoryMapper;
import com.viiku.blog.domain.model.entities.CategoryEntity;
import com.viiku.blog.domain.model.payload.request.CategoryRequest;
import com.viiku.blog.domain.model.payload.response.CategoryResponse;
import com.viiku.blog.repositories.CategoryRepository;
import com.viiku.blog.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getCategoryList() {

        return categoryRepository.findAll().stream()
                .map(categoryEntity -> {
                    CategoryDto dto = categoryMapper.mapToTarget(categoryEntity);
                    return CategoryResponse.builder()
                            .id(dto.getId())
                            .name(dto.getName())
                            .postCount(dto.getPostCount())
                            .build();
                })
                .toList();
    }

    @Override
    @Transactional
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        
        String categoryName = categoryRequest.getName();

        if (categoryRepository.existsByNameIgnoreCase(categoryName)) {
            throw new IllegalArgumentException("Category Name already exist:- " + categoryName);
        }

        // Convert Request → Entity
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .name(categoryRequest.getName())
                .build();

        // Save to DB
        CategoryEntity savedEntity = categoryRepository.save(categoryEntity);

        // Map Entity → DTO → Response
        CategoryDto dto = categoryMapper.mapToTarget(savedEntity);
        return CategoryResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .postCount(dto.getPostCount())
                .build();
    }

    @Override
    public void deleteCategory(UUID id) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);
        if(categoryEntity.isPresent()) {
//            if(categoryEntity.get().getPosts().isEmpty()) {
                throw new IllegalStateException("Category has post associated with it.");
//            }
        }
        categoryRepository.deleteById(id);
    }
}
