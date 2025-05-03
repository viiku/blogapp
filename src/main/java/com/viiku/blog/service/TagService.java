package com.viiku.blog.service;

import com.viiku.blog.domain.model.payload.request.TagRequest;
import com.viiku.blog.domain.model.payload.response.TagResponse;

public interface TagService {

    TagResponse getTagList();

    TagResponse createTag(TagRequest tagRequest);
}
