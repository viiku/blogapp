package com.viiku.blog.controller;

import com.viiku.blog.domain.model.payload.request.TagRequest;
import com.viiku.blog.domain.model.payload.response.TagResponse;
import com.viiku.blog.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tags")
public class TagController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags() {

        return ResponseEntity.ok(tagService.getTagList());
    }

    @PostMapping
    public ResponseEntity<List<TagResponse>> createTags(@Valid @RequestBody TagRequest tagRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(tagService.createTags(tagRequest.getNames()));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id) {

        tagService.deleTag(id);
        return ResponseEntity.noContent().build();
    }
}
