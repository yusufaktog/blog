package com.folksdev.blog.controller;

import com.folksdev.blog.dto.BlogDto;
import com.folksdev.blog.dto.request.CreateBlogRequest;
import com.folksdev.blog.dto.request.update.UpdateBlogRequest;
import com.folksdev.blog.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "v1/Blog")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public ResponseEntity<List<BlogDto>> getBlogs() {
        return ResponseEntity.ok(blogService.getAllBlogDtoList());
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
    public ResponseEntity<BlogDto> updateBlog(@PathVariable String id,@Valid @RequestBody UpdateBlogRequest request) {
        return ResponseEntity.ok(blogService.updateBlog(id,request));
    }

    @DeleteMapping(value = "/{blogId}")
    public ResponseEntity<String> deleteBlog(@PathVariable String blogId) {
        return ResponseEntity.ok(blogService.deleteByBlogId(blogId));
    }

}