package com.viiku.blog.domain.mapper;

import com.viiku.blog.common.model.mapper.BaseMapper;
import com.viiku.blog.domain.dto.CategoryDto;
import com.viiku.blog.domain.model.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<CategoryEntity, CategoryDto> {

    @Override
    CategoryDto mapToTarget(CategoryEntity category);

    @Override
    CategoryEntity mapToSource(CategoryDto dto);
}
