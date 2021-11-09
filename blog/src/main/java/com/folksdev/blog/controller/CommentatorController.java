package com.folksdev.blog.controller;


import com.folksdev.blog.dto.CommentatorDto;
import com.folksdev.blog.dto.request.CreateCommentatorRequest;
import com.folksdev.blog.entity.Commentator;
import com.folksdev.blog.service.CommentatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/Commentator")
public class CommentatorController {

    private final CommentatorService commentatorService;

    public CommentatorController(CommentatorService commentatorService) {
        this.commentatorService = commentatorService;
    }

    @GetMapping()
    public ResponseEntity<List<CommentatorDto>> getAllCommentators() {
        return ResponseEntity.ok(commentatorService.getAllCommentatorList());
    }

    @GetMapping("/{CommentatorId}")
    public ResponseEntity<CommentatorDto> getCommentatorById(@PathVariable String commentatorId) {
        return ResponseEntity.ok(commentatorService.getCommentatorById(commentatorId));
    }

    @PostMapping
    public ResponseEntity<CommentatorDto> createCommentator(@Valid @RequestBody CreateCommentatorRequest request) {
        return ResponseEntity.ok(commentatorService.createCommentator(request));
    }

    @DeleteMapping("/{CommentatorId}")
    public ResponseEntity<String> deleteCommentator(@PathVariable String commentatorId) {
        return ResponseEntity.ok(commentatorService.deleteCommentatorByID(commentatorId));
    }

    @PutMapping("/{CommentatorId}")
    public ResponseEntity<CommentatorDto> updateCommentator(@Valid @RequestBody CreateCommentatorRequest request) {
        return ResponseEntity.ok(commentatorService.updateCommentator(request));
    }
}
