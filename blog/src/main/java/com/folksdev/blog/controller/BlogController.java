package com.folksdev.blog.controller;

import com.folksdev.blog.dto.BlogDto;
import com.folksdev.blog.dto.request.CreateBlogRequest;
import com.folksdev.blog.entity.Blog;
import com.folksdev.blog.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "v1/Blog")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public ResponseEntity<List<BlogDto>> getBlogs() {
        return ResponseEntity.ok(blogService.getAllBlogList());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BlogDto> findByBlogId(@PathVariable String blogId) {
        return ResponseEntity.ok(blogService.getBlogById(blogId));
    }

    @PostMapping
    public ResponseEntity<BlogDto> createBlog(@Valid @RequestBody CreateBlogRequest createBlogRequest) {
        return ResponseEntity.ok(blogService.createBlog(createBlogRequest));
    }

    @PutMapping
    public ResponseEntity<BlogDto> updateBlog(@Valid @RequestBody CreateBlogRequest request) {
        return ResponseEntity.ok(blogService.updateBlog(request));
    }

    @DeleteMapping(value = "/{blogId}")
    public ResponseEntity<String> deleteBlog(@PathVariable String blogId) {
        return ResponseEntity.ok(blogService.deleteByBlogId(blogId));
    }

}