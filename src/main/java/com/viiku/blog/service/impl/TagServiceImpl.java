package com.viiku.blog.service.impl;

import com.viiku.blog.domain.dto.CategoryDto;
import com.viiku.blog.domain.dto.TagDto;
import com.viiku.blog.domain.mapper.TagMapper;
import com.viiku.blog.domain.model.entities.TagEntity;
import com.viiku.blog.domain.model.payload.request.TagRequest;
import com.viiku.blog.domain.model.payload.response.CategoryResponse;
import com.viiku.blog.domain.model.payload.response.TagResponse;
import com.viiku.blog.repositories.TagRepository;
import com.viiku.blog.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public TagResponse getTagList() {
        return tagRepository.findAll().stream()
                .map(tagEntity -> {
                    TagDto dto = tagMapper.toTagResponse(tagEntity);
                    return TagResponse.builder()
                            .id(dto.getId())
                            .name(dto.getName())
                            .postCount(dto.getPostCount())
                            .build();
                })
                .toList();
    }

    @Override
    public TagResponse createTag(TagRequest tagRequest) {
        TagEntity tagEntity = TagEntity.builder()
                .name(tagRequest.getName())
                .build();

        tagRepository.save(tagEntity);


    }
}
