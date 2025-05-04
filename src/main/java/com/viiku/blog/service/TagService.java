package com.viiku.blog.service;

import com.viiku.blog.domain.model.payload.request.TagRequest;
import com.viiku.blog.domain.model.payload.response.TagResponse;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagService {

    List<TagResponse> getTagList();

    List<TagResponse> createTags(Set<String> names);

    void deleTag(UUID id);
}
