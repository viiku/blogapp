package com.viiku.blog.controller;

import com.viiku.blog.domain.model.payload.request.TagRequest;
import com.viiku.blog.domain.model.payload.response.TagResponse;
import com.viiku.blog.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tags")
public class TagController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<TagResponse> getAllTag() {

        return ResponseEntity.ok(tagService.getTagList());
    }

    @PostMapping
    public ResponseEntity<TagResponse> createTag(@Valid @RequestBody TagRequest tagRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(tagService.createTag(tagRequest))
    }

    @DeleteMapping("/{id}")
    public String deleteTag(@PathVariable String id) {
        return "Delete a tag";
    }
}
