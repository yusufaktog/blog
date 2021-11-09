package com.folksdev.blog.controller;

import com.folksdev.blog.dto.PostDto;
import com.folksdev.blog.dto.request.CreatePostRequest;
import com.folksdev.blog.entity.Post;
import com.folksdev.blog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/v1/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable String id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<PostDto> createPost(@PathVariable String userId,
                                              @Valid @RequestBody CreatePostRequest request) {
        return ResponseEntity.ok(postService.createPost(request));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String postId) {
        return ResponseEntity.ok(postService.deletePostByID(postId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody CreatePostRequest request) {
        return ResponseEntity.ok(postService.updatePost(request));
    }
}
