package com.viiku.blog.domain.model.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class CategoryResponse {

    private UUID id;
    private String name;
    private long postCount;
}
