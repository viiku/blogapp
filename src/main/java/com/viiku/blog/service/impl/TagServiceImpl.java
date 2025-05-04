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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public List<TagResponse> getTagList() {
        return tagRepository.findAll().stream()
                .map(tagEntity -> {
                    TagDto dto = tagMapper.toTagDto(tagEntity);
                    return TagResponse.builder()
                            .id(dto.getId())
                            .name(dto.getName())
                            .postCount(dto.getPostCount())
                            .build();
                })
                .toList();
    }

    @Transactional
    @Override
    public List<TagResponse> createTags(Set<String> tagNames) {
        List<TagEntity> existingTags = tagRepository.findByNameIn(tagNames);
        Set<String> existingTagNames = existingTags.stream().map(TagEntity:: getName).collect(Collectors.toSet());
        List<TagEntity> newTags = tagNames.stream().filter(name -> !existingTagNames.contains(name))
                .map(name -> TagEntity.builder()
                        .name(name)
                        .posts(new HashSet<>())
                        .build())
                .collect(Collectors.toList());

        List<TagEntity> savedTags = new ArrayList<>();
        if (!newTags.isEmpty()) {
            savedTags = tagRepository.saveAll(newTags);
        }
        savedTags.addAll(existingTags);

        List<TagDto> mappedToDto = tagMapper.toTagDtoList(savedTags);
        List<TagResponse> tagResponses = tagMapper.toTagResponseFromDto(mappedToDto);
        return tagResponses;
    }

    @Override
    public void deleTag(UUID id) {
        tagRepository.findById(id).ifPresent(tag -> {
            if(!tag.getPosts().isEmpty()) {
                throw new IllegalStateException("Cannot delete tag with posts");
            }
            tagRepository.deleteById(id);
        });
    }

//    @Override
//    public TagResponse createTag(TagRequest tagRequest) {
//        TagEntity tagEntity = TagEntity.builder()
//                .name(tagRequest.getName())
//                .build();
//
//        tagRepository.save(tagEntity);
//
//        return null;
//    }
}
