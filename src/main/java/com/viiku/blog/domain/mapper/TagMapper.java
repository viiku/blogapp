package com.viiku.blog.domain.mapper;

import com.viiku.blog.common.model.mapper.BaseMapper;
import com.viiku.blog.domain.dto.CategoryDto;
import com.viiku.blog.domain.dto.TagDto;
import com.viiku.blog.domain.model.entities.CategoryEntity;
import com.viiku.blog.domain.model.entities.PostEntity;
import com.viiku.blog.domain.model.entities.TagEntity;
import com.viiku.blog.domain.model.enums.PostStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper extends BaseMapper<CategoryEntity, CategoryDto> {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    TagDto toTagResponse(TagEntity tagEntity);

    @Named("calculatePostCount")
    default Integer calculatePostCount(List<PostEntity> postEntityList) {
        if (postEntityList == null) {
            return 0;
        }

        return (int) postEntityList.stream()
                .filter(postEntity -> PostStatus.PUBLISHED.equals(postEntity.getStatus()))
                .count();
    }
}
