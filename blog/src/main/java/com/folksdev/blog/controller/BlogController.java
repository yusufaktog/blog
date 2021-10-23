package com.folksdev.blog.controller;

import com.folksdev.blog.dto.CreateBlogDto;
import com.folksdev.blog.dto.CreateBlogRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "v1/Blog")
public class BlogController {


    @GetMapping
    public ResponseEntity<String> getBlog() {
        return ResponseEntity.ok("get Blog");
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<String> getBlogById(@PathVariable String id) {
        return ResponseEntity.ok("Blog with id: " + id);
    }

    @PostMapping
    public ResponseEntity<CreateBlogDto> createBlog(@Valid @RequestBody CreateBlogRequest createBlogRequest) {

        CreateBlogDto createBlogDto = new CreateBlogDto(createBlogRequest.getId(),
                createBlogRequest.getName(),
                createBlogRequest.getContent(),
                createBlogRequest.getAuthor());

        return new ResponseEntity<>(createBlogDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateBlog(@PathVariable String id, @RequestBody String a) {
        return ResponseEntity.ok("Blog id: " + id + " is updated with " + a);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable String id) {
        return ResponseEntity.ok("Blog with id: " + id + " has been created");
    }


}