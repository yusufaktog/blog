package com.folksdev.blog.controller;

import com.folksdev.blog.dto.PostDto;
import com.folksdev.blog.dto.request.CreatePostRequest;
import com.folksdev.blog.dto.request.update.UpdatePostRequest;
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

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable String postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @PostMapping("/{authorId}/{blogId}")
    public ResponseEntity<PostDto> createPost(@PathVariable String authorId, @PathVariable String blogId, @Valid @RequestBody CreatePostRequest request) {
        return ResponseEntity.ok(postService.createPost(authorId, blogId, request));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String postId) {
        return ResponseEntity.ok(postService.deletePostByID(postId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable String postId,@Valid @RequestBody UpdatePostRequest request) {
        return ResponseEntity.ok(postService.updatePost(postId,request));
    }
}
