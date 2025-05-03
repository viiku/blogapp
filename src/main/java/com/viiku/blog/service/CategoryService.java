package com.viiku.blog.service;

import com.viiku.blog.domain.model.payload.request.CategoryRequest;
import com.viiku.blog.domain.model.payload.response.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<CategoryResponse> getCategoryList();

    CategoryResponse createCategory(CategoryRequest categoryRequest);

    void deleteCategory(UUID id);
}
