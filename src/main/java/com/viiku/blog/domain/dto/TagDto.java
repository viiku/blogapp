package com.viiku.blog.domain.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {

    private UUID id;
    private String name;
    private Integer postCount;
}
