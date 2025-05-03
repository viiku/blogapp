package com.viiku.blog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@Tag(name = "Post", description = "Post management APIs")
public class PostController {

    @GetMapping
    @Operation(
            summary = "Retrieve all posts",
            description = "Get a list of all blog posts",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    public String getAllPost() {
        return "Return list of all published posts";
    }

    @PostMapping
    public String createPost() {
        return "Create a post";
    }

    @GetMapping("/{id}")
    public String getPostById(@PathVariable String id) {
        return "Return post by id";
    }

    @PutMapping("/{id}")
    public String updatePost(@PathVariable String id) {
        return "Return updated post by id";
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable String id) {
        return "Delete a post by id";
    }

    @GetMapping("/drafts")
    public String getDrafts() {
        return "Return Draft post for authenticated user";
    }
}
