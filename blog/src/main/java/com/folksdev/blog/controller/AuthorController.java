package com.folksdev.blog.controller;


import com.folksdev.blog.dto.AuthorDto;
import com.folksdev.blog.dto.request.CreateAuthorRequest;
import com.folksdev.blog.dto.request.update.UpdateAuthorRequest;
import com.folksdev.blog.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public ResponseEntity<List<AuthorDto>> getAllUsers() {
        return ResponseEntity.ok(new ArrayList<>(authorService.getAllAuthorDtoList()));
    }

    @GetMapping("/{AuthorId}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable String authorId) {
        return ResponseEntity.ok(authorService.getAuthorById(authorId));
    }

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody CreateAuthorRequest request) {
        return ResponseEntity.ok(authorService.createAuthor(request));
    }

    @DeleteMapping("/{AuthorId}")
    public ResponseEntity<String> deleteAuthor(@PathVariable String AuthorId) {
        return ResponseEntity.ok(authorService.deleteAuthorByID(AuthorId));
    }

    @PutMapping("/{AuthorId}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable String authorId, @Valid @RequestBody UpdateAuthorRequest request) {
        return ResponseEntity.ok(authorService.updateAuthor(authorId,request));
    }
}