package com.folksdev.blog.controller;


import com.folksdev.blog.dto.AuthorDto;
import com.folksdev.blog.dto.request.CreateAuthorRequest;
import com.folksdev.blog.entity.Author;
import com.folksdev.blog.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
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
        return ResponseEntity.ok(authorService.getAllAuthorList().stream().collect(Collectors.toList()));
    }

    @GetMapping("/{AuthorId}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable String authorId)  {
        return ResponseEntity.ok(authorService.getAuthorById(authorId));
    }

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody CreateAuthorRequest request) {
        return ResponseEntity.ok(authorService.createAuthor(request));
    }

    @DeleteMapping("/{AuthorId}")
    public ResponseEntity<String> deleteAuthor(@PathVariable String AuthorId)  {
        return ResponseEntity.ok(authorService.deleteAuthorByID(AuthorId));
    }

    @PutMapping("/{AuthorId}")
    public ResponseEntity<AuthorDto> updateAuthor(@Valid @RequestBody CreateAuthorRequest request)  {
        return ResponseEntity.ok(authorService.updateAuthor(request));
    }
}
