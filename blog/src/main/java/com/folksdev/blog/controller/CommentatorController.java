package com.folksdev.blog.controller;


import com.folksdev.blog.dto.CommentatorDto;
import com.folksdev.blog.dto.request.CreateCommentatorRequest;
import com.folksdev.blog.dto.request.update.UpdateCommentatorRequest;
import com.folksdev.blog.service.CommentatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/commentator")
public class CommentatorController {

    private final CommentatorService commentatorService;

    public CommentatorController(CommentatorService commentatorService) {
        this.commentatorService = commentatorService;
    }

    @GetMapping()
    public ResponseEntity<List<CommentatorDto>> getAllCommentators() {
        return ResponseEntity.ok(commentatorService.getAllCommentatorDtoList());
    }

    @GetMapping("/{commentatorId}")
    public ResponseEntity<CommentatorDto> getCommentatorById(@PathVariable String commentatorId) {
        return ResponseEntity.ok(commentatorService.getCommentatorById(commentatorId));
    }

    @PostMapping("/{blogId}")
    public ResponseEntity<CommentatorDto> createCommentator(@PathVariable String blogId, @Valid @RequestBody CreateCommentatorRequest request) {
        return ResponseEntity.ok(commentatorService.createCommentator(blogId, request));
    }

    @DeleteMapping("/{commentatorId}")
    public ResponseEntity<String> deleteCommentator(@PathVariable String commentatorId) {
        return ResponseEntity.ok(commentatorService.deleteCommentatorByID(commentatorId));
    }

    @PutMapping("/{commentatorId}")
    public ResponseEntity<CommentatorDto> updateCommentator(@PathVariable String commentatorId,@Valid @RequestBody UpdateCommentatorRequest request) {
        return ResponseEntity.ok(commentatorService.updateCommentator(commentatorId, request));
    }
}
