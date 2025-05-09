package com.viiku.blog.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class CategoryDto {

    private UUID id;
    private String name;
    private long postCount;
}
